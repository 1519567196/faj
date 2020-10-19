layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage', 'table', 'laytpl'], function () {
    var form = layui.form, table = layui.table, laytpl = layui.laytpl;
    layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    //数据表格
    table.render({
        id: 'materialList',
        elem: '#materialList'
        , url: baseURL + 'sys/fmaterial/list' //数据接口
        , method: 'POST'
        , response: {
            statusCode: 0 //规定成功的状态码，默认：0
        }
        , cellMinWidth: 100
        // ,toolbar : true
        // ,toolbar: '#toolbarDemo'
        , title: '建材主分类列表',
        defaultToolbar: false
        , limit: 10//每页默认数
        , limits: [10, 20, 30, 40, 50]
        , cols: [[ //表头

            {field: 'itemid', title: '序号', align: 'center', width: 60,}
            , {
                field: 'childCount', title: '子分类', align: 'center', width: 100,
                event: 'collapse',
                templet: function (d) {
                    return '<div style="position: relative;\n' + '    padding: 0 10px 0 20px;">'
                        + '点击展开<i style="left: 0px;" lay-tips="展开" class="layui-icon layui-colla-icon layui-icon-right"></i></div>'
                }
            }
            , {
                field: 'type', title: '软装/硬装', align: 'center',
                templet: function (d) {
                	if (d.type==1)
					{
						return '硬装（建材）';
					}else if (d.type==2){
                		return '软装';
					}


                },
                width: 150,
            }
            , {field: 'title', title: '主分类名称', align: 'center', width: 150,}
            , {field: 'addtime', title: '添加时间', align: 'center', width: 100}
            , {field: 'addUserName', title: '添加人', align: 'center', width: 80}
            , {field: 'updatetime', title: '修改时间', align: 'center', width: 100}
            , {title: '操作', toolbar: '#barGet', align: 'center', width: 250,}

        ]]
        , page: true //开启分页
        , loading: true
        , where: {timestamp: (new Date()).valueOf()}
        , done: function (res, curr, count) {
            console.log(res);

        }

    });

    // 表格初始化 ------------------
    function init() {
        // 列表请求
        send(postData);
        // 完成表格数据
        $.extend(infoOptions, {data: data});
        infoOptions.page = {
            curr: 1
        }
        console.log("data1  " + data[0].id);
        table.render(infoOptions);

        data = null;
    }

    table.on('tool(tbList1)', function (obj) {
        var data = obj.data;
        if (obj.event === 'editC') {
            vm.updateC(data.itemid);
        } else if (obj.event === 'delC') {
            vm.delC([data.itemid]);
        }


    })


    //监听工具条
    table.on('tool(materialList)', function (obj) {

        console.log(obj);
        var data = obj.data;
        if (obj.event === 'addChild') {

            vm.addC(data.itemid, data.title);


        } else if (obj.event === 'del') {
            vm.del([data.itemid]);


        } else if (obj.event === 'edit') {
            vm.update([data.itemid]);


        }
        if (obj.event === 'collapse') {
            var trObj = layui.$(this).parent('tr');//当前行

            var accordion = true //开启手风琴，那么在进行折叠操作时，始终只会展现当前展开的表格。
            var content = '<table  lay-filter="tbList1"> </table>';//内容
            //表格行折叠方法
            collapseTable({
                elem: trObj,
                accordion: accordion,
                content: content,
                success: function (trObjChildren, index) { //成功回调函数
                    //trObjChildren 展开tr层DOM
                    //index 当前层索引
                    trObjChildren.find('table').attr("id", index);
                    table.render({
                        elem: "#" + index,
                        url: baseURL + 'sys/fmaterialtype/list',//---控制层接口
                        limit: 3,
                        // toolbar : true,
                        // toolbar: '#toolbarDemo1',
                        cellMinWidth: 80,
                        defaultToolbar: [],
                        where: {mainId: data.itemid},
                        cols: [[

                            {
                                field: 'itemid',
                                width: 80,
                                title: '序号',
                                sort: true
                            },
                            {
                                field: 'type',
                                title: '分类名称'
                                ,
                                width: 180,
                            },
                            {
                                field: 'addtime',
                                title: '添加时间'
                                ,
                                width: 180,
                            },
                            {
                                field: 'updatetime',
                                title: '修改时间'
                                ,
                                width: 180,
                            },

                            {
                                title: '操作', toolbar: '#barGet1', align: 'center', width: 180
                            }]],


                    });

                }
            });

        }
    });

})

function collapseTable(options) {
    var trObj = options.elem;
    if (!trObj) return;
    var accordion = options.accordion,
        success = options.success,
        content = options.content || '';
    var tableView = trObj.parents('.layui-table-view'); //当前表格视图
    var id = tableView.attr('lay-id'); //当前表格标识
    var index = trObj.data('index'); //当前行索引
    var leftTr = tableView.find('.layui-table-fixed.layui-table-fixed-l tr[data-index="' + index + '"]'); //左侧当前固定行
    var rightTr = tableView.find('.layui-table-fixed.layui-table-fixed-r tr[data-index="' + index + '"]'); //右侧当前固定行
    var colspan = trObj.find('td').length; //获取合并长度
    var trObjChildren = trObj.next(); //展开行Dom
    var indexChildren = id + '-' + index + '-children'; //展开行索引
    var leftTrChildren = tableView.find('.layui-table-fixed.layui-table-fixed-l tr[data-index="' + indexChildren + '"]'); //左侧展开固定行
    var rightTrChildren = tableView.find('.layui-table-fixed.layui-table-fixed-r tr[data-index="' + indexChildren + '"]'); //右侧展开固定行
    var lw = leftTr.width() + 15; //左宽
    var rw = rightTr.width() + 15; //右宽
    //不存在就创建展开行
    if (trObjChildren.data('index') != indexChildren) {
        //装载HTML元素
        var tr = '<tr data-index="' + indexChildren + '"><td colspan="' + colspan + '"><div style="height: auto;padding-left:' + lw + 'px;padding-right:' + rw + 'px" class="layui-table-cell">' + content + '</div></td></tr>';
        trObjChildren = trObj.after(tr).next().hide(); //隐藏展开行
        var fixTr = '<tr data-index="' + indexChildren + '"></tr>';//固定行
        leftTrChildren = leftTr.after(fixTr).next().hide(); //左固定
        rightTrChildren = rightTr.after(fixTr).next().hide(); //右固定
    }
    //展开|折叠箭头图标
    trObj.find('td[lay-event="collapse"] i.layui-colla-icon').toggleClass("layui-icon-right layui-icon-down");
    //显示|隐藏展开行
    trObjChildren.toggle();
    //开启手风琴折叠和折叠箭头
    if (accordion) {
        trObj.siblings().find('td[lay-event="collapse"] i.layui-colla-icon').removeClass("layui-icon-down").addClass("layui-icon-right");
        trObjChildren.siblings('[data-index$="-children"]').hide(); //展开
        rightTrChildren.siblings('[data-index$="-children"]').hide(); //左固定
        leftTrChildren.siblings('[data-index$="-children"]').hide(); //右固定
    }
    success(trObjChildren, indexChildren); //回调函数
    heightChildren = trObjChildren.height(); //展开高度固定
    rightTrChildren.height(heightChildren + 115).toggle(); //左固定
    leftTrChildren.height(heightChildren + 115).toggle(); //右固定
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        fMaterial: {},
        showListC: true,
        titleC: null,
        fMaterialType: {}
    },
    methods: {

        addC: function (e, n) {
            vm.showListC = false;
            vm.showList = false;
            vm.titleC = "新增";
            vm.fMaterialType = {mainMateriaId: e, mainMateriaName: n};
        },
        updateC: function (event) {
            var itemid = event;
            if (itemid == null) {
                return;
            }
            vm.showListC = false;
            vm.showList = false;
            vm.titleC = "修改";

            vm.getInfoC(itemid)
            vm.fMaterialType.mainMateriaName = title;
        },
        saveOrUpdateC: function (event) {
            var url = vm.fMaterialType.itemid == null ? "sys/fmaterialtype/save" : "sys/fmaterialtype/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fMaterialType),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        delC: function (event) {
            var itemids = event;
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fmaterialtype/delete",
                    contentType: "application/json",
                    data: JSON.stringify(itemids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                location.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfoC: function (itemid) {
            $.get(baseURL + "sys/fmaterialtype/info/" + itemid, function (r) {
                vm.fMaterialType = r.fMaterialType;
            });
        },


        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.fMaterial = {};
        },
        update: function (event) {
            var itemid = event;
            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(itemid)
        },
        saveOrUpdate: function (event) {
            var url = vm.fMaterial.itemid == null ? "sys/fmaterial/save" : "sys/fmaterial/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fMaterial),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            //刷新页面
                            location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var itemids = event;
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fmaterial/delete",
                    contentType: "application/json",
                    data: JSON.stringify(itemids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
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
            $.get(baseURL + "sys/fmaterial/info/" + itemid, function (r) {
                vm.fMaterial = r.fMaterial;
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
