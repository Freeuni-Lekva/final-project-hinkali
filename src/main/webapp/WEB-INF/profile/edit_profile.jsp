<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dao.interfaces.UserWrapperInterface" %>
<%@ page import="dao.implementation.UserWrapperDao" %>
<%@ page import="commons.beans.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: giorgi
  Date: 10/07/2021
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    UserWrapperInterface wrapper = (UserWrapperInterface) request.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
    UserBean user = wrapper.getUserById(Integer.parseInt(request.getParameter("id")));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <!--    CSS-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <!--    jQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/edit_profile.css"/>">
    <script src="<c:url value="/javascript/edit_profile.js"/>" type="text/javascript"></script>
</head>
<body>
    <a href="profile">
        <button class="return_link" id="returnBtnId"><img src="<c:url value="/resources/back.svg"/>" class="back_svg" alt="error"></button>
    </a>
    <div class="form_container">
        <h1 class="edit_label">Edit Profile</h1>
        <p id="info" style="font-style: italic">Change desired fields and click update</p>
        <form id="edit_form" name="edit_form">
            <div class="fields">
                <label for="userField">Username: </label>
                <input type="text" id="userField" class="user_field" name="username" required pattern="[A-Za-z0-9]{3,20}" value="<%=user.getUsername()%>"
                       title="Username must only contain lowercase and/or uppercase letters and numbers, length 3-20"><br>
                <label for="oldPasswordField">Old Password: </label>
                <input class="password_field" type="password" id="oldPasswordField" name="oldPassword"><br>
                <label for="newPasswordField">New Password: </label>
                <input class="password_field" type="password" id="newPasswordField" name="newPassword" pattern=".{5,60}"
                       title="Password length must be at least 5, at most 60"><br>
                <label for="nameField">Name: </label>
                <input type="text" id="nameField" name="name" value="<%=user.getName()%>" pattern="[A-Za-z]{0,30}"
                       title="(Optional) Name must contain letters only, at most 30 characters long"><br>
                <label for="surnameField">Surname: </label>
                <input type="text" id="surnameField" name="surname" value="<%=user.getSurname()%>" pattern="[A-Za-z]{0,30}"
                       title="(Optional) Surname must contain letters only, at most 30 characters long"><br>
            </div>
            <label for="dateField" class="date_label">Birthday</label><br>
            <input type="date" class="date" id="dateField" name="birthday" min="1920-01-01" max="2016-01-01" value="<%=user.getBirthday()%>"><br><br>
            <button type="submit" class="updatebtn" id="update">Update</button><br>
            <input type="hidden" id="userId" name="userId" value="<%=user.getId()%>">
        </form>
    </div>
</body>
</html>