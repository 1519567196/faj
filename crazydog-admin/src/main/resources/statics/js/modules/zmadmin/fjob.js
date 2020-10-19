$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fjob/list',
        datatype: "json",
        colModel: [			
			{ label: 'iobId', name: 'iobId', index: 'iob_id', width: 50, key: true,hidden:true },
			{ label: '职位名称', name: 'job', index: 'job', width: 80 }, 			
			{ label: '招聘人数', name: 'count', index: 'count', width: 80 }, 			
			{ label: '工作地点', name: 'workplace', index: 'workplace', width: 80 }, 			
			{ label: '内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '添加人id', name: 'addUserid', index: 'add_userid', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
			{ label: '操作', name: 'contentDetail', index: 'content_detail', width: 80,formatter: function (cellvalue, options, rowdata) {
					var str = '<a  href="javascript:void(0);" onclick="clickth('+ rowdata.iobId +')">查看</a>';
					return str;
				} }
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
		fJob: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fJob = {};
		},
		update: function (event) {
			var iobId = getSelectedRow();
			if(iobId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(iobId)
		},
		saveOrUpdate: function (event) {
			var url = vm.fJob.iobId == null ? "sys/fjob/save" : "sys/fjob/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fJob),
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
			var iobIds = getSelectedRows();
			if(iobIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fjob/delete",
                    contentType: "application/json",
				    data: JSON.stringify(iobIds),
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
		getInfo: function(iobId){
			$.get(baseURL + "sys/fjob/info/"+iobId, function(r){
                vm.fJob = r.fJob;
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

//查看详细
function clickth(id) {
	$.get(baseURL + "sys/fjob/info/" + id, function (r) {

		if(r.code==0){
			var strbug = "";


			// strbug=strbug+"<input type='hidden' name='bugDeid' id='bugDeid' value='" + bugid + "'  />";

			strbug+="<table><tr><td>职位名称：</td><td>"+r.fJob.job+"</td></tr>";
			strbug+="<tr><td>招聘人数：</td><td>"+r.fJob.count+"</td></tr>";
			strbug+="<tr><td>添加时间：</td><td>"+r.fJob.addtime+"</td></tr>";
			strbug+="<tr><td>修改时间：</td><td>"+r.fJob.updatetime+"</td></tr></table>";
			strbug+="<table>工作地点："+r.fJob.workplace+"<br/></table>";
			strbug+="<table>内容："+r.fJob.content+"<br/></table>";



			document.getElementById("showDetailDIV").innerHTML=strbug;
			layer.open({
				type: 1,
				// btn: savebug,
				skin: 'layui-layer-molv',
				title: "详情",
				area: ['600px', '500px'],
				shadeClose: false,
				content: jQuery("#showDetail")


			});

		}
	});

}