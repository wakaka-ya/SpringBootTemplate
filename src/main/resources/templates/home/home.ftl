<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>layer</title>
<link href="/static/layui/css/layui.css" rel="stylesheet" />
<script src="/static/js/jquery-1.11.3.js"></script>
<script src="/static/layui/layui.js"></script>
<style>
body {
	margin: 10px;
}

.demo-carousel {
	height: 200px;
	line-height: 200px;
	text-align: center;
}
</style>

<style type="text/css">
#main {
	text-align: center;
	line-height: 90vh;
}
</style>

</head>
<body>
	<div class="layui-tab-content">

		<div id="main">
			<h4 style="font-size: 26px;">你好，${(user.loginName)!'wakaka'}</h4>
		</div>

	</div>
</body>
</html>
