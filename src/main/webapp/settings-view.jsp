<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        <title>Настройки</title>
        <link href="${pageContext.request.contextPath}/css/settings-view.css" rel="stylesheet" id="consult-app-css">
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Настройки

        </p>
        <div class="float-right text-center">
            <a href="settings-add.jsp"><img class="mb-3 w-50" src="${pageContext.request.contextPath}/images/add-settings.png" alt="Добавить"></a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Значение</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="elem" items="${settings}">
                <tr>
                    <td><c:out value="${elem.name}"/></td>
                    <td><c:out value="${elem.value}"/></td>
                    <td>
                        <form action='settings-edit.jsp' method='post'>
                            <input class='btn-edit' type='text' name='name' value='${elem.name}' hidden />
                            <input class='btn-edit' type='text' name='value' value='${elem.value}' hidden />
                            <input class='btn-edit' type='submit' value=''/>
                        </form>
                        <form action='settings-delete' onsubmit="return confirm('Вы уверены?');" method='post'>
                            <input class='btn-del' type='submit' name='${elem.name}' value=''/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:template>