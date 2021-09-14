<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        <title>Настройки</title>
      <style><%@include file="/css/edit.css"%></style>
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Настройки: добавление
        </p>

        <div class="text-center">
            <form method="post" action="${pageContext.request.contextPath}/settings/save">
                <div class="form-group">
                    <label>
                        Название параметра
                        <input type="text" class="second" name="name" value="${param.name}" >
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        Значение параметра
                        <input type="text" class="third" name="value" value="${param.value}">
                    </label>
                </div>

                    <%-- Элемент для определения редактирования из ru.progwards.advanced.business.SettingsSave --%>
                <input type="text" name="edit" value="true" hidden>
                <input type="submit" class="btn btn-primary" value="Сохранить">
            </form>
        </div>
    </jsp:body>
</t:template>