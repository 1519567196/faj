$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fadplace/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-success">正常</span>' :
						'<span class="label label-danger">禁用</span>';
				}
				},
			{ label: '添加时时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			{ label: '操作人id', name: 'adduserid', index: 'adduserid', width: 80,hidden:true  },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
			{ label: '操作', name: 'status', index: 'status', width: 80 , formatter: function(value, options, rows) {
					var dd ="";
					//推荐
					if (value == 0) {
						dd = dd+'<input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.itemid + ')\' value="禁用"/>';
					}else{
						dd = dd + ' <input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.itemid + ')\' value="启用"/>';

					}

					return dd;
				}}
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
		fAdPlace: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fAdPlace = {};
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
			var url = vm.fAdPlace.itemid == null ? "sys/fadplace/save" : "sys/fadplace/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fAdPlace),
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
				    url: baseURL + "sys/fadplace/delete",
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
			$.get(baseURL + "sys/fadplace/info/"+itemid, function(r){
                vm.fAdPlace = r.fAdPlace;
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

//状态：启用或禁用
function upDown(itemid) {

	$.get(baseURL + "sys/fadplace/recommend/"+itemid, function(r){
		if (r.code == 0) {
			alert('操作成功', function (index) {
				//window.parent.frames[0].location = "/" + "modules/shenhua/tcase.html";
				vm.reload();

			});
		} else {
			alert(r.msg);
		}

	});
}