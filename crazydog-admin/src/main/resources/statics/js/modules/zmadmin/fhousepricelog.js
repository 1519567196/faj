$(function () {
    // $('#yearMouth').datetimepicker({
    // 	format: 'yyyy-mm-dd',
    // 	forceParse: 0,
    // 	initialDate: new Date(),
    // 	startView: 3,
    // 	minView: 3,
    // 	language: 'zh-CN',//显示中文
    // 	todayHighlight: true,
    // 	autoclose: true
    // });


    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fhousepricelog/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {label: '楼盘', name: 'houseName', index: 'houseName', width: 80},
            {label: '均价', name: 'averagePrice', index: 'average_price', width: 80},
            {label: '起价', name: 'startPrice', index: 'start_price', width: 80},
            {label: '年月份', name: 'mouth', index: 'mouth', width: 80},
            {label: '描述', name: 'remark', index: 'remark', width: 80},
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


    $("#form-priceLog").bootstrapValidator({

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

                        regexp: /^[1-9]\d{0,7}(\.\d{1,2})?$/,


                        message: '整数最多8位，小数最多两位'

                    },
                    notEmpty: {
                        message: '必填'
                    }
                }
            },


        }

    });


});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        selHouVal: [],
        value2: '',
        title: null,
        fHousePriceLog: {mouth: ''}
    },
    methods: {
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
            // $('#yearMouth').datetimepicker('remove');
            // $('#yearMouth').datetimepicker('show');
            vm.getOptions3();
            vm.showList = false;
            vm.title = "新增";
            vm.fHousePriceLog = {};
        },
        update: function (event) {
            // $('#yearMouth').datetimepicker('remove');
            $('#yearMouth').datetimepicker('update');
            vm.getOptions3();
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(itemid)
        },
        saveOrUpdate: function (event) {
            if(!$('#form-priceLog').data('bootstrapValidator').isValid()){
                return ;
            }
            vm.fHousePriceLog.mouth = vm.value2;
            // console.log(vm.fHousePriceLog.mouth)
            var url = vm.fHousePriceLog.itemid == null ? "sys/fhousepricelog/save" : "sys/fhousepricelog/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHousePriceLog),
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
                    url: baseURL + "sys/fhousepricelog/delete",
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
            $.get(baseURL + "sys/fhousepricelog/info/" + itemid, function (r) {
                vm.fHousePriceLog = r.fHousePriceLog;
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