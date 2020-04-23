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
<body>
	
<!-- 主体 -->
	<!-- 表单 -->
	<script type="text/html" id="toolbar">
		<div class="layui-btn-container" id="sureBtn">
			<button class="layui-btn layui-btn-sm" lay-event="getThis">确定</button>
		</div>
    </script>
	<div class="layui-tab-item layui-show">
		<table class="layui-hide" id="Table_userManager" lay-filter="button"></table>
		<script type="text/html" id="switchTpl">
            <!-- 这里的 checked 的状态只是演示 -->
            <input type="checkbox" name="Status" value="{{d.SysId}}" lay-skin="switch" lay-text="锁定|正常" lay-filter="Status" {{ d.Status == '正常' ? '' : 'checked' }}>
        </script>
	</div>
	<input type="hidden" id="roleId" value="${roleId}">
<script>
layui.use([ 'layer', 'table', 'form', 'laydate' ], function() {
	var layer = layui.layer //弹层
	, table = layui.table //表格
	, form = layui.form, laydate = layui.laydate
	var id = getQueryString('id');
	//执行一个 table 实例
	table.render({
		elem : '#Table_userManager',
		url : "/sys_role/userManeger?roleId="+$("#roleId").val(),//数据接口
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
			fixed: 'left',
			type: 'checkbox'
		}, {
			field : 'id',
			title : '系统编码',
			LAY_CHECKED:true
		}, {
			field : 'loginName',
			title : '账号'
		}, {
			field : 'realName',
			title : '真实姓名',
		}, { 
			field: 'cell', 
			title: '手机号码', 
			sort : true,//是否排序
		}, { 
			field: 'idCard', 
			title: '身份证号码', 
			sort : true,//是否排序
		}, { 
			field: 'lastLoginTime', 
			title: '最后登录', 
			sort : true,//是否排序
			unresize: true//列宽可以拖拽
		}
		] ]
	});
	//头工具栏事件
    table.on('toolbar(button)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        switch (obj.event) {
            case 'getThis':
                var data = checkStatus.data;  //获取选中行数据
                if (data == '') {
                    layer.msg('请选中操作员继续');
                    return false;
                }
                var opera = '';
                for (var pore in data) {
                    if (data.hasOwnProperty(pore)) {
                        opera +=data[pore].id + ',';
                    }
                }                   
                //console.log(opera);
                subDataOpt('UpdateCloseRe', '/sys_role/updateRole?type='+$("#roleId").val(), { 'opts': opera});
                break;
        };
    });
});
</script>
</body>
</html>