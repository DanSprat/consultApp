<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:attribute name="title">
        <title>Выбор консультации</title>
        <style><%@include file="/css/consultations-choose.css"%></style>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
           Выбор консультации
        </p>
        <div class="container-consultations">
            <div class="mentor">
                <c:set var="image" value="images/NO_PHOTO.jpg" scope="page"/>
                <c:if test="${mentor.image != null && mentor.image.length()>0}">
                    <c:set var="image" value="${mentor.image}" scope="page"/>
                </c:if>
                <img src="${image}" class="mentor-image">
                <div class="mentor-name">
                    <c:out value="${mentor.name}"/>
                </div>
            </div>
        <c:forEach var="day" items="${consultationsList}">
            <c:set var="list" value="${day.value}" target=""/>
            <div class="date">
                <c:out value="${day.key}"/>
            </div>
            <div class="choose-container">
                <div class="border-time">
            <c:forEach var="consultation" items="${list}">
                <div class="time">
                    <jsp:useBean id="ZDTdate" class="date.DateFormat"></jsp:useBean>
                    <c:set target="${ZDTdate}" property="date" value="${consultation.start}"/>
                    <c:if test="${consultation.student == null}">
                    <form action="/consults/select" method="get">
                        <input type="text" name ="mentor" value="${consultation.mentor}" hidden/>
                        <input type="text" name ="time" value="${consultation.start}" hidden/>
                        <input type="submit" class="butt" value="${ZDTdate.time}" />
                    </form>
                    </c:if>
                    <c:if test="${consultation.student != null}">
                        <form action="/" method="get">
                            <input type="button" class="butt" value="${ZDTdate.time}" disabled/>
                        </form>
                    </c:if>

                </div>
            </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div id="text"><div>
        </div>
        <script src="/JS/AjaxCheckStudent.js"></script>
        <script src="/JS/choose-time.js"></script>
    </jsp:body>

</t:template>