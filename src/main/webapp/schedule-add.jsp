<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:attribute name="title">
        <title>Добавление элемента в расписание</title>
        <style><%@include file="/css/schedule-add.css"%></style>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Добавление элемента в расписание
        </p>
        <div class="add-container">
            <form action="/schedule/save" method="post">
                <div class="row">
                    <div class="form-group col-2">
                        <label for="mentor">Наставник</label>
                        <c:if test="${mentor != null}">
                            <input type="text" class="form-control" name ="mentor" id="mentor" value="${mentor}" readonly>
                        </c:if>

                        <c:if test="${mentor == null}">
                            <input type="text" class="form-control" name = "mentor" id="mentor" >
                        </c:if>
                    </div>
                    <div class="form-group  col-2">
                        <label for="exampleFormControlSelect1">День недели</label>
                        <select name = "dayofweek" class="form-control" id="exampleFormControlSelect1">
                            <option value="1">Понедельник</option>
                            <option value="2">Вторник</option>
                            <option value="3">Среда</option>
                            <option value="4">Четверг</option>
                            <option value="5">Пятница</option>
                            <option value="6">Пятница</option>
                            <option value="0">Воскресенье</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-2">
                        <label for="time">Время</label>
                        <input type="time"  id ="time" class="form-control" name="time" placeholder="Время" min="0" required>
                    </div>

                    <div class="form-group col-2">
                        <label for="duration">Длительность </label>
                        <input type="number"  id ="duration" class="form-control" name="duration"  placeholder="${duration}" min="0" required>
                    </div>
                </div>
                <input type="submit" value="Добавить" class="btn btn-primary">
            </form>
        </div>


    </jsp:body>
</t:template>
