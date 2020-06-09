
$(function() {
    var count = 0;
    // 加载下拉列表
    var SELECT = $('.selectData');
    SELECT.each(function() {
        var sideHref = $.trim($(this).attr('data-href'));
        var docName = $(this).attr('name');
        if (sideHref) {
            $.ajax({
                url: sideHref,
                dataType: 'json',
                success: function(data, textStatus, jqXHR) {
                    count++;
                    if (data.length > 0) {
                        var html = '';
                        data.forEach(function(obj) {
                            html += '<option value="' + obj.value + '">' + obj.text + '</option>';
                        });
                        $("*[name='" + docName + "']").append(html);
                        // 重新渲染
                        if (count == SELECT.length) {
                            layui.use('form',
                                function() {
                                    var form = layui.form; // 只有执行了这一步，部分表单元素才会自动修饰成功
                                    // form.render();
                                    form.render('select');
                                });
                        }
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {

                }
            });
        }
    });
});

String.prototype.trim = function (char, type) {
    if (char) {
        if (type == 'left') {
            return this.replace(new RegExp('^\\' + char + '+', 'g'), '');
        } else if (type == 'right') {
            return this.replace(new RegExp('\\' + char + '+$', 'g'), '');
        }
        return this.replace(new RegExp('^\\' + char + '+|\\' + char + '+$', 'g'), '');
    }
    return this.replace(/^\s+|\s+$/g, '');
};

// 执行完成并刷新验证码
function formatDateTime(time, format) {
    var t = new Date(time);
    var tf = function (i) { return (i < 10 ? '0' : '') + i };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
        switch (a) {
        case 'yyyy':
            return tf(t.getFullYear());
            break;
        case 'MM':
            return tf(t.getMonth() + 1);
            break;
        case 'mm':
            return tf(t.getMinutes());
            break;
        case 'dd':
            return tf(t.getDate());
            break;
        case 'HH':
            return tf(t.getHours());
            break;
        case 'ss':
            return tf(t.getSeconds());
            break;
        }
    })
}

// 执行完成并执行操作
function subDataOpt(type, url, data, obj) {
    if (data == null) {
        data = '[]';
    }
    // 提交中
    layer.msg('提交中...', { icon: 16, shade: 0.3, time: -1 });
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        beforeSend: function(xhr) {
			 xhr.setRequestHeader("authorization", $.cookie("token"));
		},//后端token验证
        success: function (json) {
            if (json.IsError == 0) {
                if (json.Code == 200) {
                    switch (type) {
                        case 'resetPass':
                            // 重置密码 完成后提示新密码
                            layer.alert('新密码：<span style="color:red;">' + json.Message + '</span>');
                            break; 
                        case 'Update':
                            // 完成后 关闭已打开窗体
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 }, function () {
                                window.location.reload();
                                layer.closeAll();
                            });
                            break;
                        case 'UpdateCloseRe':
                            // 完成后 关闭已打开窗体
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 }, function () {
                                layer.closeAll();
                                parent.location.reload();
                            });
                            break;
                        case 'csUpdate':
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 }, function () {
                                $(".layui-laypage-btn")[0].click(); 
                            });
                            break;
                        case 'Add':
                            // 完成后 重置当前表单
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 });
                            setTimeout(function() {
                                    window.location.reload();
                                },
                                1000);
                            break;
                        case 'Table':
                            // 完成后 从当前表单中删除元素
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 });
                            	obj.del();
                            break;
                        case 'Excel':
                            // 点击下载
                            layer.alert('生成完成：<a href="' + json.Message+'" style="color:red;">点击下载</a>');
                            break;
                        case 'Take':
                            layer.alert(json.Message, function () {
                                layer.close(layer.index);
                                window.location.reload();
                            });
                            
                            break;
                        default:
                            layer.msg(json.Message, { icon: 1, shade: 0.3, time: 1500 });
                    }
                } else {
                    layer.msg(json.Message, { icon: 0, shade: 0.3, time: 1500 });
                }
            } else {
                layer.msg(json.Message, { icon: 2, shade: 0.3, time: 1500 });
            }
        },
        error: function (event, request, setting) {
            if (request.status === 200) {
                layer.msg('Invalid response', { icon: 2, shade: 0.3, time: 1500 });
            } else {
                layer.msg('连接超时，请刷新', { icon: 2, shade: 0.3, time: 1500 });
            }
        }
    });
}

// 使用layer.open打开iframe页面
function showDetail(Url)
{
    layer.open({
        type: 2,
        // title: '很多时候，我们想最大化看，比如像这个页面。',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, // 开启最大化最小化按钮
        area: ['80%', '80%'],
        content: Url,
        headers: {
            'authorization': 'Bearer '+$.cookie("token")
        }
    });
}

function showMinDetail(Url)
{
    layer.open({
        type: 2,
        // title: '很多时候，我们想最大化看，比如像这个页面。',
        shadeClose: true,
        shade: 0.5,
        maxmin: true, // 开启最大化最小化按钮
        area: ['30%', '80%'],
        content: Url,
        headers: {
            'authorization': 'Bearer '+$.cookie("token")
        }
    });
}

// 获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

// 获取某表数据并执行回调函数
function GetDataByTab(url) {
    layui.use('layer', function () {
        var layer = layui.layer;

        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            success: function (json) {
                if (json.IsError == 0) {
                    if (json.Code == 200) {
                        doIT(json.Message);
                    } else {
                        layer.msg(json.Message, { icon: 0, shade: 0.3, time: 1500 });
                    }
                } else {
                    layer.msg(json.Message, { icon: 2, shade: 0.3, time: 1500 });
                }
            },
            error: function (event, request, setting) {
                if (request.status === 200) {
                    layer.msg('Invalid response', { icon: 2, shade: 0.3, time: 1500 });
                } else {
                    layer.msg('连接超时，请刷新', { icon: 2, shade: 0.3, time: 1500 });
                }
            }
        });
    })
}


function trim(str, is_global) {
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g, "");
    if (is_global && is_global.toLowerCase() == "g") {
        result = result.replace(/\s/g, "");
    }
    return result;
}

// 判断是否是手机号码格式
function isPhone(str) {
    var reg = /^1(3|4|5|7|8)\d{9}$/;
    return reg.test(trim(str, 'g'));
}

// 手机号码格式转化为 344 格式 （188 3886 9199）
function phoneSeparated(phoneNumber) {
    var tel = trim(phoneNumber, 'g');
    if (isPhone(tel)) {
        tel = tel.substring(0, 3) + ' ' + tel.substring(3, 7) + ' ' + tel.substring(7, 11);
    }
    return tel;
}