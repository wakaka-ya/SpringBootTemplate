<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>添加角色</title>
		<link href="../layui/css/layui.css" rel="stylesheet" />
		<script src="../js/jquery-1.11.3.js"></script>
		<script src="../layui/layui.js"></script>
		<script src="../layui/common.js"></script>
		<script src="../js/jquery.cookie.js"></script>
	</head>

	<body>
	<fieldset class="layui-elem-field">
		<legend>基本信息</legend>
		<form class="layui-form" action="">
			<input type="hidden" name="menuLong" value="${menuLong?if_exists}">
			<div class="admin-main">
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称</label>
		            <div class="layui-input-block">
		                <input type="text" name="roleName" id="roleName" autocomplete="off" class="layui-input">
		            </div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">描述</label>
		            <div class="layui-input-block">
	      				<textarea name="roleDesc" placeholder="请输入描述" class="layui-textarea"></textarea>
		            </div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">选择权限</label>
					<div id="munuManeger"></div>
					
				</div>
				<div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
				      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				    </div>
				</div>
			</div>
		</form>
		</fieldset>
		<script>
		
		layui.use([ 'form', 'jquery' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			//form.render();
			//监听提交
			form.on('submit(submit)', function(data) {
				//console.log(data.field);
				$.ajax({
		            type: "POST",
		            url:  '/sys_role/add',
		            data: data.field,
		            dataType: "json",
		            headers: { 
		            	'authorization': $.cookie("token")
                    },
		            success: function (json) {
		            	if (json.status) {
							top.layer.msg(json.msg);
						} else {
							top.layer.msg(json.msg);
						}
		            }
		        });
/* 				$.post('/sys_role/add', data.field, function(msg) {
					if (msg == '1') {
						layer.msg("保存成功");
					} else {
						layer.msg("保存失败");
					}
				}, 'json'); */
				//layer.msg(JSON.stringify(data.field));
				return false;
			});
			var lists = ${lists?if_exists};
			for (index in lists){
				var content = "";
/* 				if(index != 0){
					content = content + '<label class="layui-form-label"></label>';
					//$("#munuManeger").append('<label class="layui-form-label"></label>');
				} */
				content = content + '<div class="layui-input-block">';
				//$("#munuManeger").append('<div class="layui-input-block">');
				for (item in lists[index]){
					content = content + '<input type="checkbox" lay-filter="'+lists[index][item].id+'" id="'+lists[index][item].id+'" name="menuItem['+lists[index][item].id+']" title="'+lists[index][item].title+'" value="'+lists[index][item].pid+'">';
					//$("#munuManeger").append('<input type="checkbox" id="'+lists[index][item].id+'" name="menuItem['+lists[index][item].id+']" title="'+lists[index][item].title+'">');
					//console.log(lists[index][item].title);
					form.on("checkbox("+lists[index][item].id+")", function(data){
						if(data.elem.value != 1){
							//console.log(data.elem.pid);
							var objs = $("input[value='"+data.elem.value+"']");
							//console.log(objs[0].checked);
							if(data.elem.checked==false){
								for(var i = 0;i<objs.length;i++){
									if(objs[i].checked==true){
										return;
									}
									$("#"+data.elem.value).prop("checked",false);
								}
							}else{
								$("#"+data.elem.value).prop("checked",true);
							}
							//动态添加checkbox后渲染表格
						 	form.render();
							//console.log($('#flog_'+data.elem.value).val());
						}
						if(data.elem.value == 1){
							//console.log($("input[value='"+data.elem.id+"']").val());
							$("input[value='"+data.elem.id+"']").prop("checked",data.elem.checked);
							//动态添加checkbox后渲染表格
						 	form.render();
						}
						//console.log(data.elem); //得到checkbox原始DOM对象
						//console.log(data.elem.checked); //是否被选中，true或者false
						//console.log(data.value); //复选框value值，也可以通过data.elem.value得到
						//console.log(data.othis); //得到美化后的DOM对象
				 	}); 
				}
				content = content + '</div>';
				//$("#munuManeger").append('</div>');
				$("#munuManeger").append(content);
			}
			//动态添加checkbox后渲染表格
		 	form.render();
		});
		</script>
	</body>
</html>