<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="title">
        <title>Настройки</title>
        <link href="${pageContext.request.contextPath}/css/settings-view.css" rel="stylesheet" id="consult-app-css">
        <link href="${pageContext.request.contextPath}/css/test.css" rel="stylesheet" id="consult-app-css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Настройки
        </p>
        <div class= "container_button">
        <div class= "float-right text-center">
            <form>
            <button type="submit" class="btn btn-success" formaction="/settings/add">
                Добавить
            </button>
            </form>
        </div>
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
                        <div class="block">
                            <form action='${pageContext.request.contextPath}/settings/delete' onsubmit="return confirm('Вы уверены?');" method='post'>
                                <input class='btn-del' type='text' name="${elem.name}" value='' hidden />
                                <button type="submit" class="btn btn-outline-danger" name='${elem.name}' value='' ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                </svg>Удалить</button>
                            </form>
                        </div>
                        <div class="block">
                        <form action="${pageContext.request.contextPath}/settings/edit" method='post'>
                            <input class='btn-edit' type='text' name='name' value='${elem.name}' hidden />
                            <input class='btn-edit' type='text' name='value' value='${elem.value}' hidden />
                            <button type="submit" class = "btn btn-primary" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg> Изменить</button>
                        </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:template>