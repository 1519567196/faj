$(function () {
    vm.getOptions3();
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fhouseimgsort/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'iaId', index: 'iaId', width: 50, key: true},
            {label: '相册名称', name: 'imageAlbumName', index: 'imageAlbumName', width: 80},
            {label: '相册图片数量', name: 'imgCount', index: 'imgCount', width: 80},
            {label: '添加时间', name: 'addTime', index: 'addTime', width: 80},
            {label: '添加人', name: 'addUserName', index: 'addUserId', width: 80},
            // {
            //     label: '添加图片',
            //     name: 'state',
            //     index: 'state',
            //     width: 50,
            //     edittype: "button",
            //     formatter: addImgButton
            // }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        subGrid: true,//开启子表格支持
        subGridOptions: {
        	// plusicon: 'ui-icon-plus',
        	// minusicon: 'ui-icon-minus',
        	// openicon: 'ui-icon-carat-1-sw',
            // height: 10000,
        	expandOnLoad: true,
        	selectOnExpand: false,
        	reloadOnExpand: false
        },
        subGridRowExpanded: function (subgrid_id, row_id) {//子表格容器的id和需要展开子表格的行id

            bindSubGrid(subgrid_id, row_id);

        },
        multiselect: true,
        pager: "#jqGridPager",
        postData: {iaType: 1},
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

function showPicture(cellvalue) {
    cellvalue="http://localhost:81"+cellvalue;
    return "<img src='" + cellvalue + "'  onclick=\'showMax(\"" + cellvalue + "\")\'  height='100' width='100'/>" +
        "<div id='Cover_Div' onClick=\'hideMax()\'></div>";
}

// function addImgButton(cellValue, grid, rows, state) {
//
//     return "<button class='btn btn-primary '  @click='"+addImg( rows.iaId)+"'>添加图片</button>";
//
//
// }

// function addImg(sortId) {
// // alert(sortId)
//     console.log(sortId)
//     vm.showAddImg=true;
// }

function bindSubGrid(subgrid_id, collectLineId) {
console.log(collectLineId)
    var baseimgUrl = "http://localhost:81/sqEditor/"
    var subgrid_table_id;
    subgrid_table_id = subgrid_id + "_t"; // (3)根据subgrid_id定义对应的子表格的table的id jqGrid_2_expandedContent
    var subgrid_pager_id;
    subgrid_pager_id = subgrid_id + "_pgr" // (4)根据subgrid_id定义对应的子表格的pager的id

    var cell = $("#jqGrid").getCell(collectLineId, "iaId");
    // console.log(cell);
    var newHeight = 10000;
    // (5)动态添加子报表的table和pager
    $("#" + subgrid_id)
        .html(
            "<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + subgrid_pager_id + "' class='scroll'></div>");

    $("#" + subgrid_table_id)
        .jqGrid(
            {
                url: baseURL + 'sys/fhouseimages/list', // (7)子表格数据对应的url，注意传入的contact.id参数
                datatype: "json",
                postData: {sortId: cell},
                height: 500,
                viewrecords: true,
                colModel: [{
                    label: '序号',
                    name: "itemid",
                    index: "itemid",
                    width: 50,
                    key: true
                }, {
                    label: '名称',
                    name: "title",
                    index: "title",
                    width: 80,
                    align: "left"
                }, {
                    label: '图片',
                    name: "image",
                    index: "image",
                    formatter: showPicture,
                    edittype: 'file',
                    editoptions: {enctype: "multipart/form-data"},
                    width: 100
                }, {
                    label: '所属楼盘',
                    name: "houseName",
                    index: "houseName",
                    width: 80,
                    align: "left"
                },
                    {
                        label: '添加时间',
                        name: "addtime",
                        index: "addtime",
                        width: 80,
                        align: "left"
                    }, {
                        label: '上传人',
                        name: "addUserName",
                        index: "addUserName",
                        width: 50,
                        align: "left"

                    },
                    {
                        label: '操作',
                        name: 'state',
                        index: 'state',
                        width: 50,
                        edittype: "button",
                        formatter: cmgStateFormat
                    },
                ],
                rowNum: 10,
                rolList: [10, 20, 30],
                pager: subgrid_pager_id,
                mtype: "post",
                viewrecord: true,
                jsonReader: {
                    root: "page.list",
                    page: "page.currPage",
                    total: "page.totalPage",
                    records: "page.totalCount"
                },

            });

}

function cmgStateFormat(cellValue, grid, rows, state) {

    return "<button class='btn btn-primary ' onclick=\"deleteHouseImg(" + rows.itemid + ")\">删除</button>";

}


function deleteHouseImg(imgId) {


    // alert(imgId)
    confirm('确定要删除选中的图片？', function () {
        var url = "sys/fhouseimages/delete";
        $.ajax({
            type: "POST",
            url: baseURL + url,
            contentType: "application/json",
            data: JSON.stringify([imgId]),
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
    });
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        showAddImg: false,
        title: null,
        selHouVal: [],
        fHouseImages: {},
        imgs:[],
        showImg:false,

    },
    methods: {

        upload: function(name){
            var fileObj = document.getElementById(name).files[0]; // js 获取文件对象
            if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                alert("请选择图片");
                return;
            }
            var formData=  new FormData();
            formData.append("file",fileObj);
            formData.append("filePage","house");

            $.ajax({
                url: baseURL + "upload/imgUpload",
                type: "post",
                data: formData,
                processData: false,//不处理数据
                contentType: false,//不处理内容类型
                success: function (r) {
                    console.info(r);
                    if(r.code == 0){
                        // var path = "/admin" + r.path;
                        var path =  r.urls[0].url;
                        var imgUrl =  r.urls[0].imgUrl;

                        vm.imgs.push(path);
                        // console.log(imgUrl)
                        vm.fHouseImages.image=imgUrl;
                            vm.showImg = true;


                    }else{
                        alert(r.msg);
                    }
                }

            })
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
        addImg:function(){
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            console.log(itemid)
            vm.showList = false;
            vm.showAddImg=true;
   vm.getInfo(itemid)
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.showAddImg = false;
            vm.title = "新增";
            vm.fHouseImages = {
                iaType:1
            };
        },
        update: function (event) {
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }

            console.log(itemid)
            vm.showList = false;

            vm.title = "修改";

            vm.getInfo(itemid);


        },
        saveImg: function (event) {
            // vm.fHouseImages.image=vm.imgs[0];
           vm.fHouseImages.sortId= vm.fHouseImages.iaId
            var url = "sys/fhouseimages/save";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseImages),
                success: function (r) {
                    // console.log(r)
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.showAddImg = false;
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.fHouseImages.iaId == null ? "sys/fhouseimgsort/save" : "sys/fhouseimgsort/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseImages),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.showAddImg=false;
                            vm.reload();
                           // window.location.reload();
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
                    url: baseURL + "sys/fhouseimgsort/delete",
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
            $.get(baseURL + "sys/fhouseimgsort/info/" + itemid, function (r) {
                vm.fHouseImages=r.fHouseimgSort;

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