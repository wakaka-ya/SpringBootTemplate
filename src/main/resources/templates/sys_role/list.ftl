<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>layer</title>
	<link href="../layui/css/layui.css" rel="stylesheet" />
	<script src="../js/jquery-1.11.3.js"></script>
	<script src="../layui/layui.js"></script>
	<script src="../layui/common.js"></script>
	<script src="../js/jquery.cookie.js"></script>
<style>
body {
	margin: 10px;
}
</style>
</head>
<body>

<div class="layui-tab-content">
<!-- 主体 -->
<div id="opac">

	<!-- 表单 -->
	<div class="layui-tab-item layui-show">
		
		<table class="layui-hide" id="Table" lay-filter="button"></table>

		<script type="text/html" id="barTableWork">
            <a href="javascript:;" lay-event="edit" class="layui-btn layui-btn-normal layui-btn-xs">编辑</a>
			<a href="javascript:;" lay-event="userManager" class="layui-btn layui-btn-xs">成员管理</a>
			<a href="javascript:;" lay-event="del" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
        </script>
	</div>
</div>

		<script>
			$(function() {
				load();
			})

			function load() {
				layui.use([ 'layer', 'table', 'form', 'laydate' ], function() {
					var layer = layui.layer //弹层
					, table = layui.table //表格
					, form = layui.form, laydate = layui.laydate

					// 日期范围
					laydate.render({
						elem : "*[name='times']",
						range : true,
						type : 'datetime',
						format : 'yyyy-MM-dd HH:mm'
					});

					//执行一个 table 实例
					table.render({
						elem : '#Table',
						url : "/sys_role/list",//数据接口
						method : 'post',
						title : this.innerHTML,
						page : true, //开启分页
						cellMinWidth : 80 ,//列最小宽度
						toolbar : '#toolbar', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
						headers: {
							'authorization': $.cookie("token")
						},
						cols : [ [//表头
						//{ field: 'SysId', title: '系统编码' },
						{
							field : 'id',
							title : '系统编码'
						}, {
							field : 'roleName',
							title : '角色名称'
						}, {
							field : 'roleDesc',
							title : '描述',
						}, { 
							field: 'userNum', 
							title: '成员数量', 
							sort : true,//是否排序
							unresize: true//列宽可以拖拽
						}, {
							fixed : 'right',
							title : '操作',
							width : 200,
							align : 'center',
							toolbar : '#barTableWork'
						}
						] ]
					});

					//监听行工具事件
					table.on('tool(button)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
						var data = obj.data //获得当前行数据
						, layEvent = obj.event; //获得 lay-event 对应的值
						switch (layEvent) {
						case 'edit':
							showDetail("/sys_role/edit.html?id="+data.id);
							break;
						case 'userManager': //拉黑
							showDetail("/sys_role/userManeger.html?roleId="+data.roleId);
							break;
						case 'del':
							layer.confirm('确认删除<span style="color:red">'
									+ data.roleName + '</span>吗？', function(index) {
								 //删除对应行（tr）的DOM结构
								//向服务端发送删除指令
								subDataOpt('Table', '/sys_role/delete?id=' + data.id, '', obj);
								layer.close(index);
							});
							break;
						default:
						}
					});
				});
			}
		</script>

	</div>
</body>
</html>
