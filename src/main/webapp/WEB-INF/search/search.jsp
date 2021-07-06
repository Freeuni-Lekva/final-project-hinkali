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
    <div class="wrap">
        <form method="get" class="search">
            <input type="text" name="search_field" placeholder="Who are you looking for?" class="searchField"
                maxlength="32">
            <button type="submit" name="search_button" class="searchBtn">Search</button>
        </form>

        <label class="info">
            Found <%= results.getResultList().size() %> user(s)
        </label>

        <div class="resultOutput">
            <% if(!results.getResultList().isEmpty() || request.getParameter("search_button")
                != null){ %>
            <% } %>

            <ul class="resultList">
                <% for (UserBean user : results.getResultList()) { %>
                <li class="user">
                    <img src="${pageContext.request.contextPath}/resources/user-svgrepo-com.svg" class="svg">
                    <a href="/home" class="user_link_wrapper">
                        <label class="username_label">
                            <%= user.getUsername()%>
                        </label>
                        <br>
                        <label class="name_label">
                            <%= user.getName() %> <%= user.getSurname() %>
                        </label>
                    </a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
    <div class="return_wrapper">
        <a href="/home" class="return">
            <img src="${pageContext.request.contextPath}/resources/back.svg" class="home_svg">
            <label class="home_label">
                Home
            </label>
        </a>

    </div>
</body>

</html>