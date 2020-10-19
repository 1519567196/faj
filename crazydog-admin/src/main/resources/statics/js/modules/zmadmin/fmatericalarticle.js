$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fmatericalarticle/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', index: 'title', width: 80 },
			{ label: '摘要', name: 'subTitle', index: 'sub_title', width: 80 },
			{ label: '内容', name: 'content', index: 'content', width: 80 ,hidden: true},
			{ label: '浏览量', name: 'views', index: 'views', width: 80 }, 			
			{ label: '所属材料品牌', name: 'materialTypeName', index: 'materialTypeName', width: 80 },
			{ label: '所属材料分类', name: 'materialTypeName2', index: 'materialTypeName2', width: 80 },
			//{ label: '标签，多个用;分开', name: 'tags', index: 'tags', width: 80 },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '添加人id', name: 'addUserid', index: 'add_userid', width: 80 },
			{ label: '材料文章分类', name: 'typeName', index: 'typeName', width: 80 },
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
	//获取分类
	vm.getTypeList();
	vm.getTypeList2();
	vm.getTypeList3();



});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fMatericalArticle: {content:''},
		typeList:{},
		typeList2:{},
		typeList3:{},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fMatericalArticle = {};
			//获取分类
			vm.getTypeList();
			vm.getTypeList2();
			vm.getTypeList3();



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
            
            vm.getInfo(itemid);
			vm.getTypeList();
			vm.getTypeList2();
			vm.getTypeList3();

		},
		saveOrUpdate: function (event) {
			//vm.fMatericalArticle.content = encodeURIComponent($("#activity_detail").val());
			vm.fMatericalArticle.content = encodeURIComponent(editor.html());
			var url = vm.fMatericalArticle.itemid == null ? "sys/fmatericalarticle/save" : "sys/fmatericalarticle/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fMatericalArticle),
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
				    url: baseURL + "sys/fmatericalarticle/delete",
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
			$.get(baseURL + "sys/fmatericalarticle/info/"+itemid, function(r){
                vm.fMatericalArticle = r.fMatericalArticle;

				var t;
				clearTimeout(t);
				KindEditor.remove('textarea[name="content"]');
				t = setTimeout(function (){
					kedit('textarea[name="content"]');
				}, 500);

            });
		},

		getTypeList: function(){
			$.ajax({
				type: "POST",
				url: baseURL + "sys/fmatericalarticle/typeList",
				contentType: "application/json",
				data: JSON.stringify(vm.fMatericalArticle),
				success: function (r) {
					console.log("ddddaa======"+JSON.stringify(r));
					if (r.code === 0) {

						vm.typeList = r.list;

					} else {
						alert(r.msg);
					}
				}
			});
		},

		getTypeList2: function(){
			$.ajax({
				type: "POST",
				url: baseURL + "sys/fmatericalarticle/typeList2",
				contentType: "application/json",
				data: JSON.stringify(vm.fMatericalArticle),
				success: function (r) {
					console.log("bbbbbb======"+JSON.stringify(r));
					if (r.code === 0) {

						vm.typeList2 = r.list;

					} else {
						alert(r.msg);
					}
				}
			});
		},

		getTypeList3: function(){
			$.ajax({
				type: "POST",
				url: baseURL + "sys/fmatericalarticle/typeList3",
				contentType: "application/json",
				data: JSON.stringify(vm.fMatericalArticle),
				success: function (r) {
					console.log("cccc======"+JSON.stringify(r));
					if (r.code === 0) {

						vm.typeList3 = r.list;

					} else {
						alert(r.msg);
					}
				}
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
		resizeMode: 0,//2可以拖动改变高度和宽度，1只能改变高度，0不能拖动
		allowPreviewEmoticons: false,//true时鼠标放在表情上可以预览表情
		allowImageUpload: true,//允许上传图片
		//allowFileManager: true, //允许对上传图片进行管理，true时显示浏览远程服务器按钮
		uploadJson: baseURL+'/upload/fwbUpload', //上传图片的java代码
		//fileManagerJson: baseURL+'/upload/fileManager',//指定浏览远程图片的服务器端程序
			afterUpload: function (url, data, name) {
				this.sync();
				console.log("dsdsds=="+url);
				if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
					var img = new Image(); img.src = url;
					img.onload = function(){ //图片必须加载完成才能获取尺寸
						editor.html(editor.html().replace('<img src="' + url + '"','<img src="' + url + '" width="300"'))
					}
				}

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