<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
     <jsp:attribute name="title">
    <title>Редактирование профиля</title>
         <link href="/css/change-pwd.css" rel="stylesheet" id=user-view-css">
  </jsp:attribute>
    <jsp:body>
        <p class="h1 ml-5 text-secondary">
          Смена пароля
        </p>
        <div class="container-change">
            <form action="/user/save"  method="post">
                <div class="form-group row elem">
                    <div class="col-md-3  ">
                        Новый пароль
                    </div>
                    <div class="col-md-3">
                        <input type="password" name="password"  class="form-control" placeholder="Введите новый пароль" >
                    </div>
                </div>

                <div class="form-group row elem">
                    <div class="col-md-3">
                        Повтор пароля
                    </div>
                    <div class="col-md-3">
                        <input type="password" name="samePassword"  class="form-control" placeholder="Повторите пароль" >
                    </div>
                </div>

                <div class="form-group row elem">
                    <div class="col-md-3">
                        <input type="text" name="mode" value="changePWD" hidden>
                        <input type="text" name="login" value="${login}" hidden>
                        <input type="submit" class="btn btn-primary mt-2" value="Обновить">
                    </div>
                </div>

            </form>
        </div>

        <div>
    </jsp:body>
</t:template>
