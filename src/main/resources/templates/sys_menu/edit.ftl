<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>编辑菜单</title>
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
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">菜单标题：</label>
                <div class="layui-input-inline">
                    <input type="tel" name="title" value="${sysMenu.title?if_exists}" lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">图标：</label>
                <div class="layui-input-inline">
                    <input type="tel" name="icon" value="${sysMenu.icon?if_exists}" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">预览：</label>
                <i class="layui-icon ${sysMenu.icon?if_exists}" style="font-size: 30px;"></i>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">链接：</label>
            <div class="layui-input-block">
                <input type="text" name="title" value="${sysMenu.href?if_exists}"  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">排序：</label>
                <div class="layui-input-inline">
                    <input type="tel" name="icon" value="${sysMenu.rank?if_exists}" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
                url:  '/sys_menu/add',
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
            return false;
        });
    });
</script>
</body>
</html>