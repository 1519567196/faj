$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fbanners/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true ,hidden:true},
			{ label: '类别', name: 'type', index: 'type', width: 80,formatter:function(value, options, rows){
					return value == 1 ?
						'<span >banner图</span>' :
						'<span >城市全景图</span>';
				} },
			{ label: 'banner图链接', name: 'image', index: 'image', width: 80 }, 			
			{ label: 'vr 链接', name: 'vrUrl', index: 'vr_url', width: 80 }, 			
			{ label: '跳转链接', name: 'toUrl', index: 'to_url', width: 80 }, 			
			{ label: '排序', name: 'sort', index: 'sort', width: 80 },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '属所地区id', name: 'areaid', index: 'areaid', width: 80 },
			//{ label: '添加人员id', name: 'addUserid', index: 'add_userid', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 }
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
		fBanners: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fBanners = {};
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
			var url = vm.fBanners.itemid == null ? "sys/fbanners/save" : "sys/fbanners/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fBanners),
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
				    url: baseURL + "sys/fbanners/delete",
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
			$.get(baseURL + "sys/fbanners/info/"+itemid, function(r){
                vm.fBanners = r.fBanners;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		//是否隐藏VR链接输入框
		ishidden(){
			var val=$('input[name="type"]:checked').val();
			if(val==1){
				//document.getElementById(lianjie).style.visibility="hidden";
				$('#vr').hide();
				$('#banner').show();
			}else {
				$('#vr').show();
				$('#banner').hide();
			}
		}

	}
});