package servlets.search;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.interfaces.UserDAOInterface;
import model.SearchResults;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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

        String query = getQuery(req);
        UserDAOInterface userDao = (UserDAOInterface) req.getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        SearchResults results = getResults(userDao, query);

        // for quick testing
        //results.addEntry(new UserBean("abcd", "aa", "bb", "jj", null));
        //results.addEntry(new UserBean("cuno", "2", "22", "jj", null));

        req.setAttribute(RESULTS_ATTRIBUTE, results);
        req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
    }

    private SearchResults getResults(UserDAOInterface userDao, String query) {

        return new SearchResults();
    }

    private String getQuery(HttpServletRequest req) {
        if (req.getParameterMap().isEmpty()) return "";
        return req.getParameterValues(SEARCH_SERVLET_PARAMETER)[0];
    }
}
