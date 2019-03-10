<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>律师咨询平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/common.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/index.css" />
</head>
<body>
	<div class='header' id='header'></div>
	<div class='wrapper'>
		<div class='cs-box'>
			<!-- 显示在线客服 -->
			<div id='cs-info'>
				<div class='onlinecs'>在线客服</div>
				<ul id='cs-list'></ul>
			</div>
			<!-- 显示来信用户 -->
			<div id='user-comein' style='display: none'>
				<div class='comeinmes'>来信</div>
				<ul id='user-list'></ul>
			</div>
		</div>
		<div class='text-box'>
			<!-- 聊天框 -->
			<div id='text-info'>
				<div class='whochat'>当前没有对话</div>
			</div>
		</div>
		<div class='input-box'>
			<!-- 输入框 -->
			<div id='input-text'>
				<div id="info" contenteditable="true"></div>
				<button id="btn" class="btn btn-sm btn-default">表情</button>
				<button id='btn-submit'>发送</button>
				<div class="emoji-cont" style="display: none;"></div>
			</div>
		</div>
	</div>
	<div class='footer'></div>
	<script>
    let name='<%=session.getAttribute("name")%>';
    let type='<%=session.getAttribute("type")%>';
	</script>
	<script src="./js/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="./js/emoji-list-with-image.js"></script>
	<script src="./js/emoji.js"></script>
	<script src="./js/punycode.js"></script>
	<script src="./js/punycode.min.js"></script>
	<script src="./js/index-new.js"></script>

</body>
</html>