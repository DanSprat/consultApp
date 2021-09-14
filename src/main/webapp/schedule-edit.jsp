<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:attribute name="title">
        <title>Редактирование элемента расписания</title>
        <style><%@include file="/css/schedule-edit.css"%></style>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
           Редактирование элемента расписания
        </p>
        <div class="schedule-container">
            <form action="/schedule/save" method="post">
                <div class="row">
                    <div class="form-group col-2">
                        <label for="mentor">Наставник</label>
                        <input type="text" class="form-control" id="mentor" name = "mentor" value="${schedule.mentor}" placeholder="${schedule.mentor}">
                    </div>
                    <div class="form-group  col-2">
                        <label for="exampleFormControlSelect1">День недели</label>
                        <select name = "dayofweek" class="form-control" value="${schedule.day_of_week}"  id="exampleFormControlSelect1">
                            <option value="1" <c:if test="${schedule.day_of_week == 1}">selected="selected"</c:if>>Понедельник</option>
                            <option value="2" <c:if test="${schedule.day_of_week == 2}">selected="selected"</c:if>>Вторник</option>
                            <option value="3" <c:if test="${schedule.day_of_week == 3}">selected="selected"</c:if>>Среда</option>
                            <option value="4"<c:if test="${schedule.day_of_week == 4}">selected="selected"</c:if>>Четверг</option>
                            <option value="5" <c:if test="${schedule.day_of_week == 5}">selected="selected"</c:if>>Пятница</option>
                            <option value="6"<c:if test="${schedule.day_of_week == 6}">selected="selected"</c:if>>Пятница</option>
                            <option value="0"<c:if test="${schedule.day_of_week == 0}">selected="selected"</c:if>>Воскресенье</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-2">
                        <label for="time">Время</label>
                        <input type="text" name="start" value="${schedule.start}" hidden>
                        <input type="time"  id ="time" class="form-control" name="time" value="${time}" required>
                    </div>

                    <div class="form-group col-2">
                        <label for="duration">Длительность </label>
                        <input type="number"  id ="duration" class="form-control" name="duration"  min="0" value="${duration}" placeholder="${schedule.duration}" required>
                    </div>
                </div>
                <input type="text" name="mode" value="edit" hidden>
                <input type="submit" value="Обновить" class="btn btn-primary">

                <input type="submit" value="Удалить" class="btn btn-danger" formaction="/schedule/delete">

            </form>


        </div>
    </jsp:body>
</t:template>