<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        <style><%@include file="/css/consultation-view.css"%></style>
        <title>Информация о консультации</title>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Информация о консультации
        </p>
        <div class="consultation-select-container">
            <form method="post" onsubmit= "return confirm('Вы дейтвительно хотите отменить запись?');" action="/consults/cancel">
                <div class="row">
                    <div class="form-group col-2">
                        <label for="mentor">Наставник</label>
                        <input type="email" class="form-control" id="mentor" value="${mentor}" disabled>
                    </div>
                    <div class="form-group  col-2">
                        <label for="student">Студент</label>
                        <input type="email" class="form-control" id="student" value="${student}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group  col-2">
                        <label for="datetime">Дата и время начала</label>
                        <input type="email" class="form-control" id="datetime" value="${datetime}" disabled>
                    </div>
                    <div class="form-group  col-2">
                        <label for="duration">Длительность</label>
                        <input type="email" class="form-control" id="duration" value="${duration}" disabled>
                    </div>
                </div>
                <div class="form-group comment ">
                    <label for="comment">Комментарий</label>
                    <textarea name="comment" class="form-control" id="comment" placeholder="${comment}"  disabled></textarea>
                </div>
                <input type="text" name ="mentor_login" value="${mentor_login}" hidden>
                <input type="text" name ="start" value="${start}" hidden>
                <input type="submit" value="Отменить" class="btn btn-danger">
            </form>
        </div>
        <script src="/JS/IsAlreadyDeleted.js"></script>
    </jsp:body>
</t:template>


