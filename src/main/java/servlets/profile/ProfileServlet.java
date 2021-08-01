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
    public static final String GO_TO_RECEIVER= "3";
    public static final String GO_TO_SENDER= "4";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

            if(friendDao.isFriend(userId,userToCheck)){
                req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_FRIEND);

            }else if (friendDao.isSender(userId,userToCheck)) {
                req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_SENDER);
            } else if (friendDao.amSender(userId,userToCheck)){
                req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_RECEIVER);

            } else req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_STRANGER);

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
        FriendDaoInterface friendDao = (FriendDaoInterface) req.getServletContext().getAttribute(FriendDao.FRIEND_DAO_ATTR);
        int checking = Integer.parseInt(req.getParameter("id"));
        int currId = 0;
        switch (Integer.parseInt(req.getParameter("buttonType"))){
            case 1:
                mainButton( user,  checking,  friendDao,  req,  resp);
                break;
            case 2:
                currId = Integer.parseInt(req.getParameter("currId"));
                setCorrectAttr( user,  checking,  friendDao,  req);
                addFriendAndRefresh(user,currId,friendDao,req,resp);
                break;
            case 3:
                currId = Integer.parseInt(req.getParameter("currId"));
                setCorrectAttr( user,  checking,  friendDao,  req);
                removeFriendAndRefresh(user, currId,friendDao,req,resp);
                break;
            case 4:
                currId = Integer.parseInt(req.getParameter("currId"));
                setCorrectAttr( user,  checking,  friendDao,  req);
                friendDao.acceptFriendRequest(currId, user);
                break;
            case 5:
                currId = Integer.parseInt(req.getParameter("currId"));
                setCorrectAttr( user,  checking,  friendDao,  req);
                friendDao.rejectFriendRequest(currId, user);
                break;
        }
    }


    private void setCorrectAttr(int user, int checking, FriendDaoInterface friendDao, HttpServletRequest req){
        if(user == checking){
            req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_OWN);
        } else if(friendDao.isFriend(user,checking)){
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_FRIEND);
        } else if (friendDao.isSender(user,checking)){
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_SENDER);
        } else if(friendDao.amSender(user,checking)){
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_RECEIVER);
        } else{
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_STRANGER);
        }
    }



    private void mainButton(int user, int checking, FriendDaoInterface friendDao, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(user == checking){
            req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_OWN);
            resp.sendRedirect("/edit-profile");
        } else if(friendDao.isFriend(user,checking)){
            req.setAttribute(ProfileServlet.GO_TO_PARAM,ProfileServlet.GO_TO_STRANGER);
            removeFriendAndRefresh(user,checking,friendDao,req,resp);
        } else if(friendDao.isSender(user,checking)){
            int acc = Integer.parseInt(req.getParameter("acc"));
            if(acc == 1){
                req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_FRIEND);
                addFriendAndRefresh(user, checking,friendDao,req,resp);
            } else{
                req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_STRANGER);
                friendDao.rejectFriendRequest(checking,user);
            }
        }else if(friendDao.amSender(user,checking)){
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_RECEIVER);
        } else {
            req.setAttribute(ProfileServlet.GO_TO_PARAM, ProfileServlet.GO_TO_FRIEND);
            addFriendAndRefresh(user, checking,friendDao,req,resp);
        }
    }

    private void removeFriendAndRefresh(int user, int checking, FriendDaoInterface friendDao, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        friendDao.unfriend(user,checking);
        req.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(req,resp);
    }

    private void addFriendAndRefresh(int user, int checking, FriendDaoInterface friendDao, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        if(friendDao.isSender(user, checking)){
            friendDao.acceptFriendRequest(checking,user);
        } else if(!friendDao.isFriend(user,checking)){
            friendDao.sendFriendRequest(user, checking);
        }
        req.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(req,resp);
    }

}

