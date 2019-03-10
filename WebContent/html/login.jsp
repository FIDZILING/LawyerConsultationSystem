<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/common.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/login.css" />
</head>
<body>
	<div class='header'>
		<div class='law-header'>律师咨询平台</div>
	</div>
	<div class='wrapper'>
		<div class='login-header'>登&nbsp录</div>
		<div class='login-type'>
			身份 <input class='input-class' type='radio' id='用户' name='type' value='用户' checked />
			<label for="用户">用&nbsp户</label> 
			<input class='input-class' type='radio' id='客服' name='type' value='客服' />
			<label for="客服">客&nbsp服</label>
		</div>
		<div class='username'>
			账&nbsp号&nbsp <input type='text' id='name' />
		</div>
		<div class='password'>
			密&nbsp码&nbsp <input type='password' id='pass' />
		</div>
		<div id='error-message'></div>
		<button id='btn-login'>登&nbsp&nbsp录</button>
		<br> <a href='signup.jsp' class='signup'>没有账号？注册一个！</a> <br>
	</div>
	<div class='footer'></div>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/login.js"></script>
</body>
</html>