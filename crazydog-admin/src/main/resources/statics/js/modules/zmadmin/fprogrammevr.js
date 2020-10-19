$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fprogrammevr/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {label: '标题', name: 'title', index: 'title', width: 80},
            {label: 'vr链接', name: 'vrUrl', index: 'vr_url', width: 80},
            {
                label: 'vr首图', name: 'vrImg', index: 'vr_img',
                formatter: showPicture,
                edittype: 'file',
                editoptions: {enctype: "multipart/form-data"},
                width: 80
            },
            {
                label: '是否首页推荐', name: 'indexpush', index: 'indexpush',
                formatter: function (cellvalue) {
                    if (cellvalue == 1) {
                        return '<i class="fa fa-hand-rock-o" aria-hidden="true"></i>首页推荐'
                    } else {
                        return ''
                    }

                },
                width: 80
            },
            {label: '所属户型', name: 'programmeHouseName', index: 'programmeHouseName', width: 80},
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {label: '添加人', name: 'addUserName', index: 'addUserName', width: 80},
            {label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80},
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

function bootstrapVal() {


    $("#form-programme-vr").bootstrapValidator({

        // live: 'submitted',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证

        // excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的

        // submitButtons: '.submitVR',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面

        message: '通用的验证失败消息',//好像从来没出现过

        feedbackIcons: {//根据验证结果显示的各种图标

            valid: 'glyphicon glyphicon-ok',

            invalid: 'glyphicon glyphicon-remove',

            validating: 'glyphicon glyphicon-refresh'

        },

        fields: {

            required: {
                message: '必填',
                validators: {
                    notEmpty: {
                        message: '必填'
                    }
                }
            },
            number: {
                message: '数字',
                validators: {
                    regexp: {//正则验证

                        regexp: /^[1-9]\d{0,9}$/,


                        message: '整数最多10位'

                    },
                    notEmpty: {
                        message: '必填'
                    }
                }
            },


        }

    });
}

function showPicture(cellvalue) {
    return "<img src='" + cellvalue + "'  onclick=\'handlePictureBig(\"" + cellvalue + "\")\'  height='100' width='100'/>"
    // +
    // "<div id='Cover_Div' onClick=\'hideMax()\'></div>";
}

function handlePictureBig(fileurl) {
    console.log(fileurl)
    vm.dialogImageUrl = fileurl;
    vm.dialogVisible = true;

}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        fileList: [],
        dialogImageUrl: '',
        probablyDeleteImg: '',
        dialogVisible: false,
        selPHouseVal: [],
        fProgrammeVr: {}
    },
    methods: {
        indexPush: function () {
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }

            confirm('确定要将此条vr放置在首页推荐？', function (message) {
                $.get(baseURL + "sys/fprogrammevr/indexPush/" + itemid, function (r) {
                    if (r.code == 0) {
                        alert('操作成功', function (index) {
                            $("#jqGrid").trigger("reloadGrid");
                        });
                    } else {
                        alert(r.msg);
                    }
                });
            });


        },
        getOptions3: function () {
            var url = "sys/fprogrammehouse/onlyList";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selPHouseVal = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        onSuccess(res) {

            if (res.code == 0) {
                vm.fProgrammeVr.vrImg = res.urls[0].imgUrl;
                vm.probablyDeleteImg = res.urls[0].upload;
                this.$alert('上传成功！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {

                    },
                })
            }

        },
        handleChange(file, fileList) {
            // console.log(fileList)
            if (fileList.length > 0) {
                vm.fileList = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件

            }

        },


        handlePictureCardPreview(file) {
            vm.dialogImageUrl = file.url;
            vm.dialogVisible = true;
        },

        handleRemove(file, fileList) {
            // console.log('    1213')
            vm.fProgrammeVr.vrImg = null;
            // console.log(vm.probablyDeleteImg);
            var delUrl = vm.probablyDeleteImg;

            $.get(baseURL + "upload/deleteFile/?delUrl=" + delUrl, function (r) {
                if (r.code == 0) {
                    alert("删除成功")
                }

            });

        },
        query: function () {
            vm.reload();
        },
        add: function () {
            // $("#form-programme-vr").data('bootstrapValidator').destroy();
            $('#form-programme-vr').data('bootstrapValidator', null);
            bootstrapVal();
            vm.getOptions3();
            vm.showList = false;
            vm.title = "新增";
            vm.fProgrammeVr = {};
            vm.fileList = [];
        },
        update: function (event) {
            // $("#form-programme-vr").data('bootstrapValidator').destroy();
            $('#form-programme-vr').data('bootstrapValidator', null);
            bootstrapVal();
            vm.getOptions3();
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.fileList = [];
            vm.getInfo(itemid)
        },
        saveOrUpdate: function (event) {


        },
        del: function (event) {
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function (message) {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fprogrammevr/delete",
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
            $.get(baseURL + "sys/fprogrammevr/info/" + itemid, function (r) {
                vm.fProgrammeVr = r.fProgrammeVr;
                var str = r.fProgrammeVr.vrImg
                var split = str.split("/");
                vm.fileList = [{name: split[split.length - 1], url: "http://localhost:81" + r.fProgrammeVr.vrImg}]
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
$("#form-programme-vr").submit(function (ev) {
    console.log("   jjj")

    if (vm.fProgrammeVr.vrImg == null || undefined == vm.fProgrammeVr.vrImg) {
        alert("请上传图片");
        return;
    }
    if ((!$('#form-programme-vr').data('bootstrapValidator').isValid())) {
        return;
    }

    var url = vm.fProgrammeVr.itemid == null ? "sys/fprogrammevr/save" : "sys/fprogrammevr/update";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.fProgrammeVr),
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

/**
 * 解决表单重复提交
 */
$("#form-programme-vr").on('success.form.bv', function (e) {
    e.preventDefault();
    var form = $(e.target);

    // console.log("   bvbvbv")
    /**提交代码**/
});
