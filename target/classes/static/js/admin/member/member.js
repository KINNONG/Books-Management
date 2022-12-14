$(function () {
	var columns = [
        { checkbox: true, align: 'center' },
      	{ title: '头像', field: 'avatarUrl',
			formatter: function (value, row, index) {
				return '<img width="30px" height="30px" src="'+value+'" />';
			}	
		}, 
		{ title: '昵称', field: 'nickname'},
		{ title: '姓名', field: 'realName'},
		{ title: '手机号', field: 'mobile'},
		{ title: '性别', field: 'gender',
			formatter: function (value, row, index) {
				if(value == 1){
					return "男";
				}else if(value == 0){
					return "女";
				}else{
					return "未知";
				}
		}}, 
		{ title: '押金', field: 'deposit'},
		{ title: '注册时间', field: 'createTime'}
		];
	
	$("#table").bootstrapTable({
        url: baseURL + 'member/list',
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        sidePagination: 'server',
        columns: columns,
        queryParams: function (params) {
        	return {
	        	page: params.offset / params.limit + 1,
	        	limit: params.limit,
	        	nickname: vm.q.nickname,
	        	realName: vm.q.realName,
	        	mobile: vm.q.mobile
        	}
        }
	});
});

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		user: {},
		q:{
			nickname: '',
			realName: '',
			mobile: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.user = {};
		},
		update: function (event) {
			var id = getSelectedVal("id");
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.user.id == null ? "member/save" : "member/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg, function(e){});
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "member/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg, function(e){});
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "member/info/"+id, function(r){
                vm.user = r.user;
            });
		},
		reload: function (event) {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {pageNumber: 1});
		}
		
	}
});