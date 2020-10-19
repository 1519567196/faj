$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/ffitupcompanycases/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'caseId', index: 'case_id', width: 50, key: true},
            {label: '案例名称', name: 'title', index: 'title', width: 80},
            {
                label: '主图', name: 'mainImg', index: 'main_img',
                formatter: showPicture,
                edittype: 'file',
                editoptions: {enctype: "multipart/form-data"},

                width: 80
            },
            {label: '标签', name: 'tags', index: 'tags', width: 80},
            {label: '所属企业', name: 'companyName', index: 'companyName', width: 80},
            {label: '面积', name: 'squareNum', index: 'squareNum', width: 80},
            {label: '户型', name: 'houseType', index: 'house_typeid', width: 80},
            {label: '风格', name: 'style', index: 'style', width: 80},
            {
                label: '公装/家装', name: 'isPublic', index: 'isPublic',
                formatter: function (cellvalue) {
                    if (cellvalue == 1) {
                        return '公装'
                    } else if (cellvalue == 2) {
                        return '家装'
                    } else {
                        return '未知'
                    }
                },
            },
            {
                label: '是否推荐，默认0,1推荐', name: 'recommend', index: 'recommend',
                formatter: function (cellvalue) {
                    if (cellvalue == 0) {
                        return '不推荐'
                    } else if (cellvalue == 1) {
                        return '推荐'
                    }
                },

                width: 80
            },
            {label: '浏览量', name: 'views', index: 'views', width: 80},
            // { label: '地区', name: 'areaName', index: 'areaName', width: 80 },
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {label: '添加人', name: 'adduserName', index: 'adduserName', width: 80},
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

function bootstrapVal() {


    $("#fitup-companyCases-form").bootstrapValidator({

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
            jingdu: {
                message: '经度',
                validators: {
                    regexp: {//正则验证

                        regexp: /^(((\d|[1-9]\d|1[1-7]\d|0)\.\d{0,4})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,4}|180)$/,

                        message: '请输入正确格式的经度'

                    },
                }
            },

            weidu: {
                message: '纬度',
                validators: {
                    regexp: {//正则验证

                        regexp: /^([0-8]?\d{1}\.\d{0,4}|90\.0{0,4}|[0-8]?\d{1}|90)$/,

                        message: '请输入正确格式的纬度'

                    },
                }
            },

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

                        regexp: /^([1-5]?|[0-4]\.\d)$/,


                        message: '满分5分,一位小数'

                    },
                    notEmpty: {
                        message: '必填'
                    }
                }
            },
            number5: {
                message: '数字',
                validators: {
                    regexp: {//正则验证

                        regexp: /^[1-9]\d{0,4}$/,


                        message: '整数最多5位'

                    }
                }
            },


        }

    });
}

function showPicture(cellvalue) {
    if (cellvalue == null) {
        return "暂无数据"
    }
    return "<img src='" + cellvalue + "'  onclick=\'handlePictureBig(\"" + cellvalue + "\")\'  height='100' width='100'/>"
}

function handlePictureBig(fileurl) {
    // console.log(fileurl)
    vm.dialogImageUrl = fileurl;
    vm.dialogVisible = true;

}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        activeNames: ['1'],//折叠面板用
        fileList: [],//图片上传用
        probablyDeleteImg: '',//图片上传用
        dialogVisible: false,//图片放大用
        dialogImageUrl: '',//图片放大用
        dynamicTags: [],//标签
        bj: true,//为了标签数量限制用
        inputVisible: false,
        inputValue: '',//标签输入值
        selCompanyVal: [],//选择企业用
        selSquareVal: [],//选择面积用
        selHouseTypeVal: [],//选择户型用
        selStyleVal: [],//选择风格用
        selMoneyVal: [],//选择预算用
        selBusinessDistrictVal: [],//选择商圈用
        selPublicTypeVal: [],//选择公装类型用
        fFitupCompanyCases: {content:''},
    },
    methods: {

        //图片上传
        onSuccess(res) {

            if (res.code == 0) {
                vm.fFitupCompanyCases.mainImg = res.urls[0].imgUrl;
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
            vm.fFitupCompanyCases.mainImg = null;
            // console.log(vm.probablyDeleteImg);
            var delUrl = vm.probablyDeleteImg;

            $.get(baseURL + "upload/deleteFile/?delUrl=" + delUrl, function (r) {
                if (r.code == 0) {
                    alert("删除成功")
                }

            });

        },

        //获取属性值下拉数据
        getOptions1: function (e) {
            var url = "sys/fcommonattrvalue/onlylist";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({attrId: e}),
                success: function (r) {
                    if (r.code === 0) {
                        if (e == 4) {
                            vm.selSquareVal = r.list;
                        } else if (e == 3) {
                            vm.selBusinessDistrictVal = r.list;
                        } else if (e == 5) {
                            vm.selStyleVal = r.list;
                        } else if (e == 6) {
                            vm.selMoneyVal = r.list;
                        } else if (e == 7) {
                            vm.selPublicTypeVal = r.list;
                        } else if (e == 8) {
                            vm.selHouseTypeVal = r.list;
                        }


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        setAttrValue: function () {
            vm.getOptions1(3);
            vm.getOptions1(4);
            vm.getOptions1(5);
            vm.getOptions1(6);
            vm.getOptions1(7);
            vm.getOptions1(8);


        },

        //获取企业下拉数据
        getOptions3: function () {
            var url = "sys/ffitupcompany/onlyList";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selCompanyVal = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

        //标签用
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
            if (this.dynamicTags.length < 3) {   //  处理标签数量限制
                this.inputVisible = false;

            } else {
                this.inputVisible = true;
                this.bj = false;
            }
            this.inputValue = '';
        },
        //标签关闭
        handleClose1: function (tag) {

            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
            if (this.dynamicTags.length < 3) {
                this.inputVisible = false;
                this.bj = true;
            }


        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.dynamicTags = [];
            vm.fileList = [];
            vm.getOptions3();
            vm.setAttrValue();
            bootstrapVal()
            $("#companyCasesContent").val('');
            vm.fFitupCompanyCases = {};
        },
        update: function (event) {
            var caseId = getSelectedRow();
            if (caseId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.setAttrValue();
            $('#fitup-companyCases-form').data('bootstrapValidator', null);
            bootstrapVal()
            vm.fileList = [];
            // $("#companyCasesContent").val('');
            vm.dynamicTags = [];
            vm.getOptions3();
            vm.getInfo(caseId)
        },
        saveOrUpdate: function (event) {

        },
        del: function (event) {
            var caseIds = getSelectedRows();
            if (caseIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/ffitupcompanycases/delete",
                    contentType: "application/json",
                    data: JSON.stringify(caseIds),
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
        getInfo: function (caseId) {
            $.get(baseURL + "sys/ffitupcompanycases/info/" + caseId, function (r) {
                vm.fFitupCompanyCases = r.fFitupCompanyCases;
                // KindEditor.remove('#companyCasesContent');

                console.log(r.fFitupCompanyCases.content)

                editor.html(r.fFitupCompanyCases.content)


                /**
                 * 图片的回显
                 */

                var strm = r.fFitupCompanyCases.mainImg
                var split = strm.split("/");
                vm.fileList = [{
                    name: split[split.length - 1],
                    url: "http://localhost:81" + r.fFitupCompanyCases.mainImg
                }]


                /**
                 * 标签封装
                 */
                var str1 = r.fFitupCompanyCases.tags
                if (str1 == null) {
                    vm.dynamicTags = [];
                } else {
                    var split = str1.split(";");
                    var re = split.filter(function (s) {
                        return s && s.trim();
                    })
                    vm.dynamicTags = re;
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
$("#fitup-companyCases-form").submit(function (ev) {

    if ((!$('#fitup-companyCases-form').data('bootstrapValidator').isValid())) {
        return;
    }
    var fwb = $("#companyCasesContent").val();
    var fwb = editor.html();

    vm.fFitupCompanyCases.content = encodeURIComponent(fwb);
    // console.log(vm.fFitupCompanyCases.content)

    //封装标签
    if (vm.dynamicTags.length > 0) {
        var tag1s = '';
        vm.dynamicTags.forEach(function (value) {
            tag1s += (value + ';')

        })
        vm.fFitupCompanyCases.tags = tag1s;
    }


    var url = vm.fFitupCompanyCases.caseId == null ? "sys/ffitupcompanycases/save" : "sys/ffitupcompanycases/update";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.fFitupCompanyCases),
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
$("#fitup-companyCases-form").on('success.form.bv', function (e) {
    e.preventDefault();
    var form = $(e.target);

    // console.log("   bvbvbv")
    /**提交代码**/
});