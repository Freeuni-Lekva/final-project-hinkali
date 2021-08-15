<%@ page import="commons.beans.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: lobja
  Date: 8/14/2021
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>match over</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gameOver.css">
</head>
<body>
<div class="result">
    <%
        int id = (Integer) request.getSession().getAttribute(UserBean.USER_ATTR);

        if(Integer.parseInt(request.getParameter("winner")) == id){
            out.print("<h1 class = \"victory\">You Win</h1>");
        }
        if(Integer.parseInt(request.getParameter("loser")) == id){
            out.print("<h1 class = \"loss\">You Lose</h1>");
        }
        if(Integer.parseInt(request.getParameter("draw")) == id){
            out.print("<h1 class = \"draw\">Draw</h1>");
        }
    %>
</div>
<div class="home_button">
    <form method="post">
        <input type="submit" value="Home" class = "button">
    </form>
</div>
</body>
</html>
