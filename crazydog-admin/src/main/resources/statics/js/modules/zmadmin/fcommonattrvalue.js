$(function () {
	vm.getOptions1();
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fcommonattrvalue/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'valueId', index: 'value_id', width: 50, key: true},
            {label: '属性值', name: 'title', index: 'title', width: 80},
            {label: '绑定属性', name: 'attrName', index: 'attrName', width: 80},
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        type:'0',
        attrtype:'0',
        selAttrVal: [],
        selAttrValS: [{title:"全部",itemid:null}],
		// attrIdS:null,
        fCommonAttrValue: {}
    },
    methods: {
        getSelAttrVal:function(type){
            console.log(type)
            var url = "sys/fcommonattr/onlylist";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({type:type}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selAttrVal = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

		getOptions1: function () {
			var url = "sys/fcommonattr/onlylist";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify({}),
				success: function (r) {
					if (r.code === 0) {

						// console.log(vm.selAttrValS)
						r.list.push(vm.selAttrValS[0])
						vm.selAttrValS = r.list;
						// console.log(vm.selAttrVal)


					} else {
						alert(r.msg);
					}
				}
			});
		},
        getOptions3: function () {

        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.selAttrVal=[];
            vm.getOptions3();
            vm.showList = false;
            vm.title = "新增";
            vm.type = "0";
            vm.fCommonAttrValue = {};
        },
        update: function (event) {
            vm.selAttrVal=[];
            vm.type = "0";
            vm.getOptions3();
            var valueId = getSelectedRow();
            if (valueId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(valueId)
        },
        saveOrUpdate: function (event) {
            var url = vm.fCommonAttrValue.valueId == null ? "sys/fcommonattrvalue/save" : "sys/fcommonattrvalue/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fCommonAttrValue),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var valueIds = getSelectedRows();
            if (valueIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fcommonattrvalue/delete",
                    contentType: "application/json",
                    data: JSON.stringify(valueIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (valueId) {
            $.get(baseURL + "sys/fcommonattrvalue/info/" + valueId, function (r) {
                vm.fCommonAttrValue = r.fCommonAttrValue;
                vm.getSelAttrVal(r.fCommonAttrValue.type);
           vm.type = r.fCommonAttrValue.type+'';
           console.log(vm.type)
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
				postData:{'attrId': vm.fCommonAttrValue.attrIdS},
                page: page
            }).trigger("reloadGrid");
        }
    }
});