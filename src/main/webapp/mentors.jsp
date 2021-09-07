<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
    <jsp:attribute name="title">
        <title>Выбор наставника</title>
          <link href="/css/mentors.css" rel="stylesheet" id=user-view-css">
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Выбор ментора
        </p>
        <c:set var="image" value="images/unnamed.jpg" scope="page"/>
        <c:if test="${elem.image != null && elem.image.length()>0}">
            <c:set var="image" value="${elem.image}" scope="page"/>
        </c:if>
        <div class="mentors-container">
        <c:forEach var="elem" items="${mentors}">
            <c:set var="image" value="images/unnamed.jpg" scope="page"/>
            <c:if test="${elem.image != null && elem.image.length()>0}">
                <c:set var="image" value="${elem.image}" scope="page"/>
            </c:if>

            <div class="mentor">
                <div>
                    <img class="img-photo" src="${image}">
                </div>
                <div class="name">
                    <c:out value="${elem.name}"></c:out>
                </div>
                <form method="get" action="/${mode}/view">
                   <input type="text" name="mentor" value="${elem.login}" hidden >
                    <input type="submit" class="btn btn-primary mt-2" value="Выбрать">
                </form>

            </div>
        </c:forEach>
        </div>
    </jsp:body>
</t:template>
