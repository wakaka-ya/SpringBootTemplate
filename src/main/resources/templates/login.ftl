<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登　录</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/sign.css">
    <script>(window.top === window.self) || (window.top.location.href = window.self.location.href);</script>
    <style>
        #loginDiv {
            position: fixed;
            margin: auto;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            width: 700px;
            height: 420px;
            border-radius: 10px;
        }

        .loginBg {
            width:50%;
            height:100%;
            float:left;
            background-image:url("/static/images/leftbg.png");
            background-repeat:no-repeat;
            background-size:100% 100%;
        }
        .text {
            font-size:36px;
            color:white;
            margin-left:41px;
            margin-top:147px;
        }
        .name {
            font-size: 29px;
            color: #2B59DB;
            margin-left:41px;
            margin-top:12px;
        }
        .metion {
            font-size: 29px;
            color: white;
            margin-top: 12px;
        }

        .loginBox {
            width:50%;
            height:100%;
            float:right;
        }
        .Line {
            width: 290px;
            height: 41px;
            border-bottom: 1px solid #E1E1E1;
            margin-left:32px;
            margin-top:59px;
        }
        ._image {
            width:24px;
            height:27px;
            margin-left:5px;
        }
        ._input {
            width:230px;
            height:35px;
            margin-top:3px;
        }
        .__input {
            width: 130px;
            height: 35px;
            margin-top: 3px;
        }
        ._yanzheng {
            width:120px;
            height:36px;
        }

        input::-webkit-input-placeholder {
            color: #C2C2C2;
            font-size: 14px;
        }

        input {
            border: none;
            outline: none;
            font-size: 1em;
            background-color: transparent;
            font-size: 14px;
            text-indent:1em;
        }
        .btn {
            width: 290px;
            height: 50px;
            margin-top: 34px;
            margin-left: 26px;
            background: rgba(43,89,219,1);
            border-radius: 5px;
            cursor:pointer;
            text-align:center;
            font-size: 16px;
            color: rgba(255,255,255,1);
            line-height: 50px;
        }

        .lau-sign-form {
            padding:0;
        }
    </style>
</head>
<body class="layui-unselect lau-sign-body login-bg">
    <div class="layui-form layui-form-pane lau-sign-form" id="loginDiv">

        <div class="loginBg">
            <div class="text">欢迎登录</div>
            <div><span class="name">Template</span><span class="metion">后台管理</span></div>
        </div>

        <div class="loginBox">

            <div class="Line">
                <img src="/static/images/addount.png" class="_image" />
                <input type="text" name="userName" placeholder="请输入用户名" value="" autocomplete="off" class="_input">
            </div>

            <div class="Line" style="margin-top:38px;">
                <img src="/static/images/keyword.png" class="_image" />
                <input type="password" name="password" placeholder="请输入密码" value="" autocomplete="off" class="_input">
            </div>

            <div class="Line" style="margin-top:38px;">
                <img src="/static/images/yanzheng.png" class="_image" />
                <input type="text" name="verCode" placeholder="请输入图形验证码"  autocomplete="off" class="__input">
                <img id="valiCode" name="valiCode" style="cursor: pointer;" src="/getVerCode" onclick="changeCode()" class="_yanzheng" alt="验证码" />
            </div>

            <div style="text-align:right;margin-top:17px;width:96%;">
                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码" class="_checkbox">
            </div>

            <button type="button" class="btn layui-btn" id="login_in" lay-submit lay-filter="login">立即登录</button>
        </div>


    </div>
    <div class="layui-trans lau-sign-footer">
        <p style="color:white;">© 2019 Template</p>
    </div>

    <script src="/static/js/jquery-1.11.3.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/js/jquery.cookie.js"></script>
    <script src="/static/js/common.js"></script>
    <script>
        layui.config({ base:'/static/js/' }).use('login');
    </script>
    <script type="text/javascript">

        //var isGetCode = false;

        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("#login_in").trigger("click");
            }
        });

        function changeCode(){
            var src = "/getVerCode?"+new Date().getTime(); //加时间戳，防止浏览器利用缓存
            $('._yanzheng').attr("src",src);                  //jQuery写法
            document.getElementsByClassName("__input")[0].value = "";
        }

        $(function () {
            layui.use(['form'], function () {
                var form = layui.form;
                form.on('checkbox', function (obj) {
                    document.cookie = "checked" + '=' + escape(obj.elem.checked);
                });
            });

            if (getCookie("checked") == "true") {
                $("*[name='remember']").attr("checked", "checked");
                var userName = getCookie("userName");
                var password = getCookie("password");
                if (userName != null && password != null) {
                    $("*[name='userName']").val(userName);
                    $("*[name='password']").val(password);
                } else {
                    $("*[name='userName']").val("");
                    $("*[name='password']").val("");
                }
            }

         /*   //验证码【暂无】
            $("#valiCode").bind("click", function () {
                this.src = "/Login/GetVerCode?time=" + (new Date()).getTime();
            });*/

            function getCookie(name) {
                var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)"); //正则匹配
                if (arr = document.cookie.match(reg)) {
                    return unescape(arr[2]);
                }
                else {
                    return null;
                }
            }
        });
    </script>
</body>
</html>