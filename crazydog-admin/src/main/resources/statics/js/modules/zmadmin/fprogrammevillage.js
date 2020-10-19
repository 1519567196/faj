$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fprogrammevillage/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {label: '标题', name: 'title', index: 'title', width: 80},
            {label: '地址', name: 'address', index: 'address', width: 80},
            {
                label: '主图',
                name: "image",
                index: "image",
                formatter: showPicture,
                edittype: 'file',
                editoptions: {enctype: "multipart/form-data"},
                width: 100
            },
            {label: '单价', name: 'price', index: 'price', width: 80},
            {label: '小区所在地区', name: 'townName', index: 'townName', width: 80},//省市区拼接
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {label: '添加人员', name: 'addUserName', index: 'addUserName', width: 80},
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


    $("#form-programme-village").bootstrapValidator({

        // live: 'submitted',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证

        // excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的

        // submitButtons: '#submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面

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
});
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
        value: [],
        fileList: [],
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        options: [],
        fProgrammeVillage: {provinceid: 0}
    },
    methods: {
        onSuccess(res) {

            if (res.code==0){
                vm.fProgrammeVillage.image=res.urls[0].imgUrl;
                this.$alert('上传成功！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {

                    },
                })
            }

        },
        handleChange(file, fileList) {
console.log(fileList)
            if (fileList.length > 0) {
                vm.fileList = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件

            }
            if (vm.fileList.length==0){
                vm.fProgrammeVillage.image=null;
            }
        },


        handlePictureCardPreview(file) {
            vm.dialogImageUrl = file.url;
            vm.dialogVisible = true;
        },

        handleRemove(file, fileList) {
            vm.fProgrammeVillage.image=null;
            console.log(file, fileList);
        },
        handleChange1: function (value) {
            console.log(value);

            if (value.length == 3) {
                vm.fProgrammeVillage.provinceid = value[0]
                vm.fProgrammeVillage.cityid = value[1]
                vm.fProgrammeVillage.townid = value[2]
            }

        },
        area: function () {
            var url = "sys/farea/treeList";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                // data: JSON.stringify(vm.fProgrammeVillage),
                success: function (r) {
                    if (r.code === 0) {
                        // console.log(r)
                        vm.options = r.list;
                        vm.setDisable(2, vm.options, 1)
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        setDisable(count, data, minNum) {
            if (count < minNum) { //最多几级就写几
                data.forEach(v => {
                    // v.disabled = true // 超过设定的最大级数,给这一集的数据添加disabled属性
                })
            } else {
                data.forEach(v => {
                    v.disabled = true
                    v.count = count // 设置最外层数据的初始count

                    v.count--;
                    if (v.childList && v.childList.length) {
                        vm.setDisable(v.count, v.childList, minNum) // 子级循环时把这一层数据的count传入

                    }

                })
            }
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.area();
            vm.showList = false;
            vm.title = "新增";
            vm.fProgrammeVillage = {};
            vm.fileList=[];
        },
        update: function (event) {
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.area();
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(itemid)
        },
        saveOrUpdate: function (event) {

        },
        del: function (event) {
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fprogrammevillage/delete",
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
            $.get(baseURL + "sys/fprogrammevillage/info/" + itemid, function (r) {
                vm.fProgrammeVillage = r.fProgrammeVillage;
                var  str=r.fProgrammeVillage.image
                var split = str.split("/");
                vm.fileList=[{name : split[split.length-1],url: "http://localhost:81"+r.fProgrammeVillage.image}]
                vm.value = r.value;
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

$("#form-programme-village").submit(function (ev) {
    if(!$('#form-programme-village').data('bootstrapValidator').isValid()){
        return ;
    }

    var url = vm.fProgrammeVillage.itemid == null ? "sys/fprogrammevillage/save" : "sys/fprogrammevillage/update";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.fProgrammeVillage),
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

$("#form-programme-village").on('success.form.bv', function (e) {
    e.preventDefault();
    var form = $(e.target);

    // console.log("   bvbvbv")
    /**提交代码**/
});