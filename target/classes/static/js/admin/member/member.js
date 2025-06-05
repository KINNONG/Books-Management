$(function () {
  var columns = [
    { checkbox: true, align: "center" },
    {
      title: "头像",
      field: "avatarUrl",
      formatter: function (value, row, index) {
        return '<img width="30px" height="30px" src="' + value + '" />';
      },
    },
    { title: "昵称", field: "nickname" },
    { title: "姓名", field: "realName" },
    { title: "手机号", field: "mobile" },
    { title: "身份证号", field: "idCard" },
    {
      title: "支付类型",
      field: "paymentType",
      formatter: function (value, row, index) {
        if (value === "wechat") {
          return '<span class="label label-success">微信</span>';
        } else if (value === "alipay") {
          return '<span class="label label-info">支付宝</span>';
        } else if (value === "bank") {
          return '<span class="label label-warning">银行卡</span>';
        }
        return value || "";
      },
    },
    {
      title: "支付账户",
      field: "paymentAccount",
      formatter: function (value, row, index) {
        if (row.paymentType === "wechat" && row.wechatAccount) {
          return "<div><strong>微信：</strong>" + row.wechatAccount + "</div>";
        } else if (row.paymentType === "alipay" && row.alipayAccount) {
          return "<div><strong>支付宝：</strong>" + row.alipayAccount + "</div>";
        } else if (row.paymentType === "bank") {
          var bankInfo = "";
          if (row.bankCardNumber) {
            bankInfo += "<div><strong>卡号：</strong>" + row.bankCardNumber + "</div>";
          }
          if (row.bankName) {
            bankInfo += "<div><strong>银行：</strong>" + row.bankName + "</div>";
          }
          if (row.cardholderName) {
            bankInfo += "<div><strong>持卡人：</strong>" + row.cardholderName + "</div>";
          }
          return bankInfo || "";
        }
        return "";
      },
    },
    {
      title: "性别",
      field: "gender",
      formatter: function (value, row, index) {
        if (value == 1) {
          return "男";
        } else if (value == 0) {
          return "女";
        } else {
          return "未知";
        }
      },
    },
    { title: "押金", field: "deposit" },
    { title: "注册时间", field: "createTime" },
  ];

  $("#table").bootstrapTable({
    url: baseURL + "member/list",
    cache: false,
    striped: true,
    pagination: true,
    pageSize: 10,
    pageNumber: 1,
    sidePagination: "server",
    columns: columns,
    queryParams: function (params) {
      return {
        page: params.offset / params.limit + 1,
        limit: params.limit,
        nickname: vm.q.nickname,
        realName: vm.q.realName,
        mobile: vm.q.mobile,
      };
    },
  });
});

var vm = new Vue({
  el: "#app",
  data: {
    showList: true,
    title: null,
    user: {
      paymentType: "wechat",
    },
    q: {
      nickname: "",
      realName: "",
      mobile: "",
    },
  },
  methods: {
    query: function () {
      vm.reload();
    },
    add: function () {
      vm.showList = false;
      vm.title = "新增";
      vm.user = {
        paymentType: "wechat",
        gender: "1",
        nickname: "",
        realName: "",
        mobile: "",
        idCard: "",
        wechatAccount: "",
        alipayAccount: "",
        bankCardNumber: "",
        bankName: "",
        cardholderName: "",
        avatarUrl: "",
        deposit: 0,
      };
    },
    update: function (event) {
      var id = getSelectedVal("id");
      if (id == null) {
        return;
      }
      vm.showList = false;
      vm.title = "修改";

      vm.getInfo(id);
    },
    // 表单验证方法
    validateForm: function () {
      if (!vm.user.realName || vm.user.realName.trim() === "") {
        alert("请输入真实姓名");
        return false;
      }

      if (vm.user.idCard && vm.user.idCard.trim() !== "") {
        // 身份证号验证
        var idCardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!idCardReg.test(vm.user.idCard)) {
          alert("身份证号格式不正确");
          return false;
        }
      }

      if (vm.user.mobile && vm.user.mobile.trim() !== "") {
        // 手机号验证
        var mobileReg = /^1[3-9]\d{9}$/;
        if (!mobileReg.test(vm.user.mobile)) {
          alert("手机号格式不正确");
          return false;
        }
      }

      // 支付方式相关验证
      if (vm.user.paymentType === "wechat" && (!vm.user.wechatAccount || vm.user.wechatAccount.trim() === "")) {
        alert("请输入微信账户");
        return false;
      }

      if (vm.user.paymentType === "alipay" && (!vm.user.alipayAccount || vm.user.alipayAccount.trim() === "")) {
        alert("请输入支付宝账户");
        return false;
      }

      if (vm.user.paymentType === "bank") {
        if (!vm.user.bankCardNumber || vm.user.bankCardNumber.trim() === "") {
          alert("请输入银行卡号");
          return false;
        }
        if (!vm.user.bankName || vm.user.bankName.trim() === "") {
          alert("请输入银行名称");
          return false;
        }
        if (!vm.user.cardholderName || vm.user.cardholderName.trim() === "") {
          alert("请输入持卡人姓名");
          return false;
        }
        // 银行卡号验证（16-19位数字）
        var bankCardReg = /^\d{16,19}$/;
        if (!bankCardReg.test(vm.user.bankCardNumber)) {
          alert("银行卡号格式不正确");
          return false;
        }
      }

      return true;
    },
    saveOrUpdate: function (event) {
      // 表单验证
      if (!vm.validateForm()) {
        return;
      }

      var url = vm.user.id == null ? "member/save" : "member/update";
      $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.user),
        success: function (r) {
          if (r.code === 0) {
            alert("操作成功", function (index) {
              vm.reload();
            });
          } else {
            alert(r.msg, function (e) {});
          }
        },
      });
    },
    del: function (event) {
      var ids = getSelectedVals("id");
      if (ids == null) {
        return;
      }

      confirm("确定要删除选中的记录？", function () {
        $.ajax({
          type: "POST",
          url: baseURL + "member/delete",
          contentType: "application/json",
          data: JSON.stringify(ids),
          success: function (r) {
            if (r.code == 0) {
              alert("操作成功", function (index) {
                vm.reload();
              });
            } else {
              alert(r.msg, function (e) {});
            }
          },
        });
      });
    },
    getInfo: function (id) {
      $.get(baseURL + "member/info/" + id, function (r) {
        vm.user = r.member;
        // 确保支付类型有默认值
        if (!vm.user.paymentType) {
          vm.user.paymentType = "wechat";
        }
      });
    },
    reload: function (event) {
      vm.showList = true;
      $("#table").bootstrapTable("refresh", { pageNumber: 1 });
    },
  },
});
