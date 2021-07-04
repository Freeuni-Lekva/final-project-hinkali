package servlets.search;

import commons.beans.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public static final String SEARCH_SERVLET_PARAMETER = "query";
    public static final String RESULTS_ATTRIBUTE = "results";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
//            resp.sendRedirect("/");
//            return;
//        }
        String query = getQuery(req);
        // dao.getUsers(new Filter(query))
        req.setAttribute(RESULTS_ATTRIBUTE, new SearchResults(new ArrayList<>()));
        req.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(req, resp);
    }

    private String getQuery(HttpServletRequest req) {
        if (req.getParameterMap().isEmpty()) return "";
        return req.getParameterValues(SEARCH_SERVLET_PARAMETER)[0];
    }
}
