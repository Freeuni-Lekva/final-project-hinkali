package servlets.search;

import commons.beans.UserBean;
import dao.implementation.filters.NoFilter;
import dao.implementation.UserDAO;
import dao.implementation.filters.OrFilter;
import dao.implementation.filters.StringFilter;
import dao.interfaces.Filter;
import dao.interfaces.UserDAOInterface;
import model.SearchResults;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
        //    resp.sendRedirect("/");
        //    return;
        //}

        String query = getQuery(req).trim();
        UserDAOInterface userDao = (UserDAOInterface) req.getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        SearchResults results = getResults(userDao, query);

        // for quick testing
        //results.addEntry(new UserBean("abcd", "aa", "bb", "jj", null));
        //results.addEntry(new UserBean("cuno", "2", "22", "jj", null));

        req.setAttribute(RESULTS_ATTRIBUTE, results);
        req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
    }

    private SearchResults getResults(UserDAOInterface userDao, String query) {
        if (query.isEmpty()) return new SearchResults();

        OrFilter orFilter = new OrFilter();
        StringTokenizer tk = new StringTokenizer(query);
        while (tk.hasMoreTokens()){
            orFilter.addFilter(buildFilterFromToken(tk.nextToken()));
        }
        System.out.println(orFilter.format());
        List<UserBean> users = userDao.getUsersWithFilter(orFilter);
        return new SearchResults(users);
    }

    private Filter buildFilterFromToken(String nextToken) {
        List<Filter> filterList = new ArrayList<>();
        filterList.add(new StringFilter("username", nextToken));
        filterList.add(new StringFilter("name", nextToken));
        filterList.add(new StringFilter("surname", nextToken));
        return new OrFilter(filterList);
    }

    private String getQuery(HttpServletRequest req) {
        if (req.getParameterMap().isEmpty()) return "";
        return req.getParameterValues(SEARCH_SERVLET_PARAMETER)[0];
    }
}
