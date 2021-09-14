<%--
  Created by IntelliJ IDEA.
  User: popov
  Date: 10.09.2021
  Time: 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="/css/font-main.css" rel="stylesheet" id="main-css">
  <link href="css/mentors.css" rel="stylesheet" id=mentors-css">
    <title>Title</title>
</head>
<body>
  <p class="h1 ml-5 text-secondary">
    Выбор ментора
  </p>
  <div class="mentors-container">
      <div class="mentor">
        <div>
          <img class="img-photo" src="${image}">
        </div>
        <div class="name">
        </div>
        <form method="get" action="/${mode}/view">
          <input type="text" name="mentor" value="${elem.login}" hidden >
          <input type="submit" class="btn btn-primary mt-2" value="Выбрать">
        </form>

      </div>
  </div>
</body>
</html>
