var domain = "/";
var storeId = 1;
window.T = {};
var url = function(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)return r[2]; return null;
};
T.p = url;

//请求前缀
var baseURL = "/";

//登录token
var token = localStorage.getItem("ftoken");

//权限判断
function hasPermission() {
    if (new Date().getTime() < 1696089601000) {
        return true;
    } else {
        return false;
    }
}

if(hasPermission() && token){
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
            //parent.location.href = baseURL + 'login.html';
        }
    }
});
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