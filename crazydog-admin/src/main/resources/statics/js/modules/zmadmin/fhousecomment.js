$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fhousecomment/list',
        datatype: "json",
        colModel: [
            {label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true},
            {
                label: '综合评分', name: 'mainScore', index: 'main_score', width: 80,
                formatter: function (cellValue, grid, rows, state) {
                    return cellValue
                    // +'   <a  @click="visible = !visible">手动激活</a>' ;

                }
            },
            // { label: '价格评分', name: 'priceScore', index: 'price_score', width: 80 },
            // { label: '地段评分', name: 'placeScore', index: 'place_score', width: 80 },
            // { label: '交通评分', name: 'trafficScore', index: 'traffic_score', width: 80 },
            // { label: '配套评分', name: 'matchingScore', index: 'matching_score', width: 80 },
            // { label: '环境评分', name: 'environScore', index: 'environ_score', width: 80 },
            // { label: '点评内容', name: 'content', index: 'content', width: 80 },
            {
                label: '是否考虑购买', name: 'isThink',

                edittype: "text",
                formatter: function (cellValue, grid, rows, state) {
                    if (cellValue == 1) {
                        return '有兴趣'
                    } else if (cellValue == 2) {
                        return '再对比'
                    } else {
                        return '暂不考虑'
                    }

                },

                index: 'is_think', width: 80
            },
            {label: '楼盘', name: 'houseName', index: 'houseName', width: 80},
            {label: '评价人', name: 'memberName', index: 'memberName', width: 80},
            {label: '图片', name: 'image', index: 'image',
                formatter: showPicture,
                edittype: 'file',
                editoptions: {enctype: "multipart/form-data"},
                width: 80},
            {label: '添加时间', name: 'addtime', index: 'addtime', width: 80},
            {
                label: '是否热门', name: 'isHot',

                edittype: "button",
                formatter: function (cellValue, grid, rows, state) {
                    if (cellValue == 1) {
                        return "热门" +
                            "<button onclick=\'setNoHot(" + rows.itemid + ")\'>取消热门</button>"
                    } else if (cellValue == 2) {
                        return "不显示" +
                            "<button onclick=\'setVisible(" + rows.itemid + ")\'>恢复显示</button>"
                    } else {
                        return '无热门'
                    }

                },
                index: 'is_hot', width: 80
            }
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

function setVisible(itemId) {
    console.log(itemId + '   vvb')
	var url =  "sys/fhousecomment/setVisible";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(itemId),
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
function setNoHot(itemid) {
	var url =  "sys/fhousecomment/setVisible";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(itemid),
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

}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        fHouseComment: {priceScore: 0},
        visible: false
    },
    methods: {
        query: function () {
            vm.reload();
        },
        details: function () {
            // vm.showList = false;

            vm.title = "点评详情";
            var itemid = getSelectedRow();
            if (itemid == null) {
                return;
            }
            vm.visible = !vm.visible;
            if (vm.visible) {
                vm.getInfo(itemid)
            }

        },
        setHot: function (event) {
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }


            confirm('确定要将序号' + itemids + '的点评设置为热门？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fhousecomment/setHot",
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
        setInvisible: function (event) {
            var itemids = getSelectedRows();
            if (itemids == null) {
                return;
            }


            confirm('确定要将序号' + itemids + '的点评设置为不可见？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/fhousecomment/setInvisible",
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
        saveOrUpdate: function (event) {
            // if(!$('#form-commonattr').data('bootstrapValidator').isValid()){
            //     return ;
            // }
            var url = vm.fHouseComment.itemid == null ? "sys/fhousecomment/save" : "sys/fhousecomment/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.fHouseComment),
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
                    url: baseURL + "sys/fhousecomment/delete",
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
            $.get(baseURL + "sys/fhousecomment/info/" + itemid, function (r) {
                vm.fHouseComment = r.fHouseComment;
                // console.log(vm.fHouseComment)
                // console.log(r.fHouseComment)
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