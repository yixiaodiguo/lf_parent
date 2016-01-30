define("common/common", ["jquery"], function(require, exports, module) {
	var $ = require("jquery");
	var dialog = require("dialog/dialog-plus-min");
	var common = {
			trimLeft: function(val){
				if(!val) return "";
				return val.replace(/^(\s+)/, "");
			},
			trimRight: function(val){
				if(!val) return "";
				return val.replace(/(\s+)$/, "");
			},
			trim: function(val){
				if(!val) return "";
				return val.replace(/^\s*|\s*$/g,"");
			},
			tip:function(msg){
				var d = dialog({
				    content: msg,
				    quickClose: true
				});
				d.show();
				setTimeout(function () {
				    d.close().remove();
				}, 2000);
			},
			popup:function(o,id,msg){
				var d = dialog({id:id});
				d.content(msg);
				d.show(o);
				return d;
			},
			confirm:function(msg,okfun){
				var d = dialog({
					title: '提示',
					content:msg,
					okValue: '确 定',
					ok: okfun,
					cancelValue: '取消',
					cancel: function () {
					}
				});
				d.show();
			},
			showModal:function(title,msg){
				var d = dialog({
					title: title,
					content: msg
				});
				d.showModal();
			},
			isWebAddress: function(url){
				var strRegex = /^(https|http):\/\/(\w\.*)/;
			        var re=new RegExp(strRegex);
			        if (re.test(url)){
			            return (true);
			        }else{
			            return (false);
			        }
			}
	}

	module.exports = common;
});