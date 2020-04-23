<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${appName?if_exists}</title>
<link rel="stylesheet" href="/static/layui/css/layui.css">
<link rel="stylesheet" href="/static/lau/lau.css">
<script>
	(window.top === window.self)
			|| (window.top.location.href = window.self.location.href);
</script>
<style type="text/css">
/*#ul li dl {
            background-color: #000;
        }

        .layui-side {
            font-style: normal;
        }

        #ul a {
            
            font-weight: 500;
        }
        #ul cite {
            color: #d2d2d2 !important;
        }
        #ul li a:hover {
            color: #ffffff !important;
            font-weight: 500;
        }*/
</style>
</head>
<body class="layui-layout-body layui-unselect">
	<div class="layui-layout layui-layout-admin">
		<!--顶部导航开始-->
		<div class="layui-header" style="background-color: #20222a">
			<a class="layui-logo">${appName?if_exists}</a>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item" lay-unselect><a href="javascript:;">${user.loginName?if_exists}</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" onclick="ChangePass()">修改密码</a>
						</dd>
						<dd>
							<a href="/logout">安全退出</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item" lay-unselect><a lau-event="about">关于我们</a></li>
			</ul>
		</div>
		<!--顶部导航结束-->


		<!--侧边菜单开始-->
		<div class="layui-side" style="background-color: #20222a;">
			<div class="lau-side-fold">
				<i class="layui-icon layui-icon-shrink-right"></i>
			</div>
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" id="ul"
					style="background-color: #20222a">
				</ul>
			</div>
		</div>
		<!--侧边菜单结束-->


		<!--内容主体区域开始-->
		<div class="layui-body" data-type="" data-title="公告栏"
			data-icon="layui-icon-home" data-href="/home"></div>
		<!--内容主体区域结束-->

	</div>
	<script src="/static/js/jquery-1.11.3.js"></script>
	<script src="/static/layui/layui.js"></script>

	<script>
		layui.config({
			base : '/static/'
		}).extend({
			lau : 'lau/lau'
		}).use([ 'lau' ], function() {
			var lau = layui.lau, layer = layui.layer, $ = layui.$;
			//监听事件,这个不一定要用lau-event,可以自己写
			$(document).on('click', '[lau-event]', function() {
				var _this = $(this);
				switch (_this.attr('lau-event')) {
				case "sideMenu0":
					lau.sideMenuChange(0);
					break;
				case "sideMenu1":
					lau.sideMenuChange(1);
					break;
				case "reload":
					lau.reload();
					break;
				}
			});
		});

		function ChangePass() {
			var html = '<fieldset class="layui-elem-field layui-field-title">';
			html += '<legend>修改密码</legend>';
			html += '</fieldset>';
			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">输入原密码</label>';
			html += '<div class="layui-input-inline">';
			html += '<input type="password" id="PasswordOld" placeholder="请输入原密码" autocomplete="off" class="layui-input">';
			html += '</div>';
			html += '</div>';

			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">输入新密码</label>';
			html += '<div class="layui-input-inline">';
			html += '<input type="password" id="Password" placeholder="4-20为且只能是字母或数字" autocomplete="off" class="layui-input">';
			html += '</div>';
			html += '</div>';

			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">再输入密码</label>';
			html += '<div class="layui-input-inline">';
			html += '<input type="password" id="Password2" placeholder="再次输入密码" autocomplete="off" class="layui-input">';
			html += '</div>';
			html += '</div>';

			html += '<div class="layui-form-item" style="text-align: center;">';
			html += '<button class="layui-btn layui-btn-sm layui-btn-normal" onclick="change()">&nbsp;&nbsp;确&nbsp;&nbsp;认&nbsp;&nbsp;</button>';
			html += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			html += '<button class="layui-btn layui-btn-sm" onclick="javascript:layer.closeAll();">&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</button>';
			html += '</div>';
			layer.open({
				type : 1,
				area : [ '330px', '310px' ],
				fixed : false, //不固定
				maxmin : false,
				content : html
			});
		}

		function change() {
			//checkNull
			if ($("#PasswordOld").val().length == 0) {
				layer.msg('请输入原密码');
				$('#PasswordOld').focus();
				return false;
			}

			if ($("#Password").val().length == 0) {
				layer.msg('请输入新密码');
				$('#Password').focus();
				return false;
			}

			if ($("#Password2").val().length == 0) {
				layer.msg('请再次输入');
				$('#Password2').focus();
				return false;
			}

			//checkPassWord2
			if ($("#Password").val() != $("#Password2").val()) {
				layer.msg('两次密码不一致');
				$("#Password2").focus();
				return false;
			}

			//checkPassReg
			var reg = /^[0-9A-Za-z]{6,20}$/;
			if (!reg.test($("#Password").val())) {
				layer.msg('密码不符合要求');
				$("#Password").focus();
				return false;
			}

			var data = {
				Oldpsw : $("#PasswordOld").val(),
				Newpsw : $("#Password").val(),
				Newpsw2 : $("#Password2").val()
			}
			subDataOpt("resetPass", "/ChangePassword", data, "");
		}

		function subDataOpt(type, url, data, obj) {
			console.log(type + '==' + url);
			if (data == null) {
				data = '[]';
			}
			//提交中
			layer.msg('提交中...', {
				icon : 16,
				shade : 0.3,
				time : -1
			});
			$.ajax({
				type : "POST",
				url : url,
				data : data,
				dataType : "json",
				success : function(json) {
					if (json.status) {
						switch (type) {
						case 'resetPass':
							//重置密码 完成后提示新密码
							layer.msg(json.msg, {
								icon : 1,
								shade : 0.3,
								time : 1500
							}, function() {
								layer.closeAll();
								location.href = "/logout"
							});
							break;
						case 'Update':
							//完成后 关闭已打开窗体
							layer.msg(json.msg, {
								icon : 1,
								shade : 0.3,
								time : 1500
							}, function() {
								parent.layer.closeAll();
							});
							break;
						case 'Add':
							//完成后 重置当前表单
							layer.msg(json.msg, {
								icon : 1,
								shade : 0.3,
								time : 1500
							});
							$('#reset').trigger("click");
							break;
						case 'Table':
							//完成后 从当前表单中删除元素
							layer.msg(json.msg, {
								icon : 1,
								shade : 0.3,
								time : 1500
							});
							obj.del();
							break;
						default:
							layer.msg(json.msg, {
								icon : 1,
								shade : 0.3,
								time : 1500
							});
						}
					} else {
						layer.msg(json.msg, {
							icon : 2,
							shade : 0.3,
							time : 1500
						});
					}
				},
				error : function(event, request, setting) {
					if (request.status === 200) {
						layer.msg('Invalid response', {
							icon : 2,
							shade : 0.3,
							time : 1500
						});
					} else {
						layer.msg(request.status + ': ' + request.statusText, {
							icon : 2,
							shade : 0.3,
							time : 1500
						});
					}
				}
			});
		}

		$(function() {
			GetDataByTab('/jsonMenu');
			//doITS()
		});

		//获取某表数据并执行回调函数
		function GetDataByTab(url) {
			layui.use('layer', function() {
				var layer = layui.layer;
				$.ajax({
					type : "POST",
					url : url,
					dataType : "json",
					success : function(json) {
						if (json.status) {
							doIT(json.menu);
						}
					},
					error : function(event, request, setting) {
						if (request.status === 200) {
							layer.msg('Invalid response', {
								icon : 2,
								shade : 0.3,
								time : 1500
							});
						} else {
							layer.msg('连接超时，请刷新', {
								icon : 2,
								shade : 0.3,
								time : 1500
							});
						}
					}
				});
			})
		}

		//加载菜单
		function doIT(result) {
			var data = JSON.parse(result);
			for ( var i in data) {
				var href = '';
				if (data[i].href != undefined) {
					href = 'lau-href="' + data[i].href + '" lau-event="reload"';
				}
				var html = '<li class="lau-nav-item">';
				html += '<a class="lau-nav-header"' + href + ' ><i class="layui-icon ' + data[i].icon + '"></i><cite>'
						+ data[i].title + '</cite></a>';
				if (data[i].href == undefined) {
					html += '<dl class="lau-nav-child">';
					for ( var j in data[i].children) {
						html += '<dd><a lau-href="' + data[i].children[j].href + '" lau-event="reload"><i class="layui-icon '+data[i].children[j].icon+'"></i><cite>'
								+ data[i].children[j].title
								+ '</cite></a></dd>';
					}
					html += '</dl>';
				}
				html += '</li>';
				$("#ul").append(html);
			}
		}
		
	</script>
</body>
</html>