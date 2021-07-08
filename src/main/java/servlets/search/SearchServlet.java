package servlets.search;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.implementation.filters.OrFilter;
import dao.implementation.filters.StringFilterInclusive;
import dao.interfaces.Filter;
import dao.interfaces.UserDAOInterface;
import model.SearchResults;
import servlets.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public static final String SEARCH_SERVLET_PARAMETER = "search_field";
    public static final String RESULTS_ATTRIBUTE = "results";
    public static final int MIN_QUERY_LENGTH = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!ContextListener.TESTING)
            if (handleUnauthorizedCase(req, resp)) return;
        if (handleFirstRequestCase(req, resp)) return;

        String query = getQuery(req).trim();
        if (isInvalidQuery(query)){
            req.setAttribute("isInvalidQuery", true);
            req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
            return;
        }

        UserDAOInterface userDao = (UserDAOInterface) req.getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        SearchResults results = getResults(userDao, query);
        addTestUsers(results);

        req.setAttribute(RESULTS_ATTRIBUTE, results);
        req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
    }

    private void addTestUsers(SearchResults results) {
        if (ContextListener.TESTING){
            for (int i = 0; i < 20; i++) {
                results.addEntry(new UserBean("user" + i, "name" + i * i+4, "surname", "password", null));
            }
        }
    }

    public static boolean handleUnauthorizedCase(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
            resp.sendRedirect("/");
            return true;
        }
        return false;
    }

    private boolean isInvalidQuery(String query) {
        return query.length() < MIN_QUERY_LENGTH;
    }

    private boolean handleFirstRequestCase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isFirstRequest(req)){
            req.setAttribute("isFirstRequest", true);
            req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
            return true;
        }
        req.setAttribute("isFirstRequest", false);
        return false;
    }

    private boolean isFirstRequest(HttpServletRequest req) {
        return req.getParameterMap().isEmpty();
    }

    private SearchResults getResults(UserDAOInterface userDao, String query) {
        if (query.isEmpty()) return new SearchResults();

        OrFilter orFilter = new OrFilter();
        StringTokenizer tk = new StringTokenizer(query);
        while (tk.hasMoreTokens()) {
            String currToken = tk.nextToken();
            if (currToken.length() <= 2) continue;
            orFilter.addFilter(buildFilterFromToken(currToken));
        }

        if (orFilter.format().isEmpty()) return new SearchResults();

        List<UserBean> users = userDao.getUsersWithFilter(orFilter);
        return new SearchResults(users);
    }

    private Filter buildFilterFromToken(String nextToken) {
        List<Filter> filterList = new ArrayList<>();
        filterList.add(new StringFilterInclusive("username", nextToken));
        filterList.add(new StringFilterInclusive("name", nextToken));
        filterList.add(new StringFilterInclusive("surname", nextToken));
        return new OrFilter(filterList);
    }

    private String getQuery(HttpServletRequest req) {
        if (req.getParameterMap().isEmpty()) return "";
        return req.getParameterValues(SEARCH_SERVLET_PARAMETER)[0];
    }
}
