$(function () {
    vm.getOptions();
    vm.attrandattrValues();

    $("#form-test").bootstrapValidator({

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

            // text: {
            //
            // 	validators: {
            //
            // 		notEmpty: {//检测非空,radio也可用
            //
            // 			message: '文本框必须输入'
            //
            // 		},
            //
            // 		stringLength: {//检测长度
            //
            // 			min: 6,
            //
            // 			max: 30,
            //
            // 			message: '长度必须在6-30之间'
            //
            // 		},
            //
            // 		regexp: {//正则验证
            //
            // 			regexp: /^[a-zA-Z0-9_\.]+$/,
            //
            // 			message: '所输入的字符不符要求'
            //
            // 		},
            //
            // 		remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
            //
            // 			url: '指定页面',
            //
            // 			message: 'The username is not available'
            //
            // 		},
            //
            // 		different: {//与指定文本框比较内容相同
            //
            // 			field: '指定文本框name',
            //
            // 			message: '不能与指定文本框内容相同'
            //
            // 		},
            //
            // 		emailAddress: {//验证email地址
            //
            // 			message: '不是正确的email地址'
            //
            // 		},
            //
            // 		identical: {//与指定控件内容比较是否相同，比如两次密码不一致
            //
            // 			field: 'confirmPassword',//指定控件name
            //
            // 			message: '输入的内容不一致'
            //
            // 		},
            //
            // 		date: {//验证指定的日期格式
            //
            // 			format: 'YYYY/MM/DD',
            //
            // 			message: '日期格式不正确'
            //
            // 		},
            //
            // 		choice: {//check控件选择的数量
            //
            // 			min: 2,
            //
            // 			max: 4,
            //
            // 			message: '必须选择2-4个选项'
            //
            // 		}
            //
            // 	}
            //
            // }
            required: {
                message: '必填',
                validators: {
                    notEmpty: {
                        message: '必填'
                    }
                }
            },
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

            number: {
                message: '数字',
                validators: {
                    regexp: {//正则验证

                        regexp: /^[0-9]*$/,

                        message: '请输入数字'

                    },
                    // notEmpty: {
                    //     message: '必填'
                    // }
                }
            },


        }

    });

    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        startView: 2,
        minView: 0,
        todayHighlight: true,
        autoclose: true
    });

    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fhouses/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {label: '楼盘（房产）名称', name: 'title', index: 'title', width: 80},
            {label: '详细地址', name: 'address', index: 'address', width: 80},
            {label: '楼盘所在地区', name: 'townName', index: 'townName', width: 80},
            {label: ' 开盘时间', name: 'openTime', index: 'open_time', width: 80},
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {label: '装修情况', name: 'zhuangXiu', index: 'zhuang_xiu', width: 80},
            {
                label: '销售状态', name: 'saleStatus',

                edittype: "text",
                formatter: function (cellValue, grid, rows, state) {
                    if (cellValue == 1) {
                        return '在售'
                    } else if (cellValue == 2) {
                        return '售罄'
                    } else {
                        return '未知'
                    }

                },
                index: 'sale_status', width: 80
            },
            {label: '售楼地址', name: 'saleAddress', index: 'sale_address', width: 80}


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
        fileList: [],
        dialogImageUrl: '',
        probablyDeleteImg: '',
        dialogVisible: false,   //1~4是为了主图上传
        bj:true,
        showList: true,
        title: null,
        dynamicTags: [],
        inputVisible: false,
        inputValue: '',
        selOneVal: [{areaId: '0', areaName: '请选择省'}],
        selOneVal1: [{areaId: '0', areaName: '请选择省'}],
        attrs: [],
        attr: 0,
        attrValues: [],
        attrValue: [],
        selVal: [],
        selTwoVal: [{areaId: '0', areaName: '请选择市'}],
        selTwoVal1: [{areaId: '0', areaName: '请选择市'}],
        selThreeVal: [{areaId: '0', areaName: '请选择区'}],
        selThreeVal1: [{areaId: '0', areaName: '请选择区'}],
        selected1: 0,
        attrandattrValue: [],  //多个用分号分开   商圈：嘉祥；商圈：嘉祥
        selected2: 0,
        selected3: 0,
        selected21: 0,
        selected22: 0,
        selected23: 0,
        showImg: false,
        activeNames: ['1'],
        img: '',
        fHouses: {openTime: ''}
    },
    methods: {
        onSuccess(res) {

            if (res.code == 0) {
                vm.fHouses.image = res.urls[0].imgUrl;
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

        handleRemove(file, fileList) {
            // console.log('    1213')
            vm.fHouses.image= null;
            // console.log(vm.probablyDeleteImg);
            var delUrl = vm.probablyDeleteImg;

            $.get(baseURL + "upload/deleteFile/?delUrl=" + delUrl, function (r) {
                if (r.code == 0) {
                    alert("删除成功")
                }

            });

        },
        handlePictureCardPreview(file) {
            vm.dialogImageUrl = file.url;
            vm.dialogVisible = true;
        },
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
            if (this.dynamicTags.length<3){
                this.inputVisible = false;

            }else {
                this.inputVisible = true;
                this.bj = false;
            }
            this.inputValue = '';
        },
        selectAttr1: function () {
            vm.attrValue = [];
console.log(vm.attr)
            $.ajax({
                type: "POST",
                url: baseURL + 'sys/fcommonattrvalue/onlylist',
                contentType: "application/json",
                data: JSON.stringify({attrId: vm.attr}),
                success: function (r) {
                    if (r.code === 0) {

                        vm.attrValues = r.list;


                    } else {
                        alert(r.msg);
                    }
                }
            });


            for (var i = 0; i < vm.attrandattrValue.length; i++) {

                if (vm.attr == vm.attrandattrValue[i].attrId) {  //如果选择的属性已经有选择的属性值 则显示出来
                    vm.attrValue.push(vm.attrandattrValue[i].attrValueId)
                }

            }


        },
        selectAttr: function () {

            if (vm.attr == 0) {
                alert('请先选择属性再选择属性值')
                return;
            }
            // vm.attrandattrValue=[];
            console.log(vm.attrandattrValue.length + '    qian')


            for (var i = 0; i < vm.attrandattrValue.length; i++) {
                // console.log(vm.attrandattrValue[0])
                // console.log(vm.attr)
                if (vm.attrandattrValue[i].attrId == vm.attr) {
                    // console.log(1)
                    vm.attrandattrValue.splice(vm.attrandattrValue.indexOf(vm.attrandattrValue[i]), 1);
                    i = i - 1;
                    // console.log(vm.attrandattrValue.length+'     nei'+i)
                }


            }

            // console.log(vm.attrandattrValue.length+'    hou')

            var attrValue = vm.attrValue;
            for (var i = 0; i < attrValue.length; i++) {
                var attrValue1 = {attrId: 0, attrValueId: 0, attr: '', attrValue: '', houseId: 0};
                attrValue1.attrValueId = attrValue[i];
                attrValue1.attrId = vm.attr;
                attrValue1.houseId = vm.fHouses.itemid;

                for (var q = 0; q < vm.attrs.length; q++) {
                    if (vm.attrs[q].itemid == vm.attr) {
                        attrValue1.attr = vm.attrs[q].title;
                    }
                }
                for (var e = 0; e < vm.attrValues.length; e++) {
                    if (vm.attrValues[e].valueId == attrValue[i]) {
                        attrValue1.attrValue = vm.attrValues[e].title;
                    }
                }
                var bj = true;

                if (bj) {
                    vm.attrandattrValue.push(attrValue1)
                }


            }

        },
        handleClose1: function (tag) {

            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);

            if (this.dynamicTags.length<3){
                this.inputVisible = false;
                this.bj = true;
            }
        },
        handleClose: function (tag) {

            this.attrandattrValue.splice(this.attrandattrValue.indexOf(tag), 1);


            //还要进行对应attrValue[]的修改，或者重覆
        }
        ,

        attrandattrValues: function () {
            var url = "sys/fcommonattr/onlylist";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({type: 3}),
                success: function (r) {
                    if (r.code === 0) {

                        vm.attrs = r.list;
                        // console.log('     cccc    '+vm.selVal.length)


                    } else {
                        alert(r.msg);
                    }
                }
            });


        },


        handleChange: function (val) {
            // console.log(val);
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
                        vm.fHouses.xuKeZheng = imgUrl;
                        vm.showImg = true;


                    } else {
                        alert(r.msg);
                    }
                }

            })
        },


        getOptions1: function (bj) {
            // console.log(bj)

            var selec;
            if (bj == 1) {
                vm.selTwoVal.length = 1;
                vm.selThreeVal.length = 1;
                selec = vm.selected1;
            } else {
                vm.selTwoVal1.length = 1;
                vm.selThreeVal1.length = 1;
                selec = vm.selected21
            }
            // console.log(vm.selected21)
            for (var i = 0; i < vm.selVal.length; i++) {
                if (vm.selVal[i].areaDeep == 2 && vm.selVal[i].areaParentId == selec) {
                    if (bj == 1) {
                        vm.selTwoVal.push(vm.selVal[i])
                    } else {
                        vm.selTwoVal1.push(vm.selVal[i])
                    }

                }
            }
        },
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
                                vm.selOneVal1.push(r.list[i])
                            } else if (r.list[i].areaDeep == 2) {
                                vm.selTwoVal.push(r.list[i])
                                vm.selTwoVal1.push(r.list[i])
                            } else if (r.list[i].areaDeep == 3) {
                                vm.selThreeVal.push(r.list[i])
                                vm.selThreeVal1.push(r.list[i])
                            }
                        }
                        vm.selVal = r.list;
                        // console.log('     cccc    '+vm.selVal.length)


                    } else {
                        alert(r.msg);
                    }
                }
            });

        },
        getOptions2: function (bj) {


            // alert(vm.selected2);


            var selec1;
            var selec2;
            if (bj == 1) {
                vm.selThreeVal.length = 1;
                selec1 = vm.selected1;
                selec2 = vm.selected2;
            } else {
                vm.selThreeVal1.length = 1;
                selec1 = vm.selected21
                selec2 = vm.selected22
            }
            for (var i = 0; i < vm.selVal.length; i++) {

                if (vm.selVal[i].areaDeep == 3 && vm.selVal[i].areaParentId == selec2) {
                    if (bj == 1) {
                        vm.selThreeVal.push(vm.selVal[i]);
                    } else {
                        vm.selThreeVal1.push(vm.selVal[i]);
                    }


                } else if (vm.selVal[i].areaId == selec2) {
                    for (var o = 0; o < vm.selVal.length; o++) {
                        if (vm.selVal[o].areaDeep == 1 && vm.selVal[o].areaId == vm.selVal[i].areaParentId) {
                            if (bj == 2) {
                                // console.log(2)
                                vm.selected21 = vm.selVal[o].areaId;
                            } else {
                                // console.log(1)
                                vm.selected1 = vm.selVal[o].areaId;
                            }

                            break;
                        }

                    }


                }
            }

        },
        getOptions33: function (bj) {

            var selec1;
            var selec2;
            var selec3;
            if (bj == 1) {
                selec1 = vm.selected1;
                selec2 = vm.selected2;
                selec3 = vm.selected3;
            } else {
                selec1 = vm.selected21
                selec2 = vm.selected22
                selec3 = vm.selected23
            }
            for (var i = 0; i < vm.selVal.length; i++) {
                if (vm.selVal[i].areaId == selec3) {
                    for (var o = 0; o < vm.selVal.length; o++) {
                        if (vm.selVal[o].areaDeep == 2 && vm.selVal[o].areaId == vm.selVal[i].areaParentId) {

                            if (bj == 1) {
                                vm.selected2 = vm.selVal[o].areaId;
                                selec1 = vm.selected1;
                                selec2 = vm.selected2;
                                selec3 = vm.selected3;

                            } else {
                                vm.selected22 = vm.selVal[o].areaId;
                                selec1 = vm.selected21
                                selec2 = vm.selected22
                                selec3 = vm.selected23
                            }
                        }

                    }
                }
            }
                            for (var j = 0; j < vm.selVal.length; j++) {
                                // console.log('   jr')
                                // console.log(selec2)
                                if (vm.selVal[j].areaId == selec2) {
                                    for (var oo = 0; oo < vm.selVal.length; oo++) {
                                        if (vm.selVal[oo].areaDeep == 1 && vm.selVal[oo].areaId == vm.selVal[j].areaParentId) {
                                            if (bj == 2) {
                                                vm.selected21 = vm.selVal[oo].areaId;
                                            } else {
                                                vm.selected1 = vm.selVal[oo].areaId;
                                            }

                                            return;


                                        }
                                    }

                                }
                            }





        },

        query: function () {
            vm.reload();
        },
        add: function () {
            // vm.getOptions();

            vm.showList = false;
            vm.title = "新增";
            vm.fHouses = {};
            vm.dynamicTags = [];
            vm.selected1 = 0;
            vm.selected2 = 0;
            vm.selected3 = 0;
            vm.selected21 = 0;
            vm.selected22 = 0;
            vm.selected23 = 0;

        },
        update: function (event) {


            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            // console.log('qian '+ vm.selVal.length);
            vm.getInfo(itemid)
            // console.log('hou '+vm.selVal.length)
        },
        saveOrUpdate: function (event) {
            // console.log(vm.fHouses.openTime)
            // return;



        },
        del: function (event) {
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fhouses/delete",
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
            $.get(baseURL + "sys/fhouses/info/" + itemid, function (r) {
                vm.fHouses = r.fHouses;
                var str = r.fHouses.image;
                if (str!=null){
                    var split = str.split("/");
                    vm.fileList = [{name: split[split.length - 1], url: "http://localhost:81" + r.fHouses.image}]
                }




                vm.attrandattrValue = r.skus;
                console.log( r.skus);
                console.log( vm.attrandattrValue);
                vm.img = 'http://localhost:81' + r.fHouses.xuKeZheng;
                vm.showImg = true;
                var str = vm.fHouses.tags;
                if (str == null) {
                    vm.dynamicTags = [];
                } else {
                    var split = str.split(";");
                    var re = split.filter(function (s) {
                        return s && s.trim();
                    })
                    vm.dynamicTags = re;
                }

// console.log(vm.selVal)
                for (var i = 0; i < vm.selVal.length; i++) {
                    // console.log(vm.fHouses)
                    // console.log(r.fHouses)
                    if (vm.selVal[i].areaDeep == 3 && vm.selVal[i].areaId == r.fHouses.townid) {


                        vm.selected3 = r.fHouses.townid;
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
                        if (vm.selVal[i].areaDeep == 2 && vm.selVal[i].areaId == r.fHouses.townid) {
                            vm.selected2 = r.fHouses.townid;
                            for (var k = 0; k < vm.selVal.length; k++) {
                                if (vm.selVal[k].areaDeep == 1 && vm.selVal[k].areaId == vm.selVal[i].areaParentId) {
                                    vm.selected1 = vm.selVal[k].areaId;
                                    return;

                                }
                            }

                        } else if (vm.selVal[i].areaDeep == 1 && vm.selVal[i].areaId == r.fHouses.townid) {
                            vm.selected1 = vm.selVal[i].areaId;
                        }
                    }


                    if (vm.selVal[i].areaDeep == 3 && vm.selVal[i].areaId == r.fHouses.areaid) {


                        vm.selected23 = r.fHouses.areaid;
                        for (var j = 0; j < vm.selVal.length; j++) {
                            if (vm.selVal[j].areaDeep == 2 && vm.selVal[j].areaId == vm.selVal[i].areaParentId) {
                                vm.selected22 = vm.selVal[j].areaId;


                                for (var k = 0; k < vm.selVal.length; k++) {
                                    if (vm.selVal[k].areaDeep == 1 && vm.selVal[k].areaId == vm.selVal[j].areaParentId) {
                                        vm.selected21 = vm.selVal[k].areaId;
                                        break;

                                    }
                                }


                            }
                        }

                    } else {
                        if (vm.selVal[i].areaDeep == 2 && vm.selVal[i].areaId == r.fHouses.areaid) {
                            vm.selected22 = r.fHouses.areaid;
                            for (var k = 0; k < vm.selVal.length; k++) {
                                if (vm.selVal[k].areaDeep == 1 && vm.selVal[k].areaId == vm.selVal[i].areaParentId) {
                                    vm.selected21 = vm.selVal[k].areaId;
                                    return;

                                }
                            }

                        } else if (vm.selVal[i].areaDeep == 1 && vm.selVal[i].areaId == r.fHouses.areaid) {
                            vm.selected21 = vm.selVal[i].areaId;
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


$("#form-test").submit(function (ev) {
    if((!$('#form-test').data('bootstrapValidator').isValid())){
        return ;
    }
    if (vm.selected3 != 0) {
        vm.fHouses.townid = vm.selected3;
    } else {
        if (vm.selected2 != 0) {
            vm.fHouses.townid = vm.selected2;
        } else {
            vm.fHouses.townid = vm.selected1;
        }
    }
    if (vm.selected23 != 0) {
        vm.fHouses.areaid = vm.selected23;
    } else {
        if (vm.selected22 != 0) {
            vm.fHouses.areaid = vm.selected22;
        } else {
            vm.fHouses.areaid = vm.selected21;
        }
    }
    vm.fHouses.attrandattrValue = vm.attrandattrValue;
    var tags = '';
    vm.dynamicTags.forEach(function (value) {
        tags += (value + ';')

    })
    vm.fHouses.tags = tags
    var url = vm.fHouses.itemid == null ? "sys/fhouses/save" : "sys/fhouses/update";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        timeout: 5000000,
        contentType: "application/json",
        // dataType:'json',
        data: JSON.stringify(vm.fHouses),
        success: function (r) {
            console.log(r + '   kk')
            if (r.code === 0) {
                console.log('  up')
                alert('操作成功', function (index) {
                    vm.reload();
                });
            } else {
                alert(r.msg);
            }
        },
        error: function (r) {
            console.log(r.code + '    error');
        }
    });

});

/**
 * 解决表单重复提交
 */
$("#form-test").on('success.form.bv', function (e) {
    e.preventDefault();
    var form = $(e.target);

    // console.log("   bvbvbv")
    /**提交代码**/
});



