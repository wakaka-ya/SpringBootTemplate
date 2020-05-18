layui.define(['layer', 'form', 'tips'], function(exports) {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.$,
        tips = layui.tips;

    //刷新验证码
    //var captchaImg = $('.lau-sign-captcha'), captchaSrc = captchaImg.attr('src');
    //captchaImg.click(function () {
    //    $(this).attr('src', captchaSrc + '?_t=' + Math.random());
    //});

    //ajax请求出错提示
    //$(document).ajaxError(function (event, request, setting) {
    //    if (request.status === 200) {
    //        tips.error('Invalid response');
    //    } else {
    //        tips.error(request.status + ': ' + request.statusText);
    //    }
    //});

    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)"); //正则匹配
        if (arr = document.cookie.match(reg)) {
            return unescape(arr[2]);
        }
        else {
            return null;
        }
    }

    //登录
    form.on('submit(login)', function (data) {
    	
        /*if (!/^[a-zA-Z0-9_]{4,16}$/.test(data.field.username)) {
            tips.warning('用户名必须为5-16位数字/字母/下划线组成');
            return false;
        } else if (!/^[0-9A-Za-z]{4,20}$/.test(data.field.password)) {
            tips.warning('密码为4-20为字符，且必须是字母或数字');
            return false;
        } else if (!/^\S{4,}$/.test(data.field.captcha)) {
            tips.warning('验证码格式不正确');
            return false;
        }*/
        
        
        //登录中
        //tips.loading('登录中...', 0, -1);
        //发送登录表单
        $.ajax({
            type: "POST",
            url: '/LoginIn',
            data: data.field,
            dataType: "json",
            /*beforeSend: function(request) {
                request.setRequestHeader("Access-Control-Allow-Origin", "*");
                request.setRequestHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                request.setRequestHeader("Access-Control-Max-Age", "3600");
                request.setRequestHeader("Access-Control-Allow-Headers", "x-requested-with");
            },*/
            success: function (json) {
            	 if(json.status){
            		 tips.loading(json.msg, 0, -1);
            		 var date = new Date();
            		 date.setTime(date.getTime()+30*60*1000);
            		 $.cookie('token', json.token, {expires: date});
                     location.href = '/index';
            	 }else{
            		 tips.warning(json.msg, 0, 1500);
                     $('#valiCode').trigger("click");
            	 }
            	
                 /*if (json.IsError == 0) {
                     if (json.Code == 200) {
                         if (getCookie("checked") == "true") {
                             document.cookie = "username" + '=' + escape(data.field.username);
                             document.cookie = "password" + '=' + escape(data.field.password);
                         }
                         if (json.Message == "true") {
                             layer.closeAll();
                             let html = "";
                             html +=`<div class="layui-form-item"  style="margin-top:45px;">
                                    <label class="layui-form-label" style="width:70px;border:1px solid #e6e6e6;border-right:none;background-color:#f8f8f8;margin-left:40px;"><i class="layui-icon layui-icon-username"></i> 账　号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="username" readonly="readonly" placeholder="请输入用户名" value="`+ data.field.username +`" autocomplete="off" class="layui-input" style="width:200px;margin-left:20px;height:41px;">
                                    </div>
                                </div>`;
                             html += `<div class="layui-form-item">
                                        <label class="layui-form-label" style="width:70px;border:1px solid #e6e6e6;border-right:none;background-color:#f8f8f8;margin-left:40px;"><i class="layui-icon layui-icon-vercode"></i> 验证码</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="phonecode" id="phonecode" placeholder="请输入手机验证码" value="" autocomplete="off" class="layui-input" style="width:200px;height:41px;margin-left:20px;">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div style="width:301px;height:38px;border:1px solid #ccc;text-align:center;line-height:38px;cursor:pointer;margin-top:5px;margin-left:40px;" id="_code_cut" onclick="getphoneCode()">获取手机验证码</div>
                                    </div>
                                    <div class="layui-form-item">
                                        <button type="button" id="login_in" class="layui-btn layui-btn-fluid" lay-filter="login" style="width:80%;margin-left:10%;margin-top:30px;" onclick="yanzheng()">验  证</button>
                                    </div>`;
                             layer.open({
                                 type: 1,
                                 title: '手机验证',
                                 shade: 0.5,
                                 area: ['380px', '362px'],
                                 content: html
                             });
                         } else {
                             tips.loading(json.Message, 0, -1);
                             location.href = '/index';
                         }
                     } else {
                         tips.warning(json.Message, 0, 1500);
                         $('#valiCode').trigger("click");
                     }
                 } else {
                     tips.error(json.Message, 0, 1500);
                     $('#valiCode').trigger("click");
                 }*/
            },
            error: function (event, request, setting) {
                console.log(event);
                    if (request.status === 200) {
                        tips.error('Invalid response');
                    } else {
                        tips.error(request.status + ': ' + request.statusText);
                    }
            }
        });
        return false;
    });
    exports('login', {});
});