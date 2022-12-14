$(function () {
	
	var columns = [
        { checkbox: true, align: 'center' },
      	{ title: '图片', field: 'picUrl',
			formatter: function (value, row, index) {
				return '<img width="48px" height="60px" src="'+value+'" />';
			}	
		}, 
		{ title: '图书名称', field: 'bookName'},
		{ title: '作者', field: 'author'},
		{ title: '分类', field: 'category.categoryName'},
		{ title: '价格', field: 'price'},
		{ title: '状态', field: 'status', formatter: function(value, row){
			if(value == 0){
				return '<span class="label label-default">可借阅</span>';
			}else if(value == 1){
				return '<span class="label label-primary">上架中</span>';
			}
			return '';
		}},
		{ title: '库存(本)', field: 'stock'},
		{ title: '创建时间', field: 'createTime'}
		];
	
	$("#table").bootstrapTable({
        url: baseURL + 'book/list',
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
	        	bookName: vm.q.bookName
        	}
        }
	});
	
});

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		book: {
			picUrls: []
		},
		categoryList: [],
		q:{
			bookName: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.book = {
				picUrls: [],
				categoryId : "",
				picUrl: ''
			};
			
		},
		update: function (event) {
			var id = getSelectedVal("id");
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.book = {
    			picUrls: []
    		};
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			if(vm.validator()){
				return;
			}
			
			var url = vm.book.id == null ? "book/save" : "book/update";
			//vm.book.describe = ue.getContent();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.book),
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
				    url: baseURL + "book/delete",
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
		upper: function(){
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			confirm('确定要上架选中的记录？', function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "book/upper",
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
		lower: function(){
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			confirm('确定要下架选中的记录？', function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "book/lower",
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
			$.get(baseURL + "book/info/"+id, function(r){
                vm.book = r.book;
                ue.setContent(r.book.describe);
            });
		},
		getCategoryList: function(){
			$.get(baseURL + "category/getAll", function(r){
				vm.categoryList = r.categoryList;
			});
		},
		reload: function (event) {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {pageNumber: 1});
		},
		validator: function () {
            if(!vm.book.picUrl){
                alert("请选择图书图片");
                return true;
            }

            if(isBlank(vm.book.bookName)){
                alert("请填写图书名称");
                return true;
            }
        }
	},
	created: function(){
		this.getCategoryList();
	}
});