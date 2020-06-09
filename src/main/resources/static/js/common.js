/** common.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.define(['layer'], function(exports) {
	"use strict";

	var $ = layui.jquery,
		layer = layui.layer;

	var common = {
		/**
		 * 抛出一个异常错误信息
		 * @param {String} msg
		 */
		throwError: function(msg) {
			throw new Error(msg);
			return;
		},
		/**
		 * 弹出一个错误提示
		 * @param {String} msg
		 */
		msgError: function(msg) {
			layer.msg(msg, {
				icon: 5
			});
			return;
		}
	};

	exports('common', common);

	$.ajaxSetup({
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		complete: function(XMLHttpRequest, textStatus) {

		},
		statusCode: {
			403: function(data) {
				console.log(data)
				//alert('登录失效，请重新登录');
				window.location.href = "/login.html";
			},
			504: function() {
				alert('数据获取/输入失败，服务器没有响应。504');
			},
			500: function() {
				alert('服务器有误。500');
			}
		}
	});
});