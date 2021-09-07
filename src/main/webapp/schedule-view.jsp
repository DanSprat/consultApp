<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:attribute name="title">
        <title>Расписание</title>
        <link href="/css/schedule-view.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Расписание
        </p>
        <div class="schedule-container">
            <c:if test="${is_mentor == true}">
            <div class= "float-right text-center">
                <form action="/schedule/add" method="get">
                    <input type="text" name = "mentor" value="${mentor}" hidden >
                    <button type="submit" class="btn btn-success">
                        Добавить
                    </button>
                </form>
            </div>
            </c:if>
            <table class="table ">
                <c:forEach var="entry" items="${schedule}">
                    <jsp:useBean id="timeStart" class="date.DateFormat"></jsp:useBean>
                    <jsp:useBean id="timeFinish" class="date.DateFormat"></jsp:useBean>
                    <tr>
                        <td class="main-td">${entry.key}</td>
                        <td >
                            <div class="day-of-week">
                            <c:forEach var ="schedule" items="${entry.value}">
                                <c:set target="${timeStart}" property="date" value="${schedule.start}"/>
                                <c:set target="${timeFinish}" property="date" value="${schedule.start+schedule.duration}"/>
                                <a href="/schedule/edit?mentor=${mentor}&day_of_week=${schedule.day_of_week}&start=${schedule.start}" class="time">${timeStart.localeTime} -  ${timeFinish.localeTime}</a>
                            </c:forEach>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
</t:template>