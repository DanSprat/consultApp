<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <jsp:attribute name="title">
        <title>Информация о пользователе</title>
        <link href="/css/user-view.css" rel="stylesheet" id=user-view-css">
        <style><%@include file="/css/user-view.css"%></style>
    </jsp:attribute>

    <jsp:body>
        <p class="h1 ml-5 text-secondary">
            Информация о пользователе
        </p>
        <div class="info_container">
            <c:set var="image" value="/images/NO_PHOTO.jpg" scope="page"/>
            <c:if test="${user.image != null && user.image.length()>0}">
                <c:set var="image" value="${user.image}" scope="page"/>
            </c:if>
            <img src="${image}" class="user-image">
            <div class ="info">
               <div class="elem col-md-3">
                   <div class="main">Логин</div>
                   ${user.login}
               </div>
                <c:if test="${user.name!=null && user.name.length()>0}">
                <div class="elem col-md-3">
                    <div class="main">Имя</div>
                        ${user.name}
                </div>
                </c:if>
                <c:if test="${user.email != null && user.email.length()>0}">
                    <div class="elem col-md-3">
                        <div class="main">Email</div>
                            ${user.email}
                    </div>
                </c:if>

                <c:if test="${user.progwardsAccountLink!=null &&  user.progwardsAccountLink.length()>0}">
                    <div class="elem col-md-3">
                        <div class="main">Progwards Link</div>
                        <a href="${user.progwardsAccountLink}">${user.progwardsAccountLink}</a>
                    </div>
                </c:if>

                <c:if test="${user.discordName !=null && user.discordName.length()>0}">
                    <div class="elem col-md-3">
                        <div class="main">Discord</div>
                            ${user.discordName}
                    </div>
                </c:if>
            </div>
            <c:if test="${my == true}">
            <div class ="buttons">
            <div class="edit-btn">
                <a href="/user/edit?login=${login}">Редактировать профиль</a>
            </div>
                <div class="change-pwd">
                    <a href="/user/chgpwd?login=${login}">Изменить пароль</a>
                </div>
            </div>
            </c:if>
        </div>
    </jsp:body>
</t:template>
