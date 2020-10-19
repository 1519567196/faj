$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/farticletype/list',
        datatype: "json",
        colModel: [			
			{ label: 'typeid', name: 'typeid', index: 'typeid', width: 50, key: true,hidden:true },
			{ label: '分类名称', name: 'typeName', index: 'type_name', width: 80 }, 			
			{ label: '排序', name: 'sort', index: 'sort', width: 80 },
			{ label: '类型', name: 'atType', index: 'atType',

				formatter:function(cel){
				if (cel==1){
					return  '建材分类'
				}else if(cel==2){
					return '楼盘动态'
				}else {
					return '未知'
				}

				},
				width: 80 },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80,hidden: true }
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
		fArticleType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fArticleType = {};
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
			var url = vm.fArticleType.typeid == null ? "sys/farticletype/save" : "sys/farticletype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fArticleType),
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
				    url: baseURL + "sys/farticletype/delete",
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
			$.get(baseURL + "sys/farticletype/info/"+typeid, function(r){
                vm.fArticleType = r.fArticleType;
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