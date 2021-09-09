<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="css/register.css" rel="stylesheet" id = "register-css">
<head>
    <title>Register</title>
</head>

<body>
<div class="login-reg-panel">
  <div class="login-info-box">
    <h2>У вас есть аккаунт?</h2>
    <p>Войдите прямо сейчас</p>
    <label id="label-register" for="log-reg-show">Login</label>
    <input type="radio" name="active-log-panel" id="log-reg-show"  checked="checked">
  </div>

  <div class="register-info-box">
    <h2>Еще не зарегестрированы?</h2>
    <p>Пора это исправить</p>
    <label id="label-login" for="log-login-show">Register</label>
    <input type="radio" name="active-log-panel" id="log-login-show">
  </div>

  <div class="white-panel">
    <div class="login-show">
      <h2>LOGIN</h2>
      <form id ="log-in-from" action="/auth" method="post">
        <input type="text" id ="login-login"  name="login" placeholder="Email">
        <input type="password" id = "login-password" name ="password" placeholder="Password">
        <input class="login-btn" type="submit" value="Login">
        <a href="">Forgot password?</a>
      </form>
    </div>
    <div class="register-show">
      <h2>REGISTER</h2>
      <form action="/user/save" method="post">
        <input type="text"  name="mode" value="add" hidden>
        <input type="text" id = "register-login" name="login" placeholder="Email">
        <input type="password" id = "register-password" name="password" placeholder="Password">
        <div id ="indicator"></div>
        <div id ="indicator_text"></div>
        <input type="password" id = "register-confirm" name ="confirmPassword" placeholder="Confirm Password">
        <div class="under-submit"></div>
        <input type="submit" id = "register_submit" value="Register">
      </form>
    </div>
  </div>
</div>
</body>
<script  src="/JS/register.js" charset="UTF-8"></script>
<script type="text/javascript" src="/JS/password.js" charset="utf-8"></script>
</html>
