<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        <title>Ошибка</title>
        <style><%@include file="/css/error.css"%></style>
    </jsp:attribute>
    <jsp:body>
        <div class="container-error" >
            <div class="message">
                ${message}
            </div>
            <form class ="error-button" action="${action}" method="get">
                <input type="submit" class="btn btn-primary" value="${name_button}">
            </form>
        </div>
    </jsp:body>

</t:template>
