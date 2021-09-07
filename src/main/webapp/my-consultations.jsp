<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
    <jsp:attribute name="title">
        <title>Мои консультации</title>
        <link href="/css/my-consults.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Мои консультации
        </p>
        <div class="consults-container">
            <div class="width">
                <c:forEach var="consultation" items="${consultations}">
                    <jsp:useBean id="ZDTdate" class="date.DateFormat"></jsp:useBean>
                    <c:set target="${ZDTdate}" property="date" value="${consultation.start}"/>

                    <jsp:useBean id="DateTime" class="date.SiteDate"></jsp:useBean>
                    <c:set target="${DateTime}" property="date" value="${consultation.start}"/>

                   <jsp:useBean id="Duration" class="date.DurationMinutes"></jsp:useBean>
                    <c:set target="${Duration}" property="millis" value="${consultation.duration}"></c:set>
                   <a  href="/consults/my?mentor=${consultation.mentor}&start=${consultation.start}" class="consultation">
                       <div class="date">
                           <c:out value="${DateTime}"/>
                       </div>
                       <div class="line">
                           <div class="gray">
                               Время:
                           </div>
                           <div class="default">
                               <c:out value="${ZDTdate.time}"/>
                           </div>
                       </div>

                       <div class="line">
                           <div class="gray">
                               Продолжительность:
                           </div>
                           <div class="default">
                               <c:out value="${Duration.duration} минут" />
                           </div>
                       </div>
                       <div class="line">
                           <div class="gray">
                               Ментор:
                           </div>
                           <div class="default">
                               <c:out value="${consultation.mentor_name} " />
                           </div>
                       </div>

                   </a>
                </c:forEach>
            </div>
        </div>
        <script src="/JS/my-consultations.js"></script>
    </jsp:body>
</t:template>