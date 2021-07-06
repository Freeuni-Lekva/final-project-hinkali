<%@ page import="commons.beans.UserBean" %>
<%@ page import="dao.interfaces.UserDAOInterface" %>
<%@ page import="dao.implementation.UserDAO" %>
<%@ page import="servlets.profile.ProfileServlet" %>
<%@ page import="dao.implementation.FriendDao" %>
<%@ page import="dao.interfaces.FriendDaoInterface" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.07.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UserDAOInterface userDao = (UserDAOInterface) request.getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
    UserBean user = userDao.getUserById(Integer.parseInt(request.getParameter("id")));
    String check = (String) request.getAttribute(ProfileServlet.GO_TO_PARAM);
%>

<html>
<head>
    <title> <%=user.getUsername()%> </title>
</head>
<body>

    <h1> <%= user.getUsername()%></h1>
    <h3> Name: <%= user.getName() %>  </h3>
    <h3> Surname: <%= user.getSurname() %>  </h3>
    <h3> Birthday: <%= user.getBirthday() %>  </h3>

    <% if(check.equals(ProfileServlet.GO_TO_OWN)) { %>
        <h3> Password:  <%=user.getPassword()%>  </h3>
        <form method="post">
            <input name="id" type="hidden" value="<%= user.getId() %>"/>
            <input type="submit" value="Edit"/>
        </form>

       <% }else if (check.equals(ProfileServlet.GO_TO_FRIEND)){ %>
        <form method="post">
        <input name="id" type="hidden" value="<%= request.getParameter("id") %>"/>
        <input type="submit" value="unfriend"/>
    </form>
        <%}else{%>
    <form method="post">
        <input name="id" type="hidden" value="<%= request.getParameter("id") %>"/>
        <input type="submit" value="add friend"/>
    </form>
       <% }%>

    <p>Friends:</p>

    <ul>
        <%
            FriendDaoInterface friends = (FriendDaoInterface) request.getServletContext().getAttribute(FriendDao.FRIEND_DAO_ATTR);
            String url = "/profile";
            int mainUser = (Integer)request.getSession().getAttribute(UserBean.USER_ATTR);
            for(int id:  friends.friendIdList(user.getId()))
                if(mainUser!=id)
                out.println("<li> <a href=" + url + "?id=" + id + ">" + userDao.getUserById(id).getUsername() + "</a> </li>");
        %>
    </ul>

</body>
</html>
