$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fnewstype/list',
        datatype: "json",
        colModel: [			
			{ label: 'typeid', name: 'typeid', index: 'typeid', width: 50, key: true,hidden:true },
			{ label: '分类名称', name: 'typeName', index: 'type_name', width: 80 }, 			
			{ label: '排序', name: 'sort', index: 'sort', width: 80 },
			//{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
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
		fNewsType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fNewsType = {};
		},
		update: function (event) {
			var typeid = getSelectedRow();
			if(typeid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(typeid)
		},
		saveOrUpdate: function (event) {
			var url = vm.fNewsType.typeid == null ? "sys/fnewstype/save" : "sys/fnewstype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fNewsType),
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
			var typeids = getSelectedRows();
			if(typeids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fnewstype/delete",
                    contentType: "application/json",
				    data: JSON.stringify(typeids),
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
		getInfo: function(typeid){
			$.get(baseURL + "sys/fnewstype/info/"+typeid, function(r){
                vm.fNewsType = r.fNewsType;
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