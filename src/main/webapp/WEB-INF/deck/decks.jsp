<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dao.interfaces.DeckWrapperInterface" %>
<%@ page import="dao.implementation.DeckWrapperDAO" %>
<%@ page import="model.Deck" %>
<%@ page import="model.Card" %><%--
  Created by IntelliJ IDEA.
  User: giorgi
  Date: 02/08/2021
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    DeckWrapperInterface deckDao = (DeckWrapperInterface) request.getServletContext().getAttribute(DeckWrapperDAO.DECK_WRAPPER_ATTR);
    int userId = Integer.parseInt(request.getParameter("id"));
    int userDeckId = deckDao.getUserDeckId(userId);
%>
<html>
<head>
    <title>Choose Deck</title>
    <!--    jQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/decks.css"/>">
    <script src="<c:url value="/javascript/deck.js"/>" type="text/javascript"></script>
</head>
<body>
    <a href="home">
        <button class="return_link" id="returnBtnId"><img src="<c:url value="/resources/home.svg"/>" class="home_svg" alt="error"></button>
    </a>
    <div class="bar">
        <h1 class="heading" id="heading">Choose a deck (current: <%=(userDeckId == -1) ? "None" : deckDao.getDeck(userDeckId).getName()%>)</h1>
    </div>
    <div class="decks">
        <ul class="deckList">
            <% for (Deck deck : deckDao.getAllDecks()) { %>
            <li class="deck" id="<%=deck.getDeckId()%>">
                <h2 class="deckName"><%=deck.getName()%></h2>
                <img src="<%="/resources/" + deck.getImage()%>" class="deckImage" alt="Deck">
                <div class="cards">
                    <ul class="cardList">
                        <% for (Integer cardId : deckDao.getDeckCardIds(deck.getDeckId())) { %>
                        <li class="card" id="<%=cardId%>">
                            <img src="<%="/resources/" + deckDao.getCardById(cardId).getImage()%>" class="cardImage" alt="Card">
                        </li>
                        <% } %>
                    </ul>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
    <input type="hidden" id="userId" name="userId" value="<%=userId%>">
</body>
</html>
