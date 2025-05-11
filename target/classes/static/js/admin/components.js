/**
 * 
 */

//菜单组件
Vue.component('menu-item',{
    props:["item", "index"],
    template:[
        '<li :class="item.class">',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.type === 0" class="treeview-menu">',
        '<menu-item :item="item" :index="index" v-for="(item, index) in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="item.url" :target="item.target">' +
        '<i v-if="item.icon != null" :class="item.icon"></i>' +
        '<i v-else class="fa fa-circle-o"></i> {{item.name}}' +
        '</a>',
        '</li>'
    ].join(''),
    created: function(){
    	if(this.item.url && location.href.indexOf(this.item.url) != -1){
    		this.item.class = "active";
    	}
    },
    mounted: function(){
    	if(this.item.url && location.href.indexOf(this.item.url) != -1){
    		$("aside .active").parents("li").attr("class", "active");
    	}
    }
});

Vue.component("main-header", {
    props: [""],
    data: function() {
        return {
        	user:{},
            form:{
                password:'',
                newPassword:''
    		}
        }
    },
    template: 
      '<div>'+
	  '<header class="main-header">'+
	    '<a href="javascript:;" class="logo">'+
	      '<!-- mini logo for sidebar mini 50x50 pixels -->'+
	      '<span class="logo-mini"><b>借阅</b></span>'+
	      '<!-- logo for regular state and mobile devices -->'+
	      '<span class="logo-lg"><b>图书借阅</b></span>'+
	    '</a>'+
	    '<!-- Header Navbar: style can be found in header.less -->'+
	    '<nav class="navbar navbar-static-top" role="navigation">'+
	      '<div class="navbar-custom-menu">'+
	        '<ul class="nav navbar-nav">'+
			  '<li><a data-toggle="modal" data-target="#pwd"><i class="fa fa-lock"></i> &nbsp;修改密码</a></li>'+
	          '<li><a href="javascript:;" @click="logout"><i class="fa fa-sign-out"></i> &nbsp;退出系统</a></li>'+
			'</ul>'+
	      '</div>'+
	    '</nav>'+
	  '</header>'+
	  '<div class="modal fade" id="pwd" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-lg">'+
	  '<div class="modal-content">'+
	  '<div class="modal-header">'+
      '<h4 class="modal-title">修改密码</h4>'+
	  '</div>'+
	  	'<div class="modal-body">'+
		'<form class="form-horizontal">'+
		'<div class="form-group">'+
			'<div class="form-group">'+
			   	'<div class="col-sm-3 control-label">账号</div>'+
			    '<div class="col-sm-7"><span class="label label-success" style="vertical-align: bottom;">{{user.username}}</span></div>'+
			'</div>'+
			'<div class="form-group">'+
			   	'<div class="col-sm-3 control-label">原密码</div>'+
			   	'<div class="col-sm-7">'+
			      '<input type="password" class="form-control" v-model="form.password" placeholder="原密码"/>'+
			    '</div>'+
			'</div>'+
			'<div class="form-group">'+
			   	'<div class="col-sm-3 control-label">新密码</div>'+
			   	'<div class="col-sm-7">'+
			      '<input type="text" class="form-control" v-model="form.newPassword" placeholder="新密码"/>'+
			    '</div>'+
			'</div>'+
		'</div>'+
		'</form>'+
		'</div>'+
	    '<div class="modal-footer">'+
          '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'+
          '<button type="button" class="btn btn-primary" @click="updatePassword">确定</button>'+
        '</div>'+
	  '</div>'+
	  '</div>'+
	'</div>'+
	'</div>'
    ,
    methods: {
		getUser: function(){
			var $this = this;
			$.getJSON(baseURL + "sys/user/getInfo", function(r){
				$this.user = r.user;
			});
		},
		updatePassword: function(){
			var $this = this;
			$.ajax({
				type: "POST",
			    url: baseURL + "sys/user/password?password="+$this.form.password+"&newPassword="+$this.form.newPassword,
			    dataType: "json",
                contentType: "application/json",
			    success: function(r){
					if(r.code == 0){
						$('#pwd').modal('hide');
						alert('修改成功', function(){
							
						});
					}else{
						alert(r.msg, function(){
							
						});
					}
				}
			});
		},
        logout: function () {
        	//删除本地token
            localStorage.removeItem("token");
            //跳转到登录页面
            location.href = baseURL + 'admin/login.html';
        }
    },
    created: function() {
		this.getUser();
    }
});

Vue.component("main-sidebar", {
    props: [""],
    data: function() {
        return {
    		menuList:[{
    			"menuId": 1,
    			"parentId": 0,
    			"parentName": null,
    			"name": "统计分析",
    			"url": "/admin/index.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa fa-bar-chart",
    			"orderNum": 0,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 2,
    			"parentId": 0,
    			"parentName": null,
    			"name": "用户管理",
    			"url": "/admin/member/member.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-user",
    			"orderNum": 1,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 6,
    			"parentId": 0,
    			"parentName": null,
    			"name": "广告管理",
    			"url": "/admin/advert/advert.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-flag",
    			"orderNum": 1,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 3,
    			"parentId": 0,
    			"parentName": null,
    			"name": "分类管理",
    			"url": "/admin/book/category.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-navicon",
    			"orderNum": 2,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 4,
    			"parentId": 0,
    			"parentName": null,
    			"name": "图书管理",
    			"url": "/admin/book/book.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-shopping-bag",
    			"orderNum": 3,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 5,
    			"parentId": 0,
    			"parentName": null,
    			"name": "借阅管理",
    			"url": "/admin/order/order.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-file-text",
    			"orderNum": 4,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 3,
    			"parentId": 0,
    			"parentName": null,
    			"name": "评价管理",
    			"url": "/admin/order/evaluation.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-navicon",
    			"orderNum": 2,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 6,
    			"parentId": 0,
    			"parentName": null,
    			"name": "管理员管理",
    			"url": "/admin/sys/user.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-cog",
    			"orderNum": 6,
    			"open": null,
    			"list": []
    		}, {
    			"menuId": 7,
    			"parentId": 0,
    			"parentName": null,
    			"name": "押金退还管理",
    			"url": "/admin/member/depositRefund.html",
    			"perms": null,
    			"type": 1,
    			"icon": "fa fa-money",
    			"orderNum": 7,
    			"open": null,
    			"list": []
    		}],
    		activeMenuId:-1
        }
    },
    template:
	  '<aside class="main-sidebar">'+
	    '<!-- sidebar: style can be found in sidebar.less -->'+
	    '<section class="sidebar">'+
	      '<!-- /.search form -->'+
	      '<!-- sidebar menu: : style can be found in sidebar.less -->'+
	      '<ul class="sidebar-menu">'+
	        '<li class="header">导航菜单</li>'+
	        '<!-- vue生成的菜单 -->'+
			'<menu-item :item="item" :index="index" v-for="(item, index) in menuList"></menu-item>'+
	      '</ul>'+
	    '</section>'+
	    '<!-- /.sidebar -->'+
	  '</aside>'
    ,
    methods: {
    },
    created: function() {
    	var params = getRequest();
    	if(params){
    		this.activeMenuId = params.menuId;
    	}
    },
    mounted: function(){
    }
});


Vue.component("content-header", {
    props: ['title'],
    template:
      '<section class="content-header">'+
	    '<h1>{{title}}</h1>'+
	    '<ol class="breadcrumb" id="nav_title">'+
	      '<li class="active"><i class="fa fa-home"></i> &nbsp; 首页</li>'+
	      '<li class="active">{{title}}</li>'+
	    '</ol>'+
	  '</section>'
});

Vue.component("main-footer", {
	props: [""],
	data: function() {
	},
	template:
	  '<footer class="main-footer">'+
	    '<div class="pull-right hidden-xs">'+
	      'Version 1.0'+
	    '</div>'+
	    'Copyright &copy; 2024 <a href="http://www.kinnong.cn" target="_blank">kinnong.cn</a> All Rights Reserved'+
	  '</footer>'
});
