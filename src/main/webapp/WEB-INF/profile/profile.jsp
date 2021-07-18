<%@ page import="commons.beans.UserBean" %>
<%@ page import="dao.interfaces.UserDAOInterface" %>
<%@ page import="dao.implementation.UserDAO" %>
<%@ page import="servlets.profile.ProfileServlet" %>
<%@ page import="dao.implementation.FriendDao" %>
<%@ page import="dao.interfaces.FriendDaoInterface" %>
<%@ page import="dao.implementation.UserWrapperDao" %>
<%@ page import="commons.beans.StatsBean" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.07.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%
    UserWrapperDao userWrapperDao = (UserWrapperDao) request.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
    UserBean user = userWrapperDao.getUserById(Integer.parseInt(request.getParameter("id")));
    String check = (String) request.getAttribute(ProfileServlet.GO_TO_PARAM);
%>

<html>
<head>
    <title> <%=user.getUsername()%> </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
<a href="/home"> <button class="return_link" id="returnBtnId">
    <img src="${pageContext.request.contextPath}/resources/home.svg" class="home_svg" alt="error">
</button>
</a>

<div class = "all">
    <div class = "user">
        <h2> Username: <%= user.getUsername()%> </h2>
        <h3> Name: <%= user.getName() %>  </h3>
        <h3> Surname: <%= user.getSurname() %>  </h3>
        <h3> Birthday: <%= user.getBirthday() %>  </h3>

        <% if(check.equals(ProfileServlet.GO_TO_OWN)) { %>
        <form method="post">
            <input name="id" type="hidden" value="<%= user.getId() %>"/>
            <input type="submit" class = "edit" value="Edit"/>
        </form>

        <% }else if (check.equals(ProfileServlet.GO_TO_FRIEND)){ %>

        <form method="post">
            <input name="id" type="hidden" value="<%= request.getParameter("id") %>"/>
            <input type="submit" class = "unfriend" value="Unfriend"/>
        </form>

        <%}else{%>

        <form method="post">
            <input name="id" type="hidden" value="<%= request.getParameter("id") %>"/>
            <input type="submit" class = "add" value="Add Friend"/>
        </form>

        <% }%>
    </div>

    <div class = "stats">
        <% StatsBean stats = userWrapperDao.getStatsById(user.getId()); %>
        <h2>Wins: <%=stats.getWins() %> </h2>
        <h2>Loses: <%=stats.getLosses()%> </h2>
        <h2>Games Played: <%=stats.getGamesPlayed()%></h2>
    </div>

    <div class = "friends">
        <h3>Friends:</h3>

        <ul>
            <%
                FriendDaoInterface friends = (FriendDaoInterface) request.getServletContext().getAttribute(FriendDao.FRIEND_DAO_ATTR);
                String url = "/profile";
                int mainUser = (Integer)request.getSession().getAttribute(UserBean.USER_ATTR);
                for(int id:  friends.friendIdList(user.getId()))
                    if(mainUser!=id)
                        out.println("<li> <a href=" + url + "?id=" + id + "> <button type=\"button\" class = \"button\">" + userWrapperDao.getUserById(id).getUsername() + "</button></a> </li>");
            %>
        </ul>
    </div>

    <div class="nothing"></div>

    <div class = "rank">
        <% if(check.equals(ProfileServlet.GO_TO_OWN)) { %>
        <h1> You are # <%=userWrapperDao.getRankById(user.getId())%></h1>
        <% }else {%>
        <h1> <%=user.getUsername()%> is # <%=userWrapperDao.getRankById(user.getId())%></h1>
        <%}%>
    </div>

</div>

</body>
</html>