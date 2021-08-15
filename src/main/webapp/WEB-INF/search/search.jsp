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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css?bandaid=1">
    <script  src="${pageContext.request.contextPath}/javascript/search.js"></script>
</head>

<body>
    <div class="topbar">
        <form method="get" class="search">
            <input type="text" name="search_field" placeholder="Who are you looking for?" class="searchField"
                maxlength="32" autocomplete="off">
            <button type="submit" name="search_button" class="searchBtn">Search</button>
        </form>
    </div>
    <button class="return_link" id="returnBtnId">
            <img src="${pageContext.request.contextPath}/resources/back.svg" class="home_svg" alt="error">
    </button>
    <div class="wrap">

        <% if(! (Boolean) request.getAttribute("isFirstRequest")){ %>
        <% if(request.getAttribute("isInvalidQuery") == null){ %>

        <label class="info">
            Found <%= results.getResultList().size() %> user(s)
        </label>

        <div class="resultOutput">

            <% if(!results.getResultList().isEmpty() || request.getParameter("search_button")
                    != null){ %>
            <% } %>

            <ul class="resultList">
                <% for (UserBean user : results.getResultList()) { %>
                <li class="user" id="<%=user.getId()%>">
                    <img src="${pageContext.request.contextPath}/resources/user-svgrepo-com.svg" class="svg" alt="error">
                    <div class="user_link_wrapper">
                        <label class="username_label">
                            <%= user.getUsername()%>
                        </label>
                        <br>
                        <label class="name_label">
                            <%= user.getName() %> <%= user.getSurname() %>
                        </label>
                    </div>
                </li>
            </ul>
            <% } %>
        </div>
        <% } else {%>
        <label class="info">
            Please enter at least <%= SearchServlet.MIN_QUERY_LENGTH %> characters
        </label>
        <% }%>
    </div>

    <% } %>

</body>

</html>