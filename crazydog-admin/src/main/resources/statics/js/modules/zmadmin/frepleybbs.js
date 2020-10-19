$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/frepleybbs/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '主贴', name: 'title', index: 'title', width: 80 },
			{ label: '回复内容', name: 'repleyContent', index: 'repley_content', width: 80 ,hidden: true},
			{ label: '回复人', name: 'repleyname', index: 'repleyname', width: 80 },
			{ label: 'ip地址', name: 'ip', index: 'ip', width: 80 }, 			
			{ label: '被跟帖人', name: 'tomembername', index: 'tomembername', width: 80 },
			//{ label: '所属地区', name: 'areaid', index: 'areaid', width: 80 },
			{ label: '楼层', name: 'floor', index: 'floor', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			{ label: '操作', name: 'contentDetail', index: 'content_detail', width: 80,formatter: function (cellvalue, options, rowdata) {
					var str = '<a  href="javascript:void(0);" onclick="clickth('+ rowdata.itemid +')">查看</a>';
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
		fRepleyBbs: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fRepleyBbs = {};
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
			var url = vm.fRepleyBbs.itemid == null ? "sys/frepleybbs/save" : "sys/frepleybbs/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fRepleyBbs),
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
				    url: baseURL + "sys/frepleybbs/delete",
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
			$.get(baseURL + "sys/frepleybbs/info/"+itemid, function(r){
                vm.fRepleyBbs = r.fRepleyBbs;
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
	$.get(baseURL + "sys/frepleybbs/info/" + id, function (r) {

		if(r.code==0){
			var strbug = "";


			strbug+="<table>回复内容："+r.fRepleyBbs.repleyContent+"<br/></table>";



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