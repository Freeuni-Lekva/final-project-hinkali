package servlets.profile;

import commons.beans.UserBean;
import dao.implementation.FriendDao;
import dao.implementation.UserDAO;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserDAOInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public static final String GO_TO_PARAM = "profile";
    public static final String GO_TO_OWN = "0";
    public static final String GO_TO_FRIEND = "1";
    public static final String GO_TO_STRANGER = "2";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(UserBean.USER_ATTR, 1) ;
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
            resp.sendRedirect("/");
            return;
        }
        int userId = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        int userToCheck = 0;
        if(req.getParameter("id") == null){
            resp.sendRedirect("profile?id=" + userId);
            return;
        } else {
            userToCheck = Integer.parseInt(req.getParameter("id"));
        }

        //Direct to own profile page
        if(userToCheck == userId ){
            req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_OWN);

        }else{
            FriendDaoInterface friendDao = (FriendDaoInterface) req.getServletContext().getAttribute(FriendDao.FRIEND_DAO_ATTR);
            //Direct to friend's profile page
            if(friendDao.isFriend(userId,userToCheck)){
                req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_FRIEND);
                //Direct to stranger's profile page
            }else{
                req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_STRANGER);
            }
        }
        req.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
            resp.sendRedirect("/");
            return;
        }

        int user = (Integer)req.getSession().getAttribute(UserBean.USER_ATTR);
        int checking = Integer.parseInt(req.getParameter("id"));
        FriendDaoInterface friendDao = (FriendDaoInterface) req.getServletContext().getAttribute(FriendDao.FRIEND_DAO_ATTR);

        if(user == checking){
            resp.sendRedirect("/edit-profile");
        } else if(friendDao.isFriend(user,checking)){
            friendDao.unfriend(user,checking);
            req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_STRANGER);
            req.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(req,resp);
            //change button to "add friend"
        } else {
            if(friendDao.isPendingFriend(user, checking)){
                friendDao.acceptFriendRequest(checking,user);
            } else{
                friendDao.sendFriendRequest(user, checking);
            }
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_FRIEND);
            req.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(req,resp);
            //change button to "unfriend"
        }
    }
}
