var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "itemid",
            pIdKey: "parentid",
            rootPId: 0
        },
        key: {
            //url:"nourl",
            name: "title"
        }
    }
};
var ztree;


var settingArea = {
    data: {
        simpleData: {
            enable: true,
            idKey: "areaId",
            pIdKey: "areaParentId",
            rootPId: 0
        },
        key: {
            //url:"nourl",
            name: "areaName"
        }
    }
};
var ztreeArea;

var pediatype = {
    id: "catTable",
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
pediatype.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'itemid', field: 'itemid', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '类目名称', field: 'title', align: 'center', valign: 'middle', sortable: false, width: '180px'},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: false, width: '100px'},
        {title: '分类类型', field: 'subjectDetail', align: 'center', valign: 'middle', sortable: false, width: '100px'}]
    return columns;
};


$(function () {
    $.get(baseURL + "sys/fpediatype/list", function (r) {
        var colunms = pediatype.initColumn();
        var table = new TreeTable(pediatype.id, baseURL + "sys/fpediatype/list", colunms);
        table.setRootCodeValue(r.deptId);
        table.setExpandColumn(2);
        table.setIdField("itemid");
        table.setCodeField("itemid");
        table.setParentCodeField("parentid");
        table.setExpandAll(false);
        table.setHeight("100%");
        table.init();
        pediatype.table = table;
    });
});


// $(function () {
//     $("#jqGrid").jqGrid({
//         url: baseURL + 'sys/fpediatype/list',
//         datatype: "json",
//         colModel: [
// 			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true },
// 			{ label: '分类名称', name: 'title', index: 'title', width: 80 },
// 			{ label: '父类', name: 'parentid', index: 'parentid', width: 80 },
// 			{ label: '序排，越大越靠前', name: 'sort', index: 'sort', width: 80 },
// 			{ label: '1房产百科，2装修百科', name: 'subject', index: 'subject', width: 80 },
// 			{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
// 			{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
//         ],
// 		viewrecords: true,
//         height: 385,
//         rowNum: 10,
// 		rowList : [10,30,50],
//         rownumbers: true,
//         rownumWidth: 25,
//         autowidth:true,
//         multiselect: true,
//         pager: "#jqGridPager",
//         jsonReader : {
//             root: "page.list",
//             page: "page.currPage",
//             total: "page.totalPage",
//             records: "page.totalCount"
//         },
//         prmNames : {
//             page:"page",
//             rows:"limit",
//             order: "order"
//         },
//         gridComplete:function(){
//         	//隐藏grid底部滚动条
//         	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
//         }
//     });
// });

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        isHavezxbkType: false,
        isShowType: false,
        fPediaType: {
            itemid: "",
            title: "",
            parentid: "",
            sort: "",
            subject: "",
            updatetime: "",
            areaid: ""
        },
        farea: {
            areaId: "",
            areaParentId: "",
            areaName: "",
            areaSort: ""
        }
    },
    methods: {

        getCat: function () {
            //加载部门树
            $.get(baseURL + "sys/fpediatype/list", function (r) {

                ztree = $.fn.zTree.init($("#fpediatype"), setting, r);
                var node = ztree.getNodeByParam("itemid", vm.fPediaType.parentid);
                ztree.selectNode(node);

                if (vm.fPediaType.parentid != 0 && !vm.fPediaType.parentid === '') {
                    $("#parentName").val(node.title);
                }

            })
        },

        getArea: function () {
            //加载地区树
            $.get(baseURL + "sys/farea/list", function (r) {

                ztreeArea = $.fn.zTree.init($("#area"), settingArea, r.list);
                var node = ztreeArea.getNodeByParam("areaId", vm.farea.areaParentId);
                ztreeArea.selectNode(node);
                if (vm.farea.areaParentId != 0) {
                    $("#areaName").val(node.areaName);
                }

            })
        },


        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.fPediaType = {};

            vm.getCat();
            vm.getArea();
        },
        update: function (event) {

            var id = getCatId();
            if (id == null) {
                return;
            }
            // var itemid = getSelectedRow();
            // if(itemid == null){
            // 	return ;
            // }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            vm.getCat();
            vm.getArea();
        },
        saveOrUpdate: function (event) {
            var url = vm.fPediaType.itemid == null ? "sys/fpediatype/save" : "sys/fpediatype/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fPediaType),
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
            var itemids = getCatId();
            if (itemids == null) {
                return;
            }
            console.log(itemids)


            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fpediatype/delete",
                    // contentType: "application/json",
                    data: {itemids: itemids},
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                // $("#jqGrid").trigger("reloadGrid");
                                location.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (itemid) {
            $.get(baseURL + "sys/fpediatype/info/" + itemid, function (r) {
                vm.fPediaType = r.fPediaType;
            });
        },
        catPage: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择分类",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#catLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    if (node.length == 0) {
                        alert("请选择一个父类")
                        return;
                    } else {
                        vm.fPediaType.parentid = node[0].itemid;
                        $("#parentName").val(node[0].title);

                        vm.fPediaType.subject = node[0].subject;
                        vm.fPediaType.zxbkType = node[0].zxbkType;
                        vm.isHavezxbkType = true;
                        $("input[name='subject']").get(node[0].subject - 1).checked = true;
                        $("input[name='zxbkType']").get(node[0].zxbkType - 1).checked = true;

                        $("input[name='subject']").each(function (t) {

                            if ($("input[name='subject']").get(t).checked == false) {

                                $("input[name='subject']").get(t).disabled = true;
                            }
                        })

                        $("input[name='zxbkType']").each(function (t) {

                            if ($("input[name='zxbkType']").get(t).checked == false) {

                                $("input[name='zxbkType']").get(t).disabled = true;
                            }
                        })
                        layer.close(index);
                    }

                }
            });
        },

        areaPage: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择地区",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#areaLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztreeArea.getSelectedNodes();
                    if (node.length == 0) {
                        alert("请选择一个地区")
                        return;
                    } else {
                        vm.farea.areaParentId = node[0].areaId;
                        $("#areaName").val(node[0].areaName);
                        vm.fPediaType.areaid = node[0].areaId;
                        layer.close(index);
                    }

                }
            });
        },

        reload: function (event) {
            // vm.showList = true;
            // var page = $("#jqGrid").jqGrid('getGridParam','page');
            // $("#jqGrid").jqGrid('setGridParam',{
            //     page:page
            // }).trigger("reloadGrid");
            vm.showList = true;
            pediatype.table.refresh();
        }
    }
});


function getCatId() {
    var selected = $('#catTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}
