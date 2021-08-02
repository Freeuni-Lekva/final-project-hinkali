package servlets.deck;

import commons.beans.UserBean;
import dao.implementation.DeckWrapperDAO;
import dao.interfaces.DeckWrapperInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/choose-deck")
public class DeckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.sendRedirect("register");
            return;
        }
        int currentUserId = (int) session.getAttribute(UserBean.USER_ATTR);
        String strId = req.getParameter("id");
        int profileId = (strId == null || strId.equals("")) ? -1 : Integer.parseInt(strId);
        if (profileId == -1 || profileId != currentUserId)
            resp.sendRedirect("choose-deck?id=" + currentUserId); // send to own profile edit
        else
            req.getRequestDispatcher("/WEB-INF/deck/decks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeckWrapperInterface deckDao = (DeckWrapperInterface) getServletContext().getAttribute(DeckWrapperDAO.DECK_WRAPPER_ATTR);
        int userId = Integer.parseInt(req.getParameter("userId"));
        int deckId = Integer.parseInt(req.getParameter("deckId"));
        deckDao.updateUserDeck(userId, deckId);
    }
}
