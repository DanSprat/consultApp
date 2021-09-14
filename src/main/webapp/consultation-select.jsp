<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
         <style><%@include file="/css/consultation-select.css"%></style>
        <title>Запись на консультацию</title>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Запись на консультацию
        </p>
        <div class="consultation-select-container">
            <form method="post" action="/consults/select">
                <div class="row">
                    <div class="form-group col-2">
                        <label for="mentor">Наставник</label>
                        <input type="email" class="form-control" id="mentor" value="${mentor.name}" disabled>
                    </div>
                    <div class="form-group  col-2">
                        <label for="student">Студент</label>
                        <input type="email" class="form-control" id="student" value="${student.name}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group  col-2">
                        <label for="datetime">Дата и время начала</label>
                        <input type="email" class="form-control" id="datetime" value="${dtime}" disabled>
                    </div>
                    <div class="form-group  col-2">
                        <label for="duration">Длительность</label>
                        <input type="email" class="form-control" id="duration" value="${duration}" disabled>
                    </div>
                </div>
                    <div class="form-group comment ">
                        <label for="comment">Комментарий</label>
                        <textarea name="comment" class="form-control" id="comment" rows="3"></textarea>
                        <input type="text"  name="mentor" value="${mentor.login}" hidden>
                        <input type="text"  name="start" value="${consultation.start}" hidden>
                    </div>
                <input type="submit" value="Записаться" class="btn btn-primary">
            </form>
        </div>
        <script src="/JS/AjaxCheckStudent.js"></script>
    </jsp:body>
</t:template>


