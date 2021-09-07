<%@tag description="Template tag for consultapp" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" fragment="true" %>
<!doctype html>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="/css/font-main.css" rel="stylesheet" id="template-css">
    <jsp:invoke fragment="title"/>
    <link href="/css/template.css" rel="stylesheet" id="template-css">

</head>
<body>
<div class="container">
    <%-- меню --%>
    <nav class="nav h5">
        <c:if test="${is_mentor == true}">
            <a class="nav-link" href="${pageContext.request.contextPath}/settings/view">Настройки</a>
        </c:if>
        <c:if test="${login == null}">
            <a class="nav-link" href="login">Войти</a>
        </c:if>
        <c:if test="${login!=null}">
            <c:if test="${name !=null}">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/view?login=${login}">${name}</a>
            </c:if>
            <c:if test="${name == null || name.length()==0}">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/view?login=${login}">${login}</a>
            </c:if>
            <a class="nav-link" href=${pageContext.request.contextPath}/consults/my>Мои консультации</a>
            <a class="nav-link" href=${pageContext.request.contextPath}/mentors?mode=consults>Запись на консультацию</a>
            <a class="nav-link" href=${pageContext.request.contextPath}/mentors?mode=schedule>Расписание</a>
        </c:if>

    </nav>
    <br>
</div>
<div class="main-container">
<jsp:doBody/>
</div>
</body>
</html>