<%@ page import="model.SearchResults" %>
<%@ page import="servlets.search.SearchServlet" %>
<%@ page import="commons.beans.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Wilson
  Date: 04-Jul-21
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<% SearchResults results = (SearchResults) request.getAttribute(SearchServlet.RESULTS_ATTRIBUTE); %>
<html>

<head>
    <title>Search Users</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css">
</head>

<body>
        <form method="get">
            <div "inputs">
                <label>
                    <input type="text" name="search_field" placeholder="Search..." class="searchField">
                </label>
                <input type="submit" name="search_button" value="Go" class="searchBtn">
            </div>
        </form>

        <ul class="resultList">
            <% for (UserBean user : results.getResultList()) { %>
            <li class="user">
                <p>
                    <strong>
                        <%=user.getUsername()%>
                    </strong>
                    <hr>
                    <i class="italics">
                    <%=user.getName()%>
                    <%=user.getSurname()%>
                    </i>
                </p>
            </li>
            <% } %>
        </ul>
    <a href="/home" class="Return"> Return</a>
</body>

</html>