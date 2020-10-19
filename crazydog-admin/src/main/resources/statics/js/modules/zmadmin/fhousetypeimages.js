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
            {
                label: '添加图片',
                name: 'state',
                index: 'state',
                width: 50,
                edittype: "button",
                formatter: addImgButton
            }
        ],
        viewrecords: true,
        height: 385,
        postData: {iaType: 2},
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        subGrid: true,//开启子表格支持
        subGridRowExpanded: function (subgrid_id, row_id) {//子表格容器的id和需要展开子表格的行id

            bindSubGrid(subgrid_id, row_id);

        },
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
            // $("#jqGrid").jqGrid('filterToolbar',{searchOperators : true});

        }
    });
});

function showPicture(cellvalue) {
    cellvalue = "http://localhost:81" + cellvalue;
    return "<img src='" + cellvalue + "'  onclick=\'showMax(\"" + cellvalue + "\")\'  height='100' width='100'/>" +
        "<div id='Cover_Div' onClick=\'hideMax()\'></div>";
}

// $("#jqGrid").filterToolbar({searchOperators : true});
var vm = new Vue({
    el: '#rrapp',
    data: {
        dynamicTags: [],
        inputVisible: false,
        bj:true,
        inputValue: '',
        showList: true,
        showAddImg: false,
        title: null,
        fHouseTypeImages: {
            houseId: null

        },
        img: '',
        showImg: false,
        selHouVal: []
    },
    methods: {
        showInput: function () {
            this.inputVisible = true;
            this.$nextTick(
                function () {
                    this.$refs.saveTagInput.$refs.input.focus();
                }
            );
        },
        handleInputConfirm: function () {
            var inputValue = this.inputValue;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            if (this.dynamicTags.length<2){
                this.inputVisible = false;

            }else {
                this.inputVisible = true;
                this.bj = false;
            }
            this.inputValue = '';
        },
        handleClose1: function (tag) {

            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
            if (this.dynamicTags.length<2){
                this.inputVisible = false;
                this.bj = true;
            }




        },

        upload: function (name) {
            var fileObj = document.getElementById(name).files[0]; // js 获取文件对象
            if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                alert("请选择图片");
                return;
            }
            var formData = new FormData();
            formData.append("file", fileObj);
            formData.append("filePage", "house");

            $.ajax({
                url: baseURL + "upload/imgUpload",
                type: "post",
                data: formData,
                processData: false,//不处理数据
                contentType: false,//不处理内容类型
                success: function (r) {
                    // console.info(r);
                    if (r.code == 0) {
                        // var path = "/admin" + r.path;
                        var path = r.urls[0].url;
                        var imgUrl = r.urls[0].imgUrl;

                        vm.img = path;
                        // console.log(imgUrl)
                        vm.fHouseTypeImages.image = imgUrl;
                        vm.showImg = true;


                    } else {
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
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.dynamicTags=[];
            vm.fHouseTypeImages = {iaType: 2};
        },
        update: function (event) {
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.dynamicTags=[];
            vm.getInfo1(itemid)
        },
        saveOrUpdateImg: function (event) {
vm.fHouseTypeImages.saleStatus=$("#saleStatus").val()
            if ($("#houseId").val() == null) {
                alert("请选择楼盘");
                return;
            }
            vm.fHouseTypeImages.houseId = $("#houseId").val()
            if (vm.fHouseTypeImages.title == null || vm.fHouseTypeImages.image == null || vm.fHouseTypeImages.houseId == null
                || vm.fHouseTypeImages.mianJi == null

            ) {

                console.log($("#houseId").val())
                return;
            }
            var reg = /^[0-9]*$/;
            if (!reg.test(vm.fHouseTypeImages.price)) {
                alert('价格应为数字哦')
                return;
            }
            if (vm.dynamicTags.length>0){
                var tag1s = '';
                vm.dynamicTags.forEach(function (value) {
                    tag1s += (value + ';')

                })
                vm.fHouseTypeImages.tags=tag1s;
            }


            var url = vm.fHouseTypeImages.itemid == null ? "sys/fhousetypeimages/save" : "sys/fhousetypeimages/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseTypeImages),
                success: function (r) {
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
            if (vm.fHouseTypeImages.imageAlbumName == null) {
                return;
            }
            var url = vm.fHouseTypeImages.iaId == null ? "sys/fhouseimgsort/save" : "sys/fhouseimgsort/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseTypeImages),
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
                    url: baseURL + "sys/fhousetypeimages/delete",
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
        getInfo1: function (itemid) {
            $.get(baseURL + "sys/fhouseimgsort/info/" + itemid, function (r) {
                vm.fHouseTypeImages = r.fHouseimgSort;


            });
        },
        getInfo: function (itemid) {
            $.get(baseURL + "sys/fhousetypeimages/info/" + itemid, function (r) {
                vm.fHouseTypeImages = r.fHouseTypeImages;
                /**
                 * 标签封装
                 */
                var   str1= r.fHouseTypeImages.tags
                if (str1 == null) {
                    vm.dynamicTags = [];
                } else {
                    var split = str1.split(";");
                    var re = split.filter(function (s) {
                        return s && s.trim();
                    })
                    vm.dynamicTags = re;
                }
                vm.img = 'http://localhost:81' + r.fHouseTypeImages.image;
                $("#saleStatus").val(vm.fHouseTypeImages.saleStatus);
                $("#houseId").val(vm.fHouseTypeImages.houseId);
                renderForm();
                vm.showImg = true;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                // postData:{'username': vm.q.username},
                page: page
            }).trigger("reloadGrid");
        }
    }
});

//重新渲染表单函数
function renderForm() {
    layui.use('form', function() {
        var form = layui.form; //高版本建议把括号去掉，有的低版本，需要加()
        form.render();
    });
}

function bindSubGrid(subgrid_id, collectLineId) {

    var baseimgUrl = "http://localhost:81/sqEditor/"
    var subgrid_table_id;
    subgrid_table_id = subgrid_id + "_t"; // (3)根据subgrid_id定义对应的子表格的table的id
    var subgrid_pager_id;
    subgrid_pager_id = subgrid_id + "_pgr" // (4)根据subgrid_id定义对应的子表格的pager的id

    var cell = $("#jqGrid").getCell(collectLineId, "iaId");
    // console.log(cell);

    // (5)动态添加子报表的table和pager
    $("#" + subgrid_id)
        .html(
            "<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + subgrid_pager_id + "' class='scroll'></div>");

    $("#" + subgrid_table_id)
        .jqGrid(
            {
                url: baseURL + 'sys/fhousetypeimages/list', // (7)子表格数据对应的url，注意传入的contact.id参数
                datatype: "json",
                postData: {sortId: cell},
                viewrecords: true,
                colModel: [{label: '序号', name: 'itemid', index: 'itemid', width: 30, key: true},
                    {label: '图片名称', name: 'title', index: 'title', width: 80},
                    {
                        label: '图片地址', name: 'image', index: 'image', width: 80,

                        formatter: showPicture,
                        edittype: 'file',
                        editoptions: {enctype: "multipart/form-data"},
                    },
                    {label: '楼盘', name: 'houseName', index: 'houseName', width: 80},
                    {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
                    // { label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 },
                    {label: '添加人', name: 'addUserName', index: 'addUserName', width: 50},
                    {label: '标签', name: 'tags', index: 'tags', width: 80},
                    {label: '朝向', name: 'chaoXiang', index: 'chao_xiang', width: 50},
                    {label: '面积', name: 'mianJi', index: 'mian_ji', width: 40},
                    {
                        label: '操作',
                        name: 'state',
                        index: 'state',
                        width: 80,
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

    return "<button class='btn btn-primary ' onclick=\"deleteHouseImg(" + rows.itemid + ")\">删除</button>" +
        "<button class='btn btn-primary ' onclick=\"editImg(" + rows.itemid + ")\">修改</button>";

}

function addImgButton(cellValue, grid, rows, state) {

    return "  <a class='btn btn-primary '  onclick='addImg(" + rows.iaId + ")'><i class='fa fa-plus'></i>&nbsp;添加图片</a>";


}


function addImg(sortId) {
// alert(sortId)
//     console.log(sortId)
    vm.showAddImg = true;
    vm.showList = false;
    vm.fHouseTypeImages.sortId = sortId;
    console.log(vm.showAddImg);

}

function editImg(imgId) {
// alert(sortId)
//     console.log(imgId)
    vm.showAddImg = true;
    vm.showList = false;
    vm.getInfo(imgId)
    // vm.fHouseTypeImages.sortId=sortId;
    // console.log(vm.showAddImg);

}

function deleteHouseImg(imgId) {


    // alert(imgId)
    confirm('确定要删除选中的图片？', function () {
        var url = "sys/fhousetypeimages/delete";
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
