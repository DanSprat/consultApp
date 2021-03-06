<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>
  <jsp:attribute name="title">
    <title>Редактирование профиля</title>
     <link type="text/css" href="/css/user-edit.css" rel="stylesheet" id=user-edit-css">
    <style><%@include file="/css/user-edit.css"%></style>
    <link rel="stylesheet" href="https://unpkg.com/jcrop/dist/jcrop.css">
  </jsp:attribute>

  <jsp:body>
    <p class="h1 ml-5 text-secondary">
      Редактирование профиля
    </p>

    <div class="container_info" >
      <form action="/user/save"  method="post" enctype="multipart/form-data">
        <div class="form-group row elem">
          <div class="col-md-3  ">
            Имя и Фамилия
          </div>
          <div class="col-md-3">
            <input type="text" name="name"  class="form-control" value="${user.name}" >
          </div>
        </div>

        <div class="form-group row elem">
          <div class="col-md-3">
            Адрес электронной почты
          </div>
          <div class="col-md-3">
            <input type="text" name="email"  class="form-control" value ="${user.email}" >
          </div>
        </div>

        <div class="form-group row elem">
          <div class="col-md-3">
            Аккаунт Progwards
          </div>
          <div class="col-md-3">

            <input type="text" name="link"  class="form-control" value ="${user.progwardsAccountLink}" >
          </div>
        </div>

        <div class="form-group row elem">
          <div class="col-md-3">
            Ник в Discord
          </div>
          <div class="col-md-3">
            <input type="text" name="discord"  class="form-control" value ="${user.discordName}" >
          </div>
        </div>
        <div class="form-group row elem photo">
          <div class="col-md-3">
            <label for="file">Загрузить фото</label>
            <input type="file" name="file" class="form-control-file" id="file">
          </div>
        </div>
        <div class="form-group row elem">
          <div class="col-md-3">
            <input type="text" name="mode" value="edit" hidden>
            <input type="submit" class="btn btn-primary mt-2" value="Обновить">
          </div>
        </div>
      </form>
    </div>
  </jsp:body>

</t:template>
