$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fbbs/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '用户', name: 'memberName', index: 'memberName', width: 80 },
			{ label: '内容', name: 'content', index: 'content', width: 80 ,hidden: true},
			{ label: '精华帖', name: 'good', index: 'good', width: 80,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-danger">否</span>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'isGood(' + rows.itemid + ')\' value="设置为精华帖"/>' :
						'<span class="label label-success">是</span>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'isGood(' + rows.itemid + ')\' value="取消精华帖"/>';
				} },
			{ label: '置顶', name: 'top', index: 'top', width: 80 ,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-danger">否</span>' +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'upDown(' + rows.itemid + ')\' value="置顶"/>' :
						'<span class="label label-success">是</span>' +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'upDown(' + rows.itemid + ')\' value="取消置顶"/>';
				}},
			{ label: '浏览量', name: 'views', index: 'views', width: 80 }, 			
			{ label: 'IP地址', name: 'ip', index: 'ip', width: 80 }, 			
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function(value, options, rows){
					return value == 1 ?
						'<span class="label label-danger">屏蔽</span>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'isStatus(' + rows.itemid + ')\' value="恢复正常"/>' :
						'<span class="label label-success">正常</span>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						'<input type="button" class="btn btn-info btn-xs" onclick=\'isStatus(' + rows.itemid + ')\' value="屏蔽"/>';
				}},
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 }
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
		fBbs: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fBbs = {};

			var t;
			clearTimeout(t);
			KindEditor.remove('textarea[name="content"]');
			t = setTimeout(function (){
				kedit('textarea[name="content"]');
			}, 500);
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
			vm.fBbs.content = encodeURIComponent($("#activity_detail").val());
			var url = vm.fBbs.itemid == null ? "sys/fbbs/save" : "sys/fbbs/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fBbs),
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
				    url: baseURL + "sys/fbbs/delete",
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
			$.get(baseURL + "sys/fbbs/info/"+itemid, function(r){
                vm.fBbs = r.fBbs;

				var t;
				clearTimeout(t);
				KindEditor.remove('textarea[name="content"]');
				t = setTimeout(function (){
					kedit('textarea[name="content"]');
				}, 500);
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

var editor;
//文本编辑器
function kedit(kedit) {
	// var clearDefault=true;//是否有默认文字信息
	editor = KindEditor.create(kedit, {
		width: '680px',
		height: '400px',
		resizeMode: 0,
		allowPreviewEmoticons: false,
		allowImageUpload: true,//允许上传图片
		allowFileManager: true, //允许对上传图片进行管理
		uploadJson: baseURL+'/upload/imgUpload', //上传图片的java代码
		fileManagerJson: baseURL+'/upload/fileManager',
		afterUpload: function () {
			console.log("=======");
			this.sync();
		}, //图片上传后，将上传内容同步到textarea中
		afterBlur: function () {
			this.sync();
		}, //失去焦点时，将上传内容同步到textarea中

		items: [
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			'table', 'hr', 'emoticons', 'baidumap','media'
		]

	});
};

//查看详细
function clickth(id) {
	$.get(baseURL + "sys/fbbs/info/" + id, function (r) {

		if(r.code==0){
			var strbug = "";

			strbug+="<table>内容："+r.fBbs.content+"<br/></table>";

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

//是否置顶
function upDown(itemid) {

	$.get(baseURL + "sys/fbbs/recommend/"+itemid, function(r){
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

//是否为精华帖
function isGood(itemid) {

	$.get(baseURL + "sys/fbbs/good/"+itemid, function(r){
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

//是否屏蔽
function isStatus(itemid) {

	$.get(baseURL + "sys/fbbs/status/"+itemid, function(r){
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