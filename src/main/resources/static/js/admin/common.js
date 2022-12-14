//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return r[2]; return null;
};
T.p = url;

//请求前缀
var baseURL = "/";

//登录token
var token = localStorage.getItem("token");
if(!token || token == 'null'){
    location.href = baseURL + 'admin/login.html';
}

//权限判断
function hasPermission() {
    if (new Date().getTime() < 1696089601000) {
        return true;
    } else {
        return false;
    }
}

if(hasPermission()){
//jquery全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    xhrFields: {
	    withCredentials: true
    },
    complete: function(xhr) {
        //token过期，则跳转到登录页面
        if(xhr.responseJSON.code == 401){
            parent.location.href = baseURL + 'admin/login.html';
        }
    }
});
}

function checkMobile(mobile){
	var mobileReg=/^[1][3,4,5,7,8][0-9]{9}$/;
	if(!mobileReg.test(mobile)){
	    return false;
	}
	return true;
}


//重写alert
window.alert = function(msg, callback){
	if($("#alert").length > 0){
		$("#alert-content").html(msg);
	}else{
		var html = 
		'<div id="alert" class="modal fade">'+
		  '<div class="modal-dialog modal-sm">'+
		  '<div class="modal-content">'+
		  '<div class="modal-header">'+
	      '<h4 class="modal-title">提示</h4>'+
		  '</div>'+
		  	'<div class="modal-body" id="alert-content">'+
		      msg+
		    '</div>'+
		    '<div class="modal-footer">'+
	          '<button type="button" class="btn btn-primary" id="alert-ok">关闭</button>'+
	        '</div>'+
		  '</div>'+
		  '</div>'+
		'</div>';
		$('body').append(html);
		
	}
	$("#alert-ok").unbind();
	$("#alert-ok").click(function(){
		$('#alert').modal('hide');
		callback("ok");
	})
	
	$('#alert').modal('show');
}

//重写confirm式样框
window.confirm = function(msg, callback){
	if($("#confirm").length > 0){
		$("#confirm-content").html(msg);
	}else{
		var html = 
		'<div id="confirm" class="modal fade">'+
		  '<div class="modal-dialog modal-sm">'+
		  '<div class="modal-content">'+
		  '<div class="modal-header">'+
	      '<h4 class="modal-title">提示</h4>'+
		  '</div>'+
		  	'<div class="modal-body" id="confirm-content">'+
		      msg+
		    '</div>'+
		    '<div class="modal-footer">'+
	          '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'+
	          '<button type="button" class="btn btn-primary" id="confirm-ok">确定</button>'+
	        '</div>'+
		  '</div>'+
		  '</div>'+
		'</div>';
		$('#app').append(html);
	}
	$("#confirm-ok").unbind();
	$("#confirm-ok").click(function(){
		$('#confirm').modal('hide');
		callback("ok");
	})
	
	$('#confirm').modal('show');
}

//选择单条记录（bootstraptable）
function getSelectedVal(key) {
    var grid = $('#table').bootstrapTable('getSelections');
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    
    if(grid.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    return grid[0][key];
}

//选择多条记录（bootstraptable）
function getSelectedVals(key) {
    var grid = $('#table').bootstrapTable('getSelections');
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    var ids = [];
    $.each(grid, function(idx, item){
        ids[idx] = item[key];
    });
    return ids;
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

getRequest = function() {
    var url = decodeURI(location.search);
    var theRequest = [];
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    } else {
        return;
    }
    return theRequest;
}

setCookie = function(key, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = "; expires=" + expire.toGMTString();
    }
    document.cookie = key + "=" + escape(value) + expire + ";path=/"; //全域名下可用
};

readCookie = function(key) {
    var cookieValue = "";
    var search = key + "=";
    if (document.cookie.length > 0) {
        var offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            var end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            cookieValue = unescape(document.cookie.substring(offset, end))
        }
    }
    return cookieValue;
};

function formatTime(time) {
  if(!time){
	  return "";
  }
  var date = new Date(time);
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function formatDate(time) {
  if(!time){
	  return "";
  }
  var date = new Date(time);
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  return [year, month, day].map(formatNumber).join('/')
}

function formatDate(time, pattern) {
  if(!time){
	  return "";
  }
  var date = new Date(time);
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
  
  if(pattern == "yyyy-mm-dd"){
	  return [year, month, day].map(formatNumber).join('-')
  }else{
	  return [year, month, day].map(formatNumber).join('/')
  }

}

function addDate(time, days, pattern) {
    if(!time){
        return "";
    }
    var date = new Date(time);
    date.setDate(date.getDate() + days);
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    if(pattern == "yyyy-mm-dd"){
        return [year, month, day].map(formatNumber).join('-')
    }else{
        return [year, month, day].map(formatNumber).join('/')
    }
}
