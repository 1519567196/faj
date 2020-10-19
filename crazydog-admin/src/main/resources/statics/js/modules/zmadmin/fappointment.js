$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fappointment/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'memberName', index: 'member_name', width: 80 }, 			
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '省', name: 'provinceid', index: 'provinceid', width: 80 },
			{ label: '市', name: 'cityid', index: 'cityid', width: 80 },
			{ label: '区', name: 'townid', index: 'townid', width: 80 },
			{ label: '小区、楼盘、工装类型', name: 'village', index: 'village', width: 80 }, 			
			{ label: '回复状态', name: 'isReply', index: 'is_reply', width: 80 ,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-danger">未回复</span>' :
						'<span class="label label-success">已回复</span>';
				}},
			{ label: '预约类型', name: 'type', index: 'type', width: 80,
				formatter:function(value, options, rows){
					switch (value) {
						case 1:
							return '<span >装修优惠</span>';
							break;
						case 2:
							return '<span >工装咨询</span>';
							break;
						case 3:
							return '<span >购房咨询</span>';
							break;
						case 4:
							return '<span >量房咨询</span>';
							break;
						case 5:
							return '<span >装修设计</span>';
							break;

					}
				} },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fAppointment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fAppointment = {};
		},
		update: function (event) {
			var itemid = getSelectedRow();
			if(itemid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(itemid)
		},
		saveOrUpdate: function (event) {
			var url = vm.fAppointment.itemid == null ? "sys/fappointment/save" : "sys/fappointment/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fAppointment),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var itemids = getSelectedRows();
			if(itemids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fappointment/delete",
                    contentType: "application/json",
				    data: JSON.stringify(itemids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(itemid){
			$.get(baseURL + "sys/fappointment/info/"+itemid, function(r){
                vm.fAppointment = r.fAppointment;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});