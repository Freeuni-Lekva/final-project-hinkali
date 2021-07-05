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
    <div "inputs">
        <form method="get">
            <label>
                <input type="text" name="search_field" placeholder="Search..." class="searchField">
            </label>
            <input type="submit" name="search_button" value="Go" class="searchBtn">
        </form>
    </div>

        <% if(!results.getResultList().isEmpty()){ %>
            <label class="numFound">
                Found <%= results.getResultList().size() %> user(s)
            </label>
        <% } %>

    <ul class="resultList">
        <% for (UserBean user : results.getResultList()) { %>
        <li class="user">
            <label>Username: <%= user.getUsername()%>, Name: <%= user.getName() %>, Surname:
                <%= user.getSurname() %></label>
        </li>
        <% } %>
    </ul>
    <a href="/home" class="Return"> Return</a>
</body>

</html>