<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/common.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/signup.css" />
</head>
<body>
	<div class='header'>
		<div class='law-header'>律师咨询平台</div>
	</div>
	<div class='wrapper'>
		<div class='signup-header'>注&nbsp册</div>
		<div class='username'>
			账&nbsp号&nbsp<input type='text' id='name' />
		</div>
		<div class='password'>
			密&nbsp码&nbsp<input type='password' id='pass' />
		</div>
		<div style='display: none' id='error-message'></div>
		<button id='btn-signup'>注册</button>
	</div>
	<div class='footer'></div>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/signup.js"></script>
</body>
</html>