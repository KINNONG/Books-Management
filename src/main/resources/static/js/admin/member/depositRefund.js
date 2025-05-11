$(function () {
    var columns = [
        { checkbox: true, align: 'center' },
        { title: '会员昵称', field: 'member.nickname' },
        { title: '会员姓名', field: 'member.realName' },
        { title: '手机号码', field: 'member.mobile' },
        { title: '申请金额', field: 'amount' },
        { title: '状态', field: 'status',
            formatter: function (value, row, index) {
                if (value === 0) {
                    return '<span class="label label-warning">待审核</span>';
                } else if (value === 1) {
                    return '<span class="label label-success">已通过</span>';
                } else if (value === 2) {
                    return '<span class="label label-danger">已拒绝</span>';
                }
                return '-';
            }
        },
        { title: '申请时间', field: 'applyTime' },
        { title: '审核时间', field: 'auditTime' },
        { title: '审核人', field: 'auditUserName' },
        { title: '备注', field: 'remark' }
    ];
    
    $("#table").bootstrapTable({
        url: baseURL + 'depositRefund/list',
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
                keyword: vm.q.keyword,
                status: vm.q.status
            }
        }
    });
});

var vm = new Vue({
    el: '#app',
    data: {
        showList: true,
        title: null,
        depositRefund: {
            member: {}
        },
        auditType: 0, // 审核类型，1通过，2拒绝
        q: {
            keyword: '',
            status: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        audit: function (auditType) {
            var id = getSelectedVal("id");
            if (id == null) {
                return;
            }
            
            var status = getSelectedVal("status");
            if (status != 0) {
                alert("只能审核待审核状态的申请");
                return;
            }
            
            vm.showList = false;
            vm.title = auditType === 1 ? "通过申请" : "拒绝申请";
            vm.auditType = auditType;
            
            vm.getInfo(id);
        },
        saveOrUpdate: function (event) {
            // 设置审核状态
            vm.depositRefund.status = vm.auditType;
            
            $.ajax({
                type: "POST",
                url: baseURL + "depositRefund/audit",
                contentType: "application/json",
                data: JSON.stringify(vm.depositRefund),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg, function (e) { });
                    }
                }
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "depositRefund/info/" + id, function (r) {
                vm.depositRefund = r.depositRefund;
            });
        },
        reload: function (event) {
            vm.showList = true;
            $('#table').bootstrapTable('refresh', { pageNumber: 1 });
        }
    }
}); 