$(function () {
  var columns = [
    { checkbox: true, align: "center" },
    { title: "ID", field: "id", width: "50px" },
    { title: "配置键", field: "configKey", width: "200px" },
    { title: "配置区域", field: "configName", width: "150px" },
    {
      title: "文案内容",
      field: "configValue",
      width: "300px",
      formatter: function (value, row, index) {
        if (value && value.length > 50) {
          return '<span title="' + value + '">' + value.substring(0, 50) + "...</span>";
        }
        return value || "";
      },
    },
    {
      title: "配置类型",
      field: "configType",
      width: "80px",
      formatter: function (value, row, index) {
        if (value === "json") {
          return '<span class="label label-info">JSON</span>';
        } else {
          return '<span class="label label-default">字符串</span>';
        }
      },
    },
    {
      title: "状态",
      field: "enable",
      width: "80px",
      formatter: function (value, row) {
        if (value == 1) {
          return '<span class="label label-success">启用</span>';
        } else {
          return '<span class="label label-danger">禁用</span>';
        }
      },
    },
    { title: "描述", field: "description", width: "200px" },
    { title: "创建时间", field: "createTime", width: "150px" },
    { title: "更新时间", field: "updateTime", width: "150px" },
    {
      title: "操作",
      field: "id",
      width: "120px",
      formatter: function (value, row, index) {
        return [
          '<a href="javascript:void(0)" onclick="quickEditConfig(' + row.id + ')" class="btn btn-xs btn-primary">',
          '<i class="fa fa-edit"></i> 快速编辑',
          "</a>",
        ].join("");
      },
    },
  ];

  $("#table").bootstrapTable({
    url: baseURL + "shareconfig/list",
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
        configKey: vm.q.configKey,
        configName: vm.q.configName,
        enable: vm.q.enable,
      };
    },
  });
});

// 全局函数，供表格操作列调用
function quickEditConfig(id) {
  vm.quickEdit(id);
}

var vm = new Vue({
  el: "#app",
  data: {
    showList: true,
    title: null,
    shareConfig: {
      enable: 1,
      configType: "string",
    },
    quickEditData: {
      id: null,
      configKey: "",
      configName: "",
      configValue: "",
    },
    q: {
      configKey: "",
      configName: "",
      enable: "",
    },
  },
  methods: {
    query: function () {
      vm.reload();
    },
    add: function () {
      vm.showList = false;
      vm.title = "新增配置";
      vm.shareConfig = {
        enable: 1,
        configType: "string",
        configKey: "",
        configName: "",
        configValue: "",
        description: "",
      };
    },
    update: function (event) {
      var id = getSelectedVal("id");
      if (id == null) {
        return;
      }
      vm.showList = false;
      vm.title = "修改配置";
      vm.shareConfig = {};
      vm.getInfo(id);
    },
    saveOrUpdate: function (event) {
      if (vm.validator()) {
        return;
      }

      var url = vm.shareConfig.id == null ? "shareconfig/save" : "shareconfig/update";

      $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.shareConfig),
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

      confirm("确定要删除选中的配置？删除后无法恢复！", function () {
        $.ajax({
          type: "POST",
          url: baseURL + "shareconfig/delete",
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
    enable: function () {
      var ids = getSelectedVals("id");
      if (ids == null) {
        return;
      }
      confirm("确定要启用选中的配置？", function () {
        $.ajax({
          type: "POST",
          url: baseURL + "shareconfig/enable",
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
    disable: function () {
      var ids = getSelectedVals("id");
      if (ids == null) {
        return;
      }
      confirm("确定要禁用选中的配置？", function () {
        $.ajax({
          type: "POST",
          url: baseURL + "shareconfig/disable",
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
    quickEdit: function (id) {
      $.get(baseURL + "shareconfig/info/" + id, function (r) {
        if (r.code === 0) {
          vm.quickEditData.id = r.shareConfig.id;
          vm.quickEditData.configKey = r.shareConfig.configKey;
          vm.quickEditData.configName = r.shareConfig.configName;
          vm.quickEditData.configValue = r.shareConfig.configValue;
          $("#quickEditModal").modal("show");
        } else {
          alert(r.msg);
        }
      });
    },
    saveQuickEdit: function () {
      if (!vm.quickEditData.configValue.trim()) {
        alert("请输入配置值");
        return;
      }

      $.ajax({
        type: "POST",
        url: baseURL + "shareconfig/updateValue",
        data: {
          configKey: vm.quickEditData.configKey,
          configValue: vm.quickEditData.configValue,
        },
        success: function (r) {
          if (r.code === 0) {
            $("#quickEditModal").modal("hide");
            alert("更新成功", function () {
              vm.reload();
            });
          } else {
            alert(r.msg);
          }
        },
      });
    },
    getInfo: function (id) {
      $.get(baseURL + "shareconfig/info/" + id, function (r) {
        if (r.code === 0) {
          vm.shareConfig = r.shareConfig;
        } else {
          alert(r.msg);
        }
      });
    },
    reload: function (event) {
      vm.showList = true;
      $("#table").bootstrapTable("refresh", { pageNumber: 1 });
    },
    validator: function () {
      if (!vm.shareConfig.configKey || !vm.shareConfig.configKey.trim()) {
        alert("请输入配置键");
        return true;
      }
      if (!vm.shareConfig.configName || !vm.shareConfig.configName.trim()) {
        alert("请输入配置名称");
        return true;
      }
      if (!vm.shareConfig.configValue || !vm.shareConfig.configValue.trim()) {
        alert("请输入配置值");
        return true;
      }

      // 验证配置键格式（只允许字母、数字、下划线）
      var keyPattern = /^[a-zA-Z0-9_]+$/;
      if (!keyPattern.test(vm.shareConfig.configKey)) {
        alert("配置键只能包含字母、数字和下划线");
        return true;
      }

      // 如果是JSON类型，验证JSON格式
      if (vm.shareConfig.configType === "json") {
        try {
          JSON.parse(vm.shareConfig.configValue);
        } catch (e) {
          alert("JSON格式不正确，请检查配置值");
          return true;
        }
      }

      return false;
    },
  },
  mounted: function () {
    // 页面加载完成后执行的操作
  },
});
