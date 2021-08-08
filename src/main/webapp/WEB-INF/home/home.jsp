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
<%@ page isELIgnored="false" %>
<%
    UserWrapperDao dao = (UserWrapperDao) request.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
    ArrayList<StatsBean> stats = dao.getStatsWithDescendingPoints();
%>
<!Doctype html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<div class="buttons">
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.PLAY_BUTTON_ID%>>
        <input type="submit" value="Play" class="play_button">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.INVITE_BUTTON_ID%>>
        <input type="submit" value="Invite Friend" class="just_button">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.PROFILE_BUTTON_ID%>>
        <input type="submit" value="Profile" class="just_button">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.SEARCH_BUTTON_ID%>>
        <input type="submit" value="Search" class="just_button">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.DECKS_BUTTON_ID%>>
        <input type="submit" value="Decks" class="just_button">
    </form>
    <form method="post">
        <input name= "buttonID" type="hidden" value=<%= HomeServlet.LOG_OUT_BUTTON_ID%>>
        <input type="submit" value="Log out" class="quit_button">
    </form>
</div>

<div class = "leaderboard_head">
    <h1>Leaderboard</h1>
</div>

<div class="leaderboard">
    <table>

        <tr>
            <th>Name</th>
            <th>Score</th>
            <th>Games Played</th>
            <th>Victories</th>
            <th>Draws</th>
            <th class="loss">Losses</th>
        </tr>

        <%
            int i = 0;
            while(true){
                if(i == stats.size())
                    break;
                if(i == stats.size() - 1){
                    StatsBean sb = stats.get(i);
                    out.print("<tr><td class = \"bottom_line\">" + dao.getUserById(sb.getUserid()).getUsername() + "</td>" +
                            "<td class = \"bottom_line\">" + sb.getPoints() + "</td>" +
                            "<td class = \"bottom_line\">" + sb.getGamesPlayed() + "</td>" +
                            "<td class = \"bottom_line\">" + sb.getWins() + "</td>" +
                            "<td class = \"bottom_line\">" + sb.getDraws() + "</td>" +
                            "<td class = \"bottom_line , loss\">" + sb.getLosses() + "</td>" +
                            "</tr>");
                }else {
                    StatsBean sb = stats.get(i);
                    out.print("<tr><td>" + dao.getUserById(sb.getUserid()).getUsername() + "</td>" +
                            "<td>" + sb.getPoints() + "</td>" +
                            "<td>" + sb.getGamesPlayed() + "</td>" +
                            "<td>" + sb.getWins() + "</td>" +
                            "<td>" + sb.getDraws() + "</td>" +
                            "<td class = \"loss\">" + sb.getLosses() + "</td>" +
                            "</tr>");
                }
                i++;
            }
        %>
    </table>
</div>
</body>
</html>
