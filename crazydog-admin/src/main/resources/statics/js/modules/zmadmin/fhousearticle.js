$(function () {
    vm.getOptions();
    vm.getOptions3();
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fhousearticle/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {label: '标题', name: 'title', index: 'title', width: 80},
            {label: '摘要', name: 'subTitle', index: 'sub_title', width: 80},
            {label: '浏览量', name: 'views', index: 'views', width: 80},
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {label: '添加人', name: 'addUserName', index: 'add_userid', width: 80},
            {label: '材料文章分类', name: 'articleTypeName', index: 'articleTypeName', width: 80},
            {label: '所属地区', name: 'areaName', index: 'areaid', width: 80},
            {label: '楼盘', name: 'houseName', index: 'house_id', width: 80},
            {label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80}
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
        fHouseArticle: {},
        selOneVal: [{areaId: '0', areaName: '请选择省'}],
        selVal: [],
        selTwoVal: [{areaId: '0', areaName: '请选择市'}],
        selThreeVal: [{areaId: '0', areaName: '请选择区'}],
        selHouVal: [],
        selArticleType: [],
        selected1: 0,
        selected2: 0,
        selected3: 0

    },

    methods: {

        getOptions: function () {
            var url = "sys/farea/list";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({}),
                success: function (r) {
                    if (r.code === 0) {
                        vm.selOneVal.length = 1;
                        for (var i = 0; i < r.list.length; i++) {
                            if (r.list[i].areaDeep == 1) {
                                vm.selOneVal.push(r.list[i])
                            } else if (r.list[i].areaDeep == 2) {
                                vm.selTwoVal.push(r.list[i])
                            } else if (r.list[i].areaDeep == 3) {
                                vm.selThreeVal.push(r.list[i])
                            }
                        }
                        vm.selVal = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });

        },
        getOptions33: function () {
            for (var i = 0; i < vm.selVal.length; i++) {
                if (vm.selVal[i].areaId == vm.selected3) {
                    for (var o = 0; o < vm.selVal.length; o++) {
                        if (vm.selVal[o].areaDeep == 2 && vm.selVal[o].areaId == vm.selVal[i].areaParentId) {
                            vm.selected2 = vm.selVal[o].areaId;
                            for (var j = 0; j < vm.selVal.length; j++) {
                                if (vm.selVal[j].areaId == vm.selected2) {
                                    for (var oo = 0; oo < vm.selVal.length; oo++) {
                                        if (vm.selVal[oo].areaDeep == 1 && vm.selVal[oo].areaId == vm.selVal[j].areaParentId) {
                                            vm.selected1 = vm.selVal[oo].areaId;
                                            return;


                                        }
                                    }

                                }
                            }


                            break;
                        }

                    }
                }
            }
        },

        getOptions2: function () {

            vm.selThreeVal.length = 1;
            // alert(vm.selected2);
            for (var i = 0; i < vm.selVal.length; i++) {

                if (vm.selVal[i].areaDeep == 3 && vm.selVal[i].areaParentId == vm.selected2) {

                    vm.selThreeVal.push(vm.selVal[i]);


                } else if (vm.selVal[i].areaId == vm.selected2) {
                    for (var o = 0; o < vm.selVal.length; o++) {
                        if (vm.selVal[o].areaDeep == 1 && vm.selVal[o].areaId == vm.selVal[i].areaParentId) {
                            vm.selected1 = vm.selVal[o].areaId;
                            break;
                        }

                    }


                }
            }

        },
        getArticleType: function () {
            var url = "sys/farticletype/onlylist";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({atType:2}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selArticleType = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

        getOptions3: function () {
            var url = "sys/fhouses/onlyList";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selHouVal = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getOptions1: function () {
            vm.selTwoVal.length = 1;
            vm.selThreeVal.length = 1;
            for (var i = 0; i < vm.selVal.length; i++) {
                if (vm.selVal[i].areaDeep == 2 && vm.selVal[i].areaParentId == vm.selected1) {
                    vm.selTwoVal.push(vm.selVal[i])
                }
            }
        },

        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.getArticleType();
            vm.fHouseArticle = {};
        },
        update: function (event) {
            var itemid = getSelectedRow();

            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getArticleType();
            vm.getInfo(itemid)
        },
        saveOrUpdate: function (event) {
            if (vm.fHouseArticle.title == null || vm.fHouseArticle.content == null
                || vm.fHouseArticle.articleTypeId == null || vm.selected1 == null || vm.fHouseArticle.houseId == null) {
                alert("您还有必填项未填写！");
                return;

            }
            if (vm.selected3 != 0) {
                vm.fHouseArticle.areaid = vm.selected3;
            } else {
                if (vm.selected2 != 0) {
                    vm.fHouseArticle.areaid = vm.selected2;
                } else {
                    vm.fHouseArticle.areaid = vm.selected1;
                }
            }

            var url = vm.fHouseArticle.itemid == null ? "sys/fhousearticle/save" : "sys/fhousearticle/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseArticle),
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
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fhousearticle/delete",
                    contentType: "application/json",
                    data: JSON.stringify(itemids),
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
        getInfo: function (itemid) {
            $.get(baseURL + "sys/fhousearticle/info/" + itemid, function (r) {
                vm.fHouseArticle = r.fHouseArticle;
                // vm.selThreeVal = vm.selVal;
                // vm.selTwoVal = vm.selVal;

                for (var i = 0; i < vm.selVal.length; i++) {

                    if (vm.selVal[i].areaDeep == 3 && vm.selVal[i].areaId == r.fHouseArticle.areaid) {


                        vm.selected3 = r.fHouseArticle.areaid;
                        for (var j = 0; j < vm.selVal.length; j++) {
                            if (vm.selVal[j].areaDeep == 2 && vm.selVal[j].areaId == vm.selVal[i].areaParentId) {
                                vm.selected2 = vm.selVal[j].areaId;


                                for (var k = 0; k < vm.selVal.length; k++) {
                                    if (vm.selVal[k].areaDeep == 1 && vm.selVal[k].areaId == vm.selVal[j].areaParentId) {
                                        vm.selected1 = vm.selVal[k].areaId;
                                        break;

                                    }
                                }


                            }
                        }

                    } else {
                        if (vm.selVal[i].areaDeep == 2 && vm.selVal[i].areaId == r.fHouseArticle.areaid) {
                            vm.selected2 = r.fHouseArticle.areaid;
                            for (var k = 0; k < vm.selVal.length; k++) {
                                if (vm.selVal[k].areaDeep == 1 && vm.selVal[k].areaId == vm.selVal[i].areaParentId) {
                                    vm.selected1 = vm.selVal[k].areaId;
                                    return;

                                }
                            }

                        } else if (vm.selVal[i].areaDeep == 1 && vm.selVal[i].areaId == r.fHouseArticle.areaid) {
                            vm.selected1 = vm.selVal[i].areaId;
                        }
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }


});