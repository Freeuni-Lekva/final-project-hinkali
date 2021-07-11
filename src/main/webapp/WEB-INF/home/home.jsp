<%@ page import="servlets.home.HomeServlet" %>
<%@ page import="dao.implementation.UserWrapperDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="commons.beans.StatsBean" %>
<%--
  Created by IntelliJ IDEA.
  User: lobja
  Date: 7/10/2021
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserWrapperDao dao = (UserWrapperDao) request.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
    ArrayList<StatsBean> stats = dao.getStatsAllWithUsernamesAndDescendingPoints();
%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div class="buttons">
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.PLAY_BUTTON_ID%>>
        <input type="submit" value="Play">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.PROFILE_BUTTON_ID%>>
        <input type="submit" value="Profile">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.SEARCH_BUTTON_ID%>>
        <input type="submit" value="Search">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.DECKS_BUTTON_ID%>>
        <input type="submit" value="Decks">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.LOG_OUT_BUTTON_ID%>>
        <input type="submit" value="Log out">
    </form>
</div>

<div>
    <%
        int i = 0;
        while(true){
            if(i == 10 || i == stats.size())
                break;
            StatsBean sb = stats.get(i);
            out.print("<ol>" + sb.getUsername() +
                    " " + sb.getPoints() +
                    " " + sb.getGamesPlayed() +
                    " " + sb.getWins() +
                    " " + sb.getDraws() +
                    " " + sb.getLosses() +
                    "</ol>");
            i++;
        }
    %>
</div>
</body>
</html>
