$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fnews/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '分类', name: 'typeName', index: 'typeName', width: 80 },
			{ label: '主图', name: 'image', index: 'image', width: 80,formatter: function(value, options, row){

					if(value!=null && value !="" && value.length>15){
						return "<img style='width:130px;height:60px; border:1px solid darkgrey' src='"+value+"'/>";
					}else{
						return "";
					}
				} },
			{ label: '内容', name: 'content', index: 'content', width: 80,hidden: true },
			{ label: '来源', name: 'from', index: 'from', width: 80 }, 			
			{ label: '浏览量', name: 'views', index: 'views', width: 80 }, 			
			{ label: '推荐', name: 'isRecommend', index: 'is_recommend', width: 80,
				formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-danger">否</span>' :
						'<span class="label label-success">是</span>';
				}
			},
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '添加人id', name: 'addUserid', index: 'add_userid', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
			{ label: '操作', name: 'isRecommend', index: 'isRecommend', width: 80 , formatter: function(value, options, rows) {
					var dd ="";
					//推荐
					if (value == 0) {
						dd = dd+'<input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.itemid + ')\' value="推荐"/>';
					}else{
						dd = dd + ' <input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.itemid + ')\' value="下架"/>';

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
	//获取分类
	vm.getTypeList();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fNews: {content:''},
		typeList:{},
		img:[],
		mgs: [],


		imgData: {
			accept: 'image/gif, image/jpeg, image/png, image/jpg',
		},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fNews = {};
			//获取分类
			vm.getTypeList();

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
		},
		saveOrUpdate: function (event) {
			vm.fNews.content = encodeURIComponent(editor.html());
console.log(editor.html())
			// return;
			var url = vm.fNews.itemid == null ? "sys/fnews/save" : "sys/fnews/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fNews),
			    success: function(r){
			    	if(r.code === 0){
						console.log("a========="+vm.fNews.content);
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
				    url: baseURL + "sys/fnews/delete",
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

			$.get(baseURL + "sys/fnews/info/"+itemid, function(r){
                vm.fNews = r.fNews;



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
				url: baseURL + "sys/fnews/typeList",
				contentType: "application/json",
				data: JSON.stringify(vm.fNews),
				success: function (r) {
					console.log("dddd======"+JSON.stringify(r));
					if (r.code === 0) {

						vm.typeList = r.list;

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
		},
		//图片上传
		add_img(event){
			var reader =new FileReader();
			let img1=event.target.files[0];
			let type=img1.type;//文件的类型，判断是否是图片
			let size=img1.size;//文件的大小，判断图片的大小
			if(this.imgData.accept.indexOf(type) == -1){
				alert('请选择我们支持的图片格式！');
				return false;
			}
			if(size>3145728){
				alert('请选择3M以内的图片！');
				return false;
			}
			var uri = ''
			let form = new FormData();
			form.append('file',img1,img1.name);
			form.append('filePage',"/sqEditor/images");
			this.$http.post(baseURL +'/upload/imgUpload',form,{
				headers:{'Content-Type':'multipart/form-data'}
			}).then(response => {
				vm.img = response.data.urls[0].url;
				vm.fNews.image=response.data.urls[0].url;
				console.log("qqqq==="+vm.fNews.image);
			}).catch(error => {
				alert('上传图片出错！');
			})


		}
	}
});

//推荐
function upDown(itemid) {

	$.get(baseURL + "sys/fnews/recommend/"+itemid, function(r){
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
		//allowFileManager: true, //允许对上传图片进行管理
		uploadJson: baseURL+'/upload/fwbUpload', //上传图片的java代码
		//fileManagerJson: baseURL+'/upload/fileManager',
		afterUpload: function (url, data, name) {
			this.sync();
			console.log("dsdsds=="+url);
			if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
				var img = new Image(); img.src = url;
				img.onload = function(){ //图片必须加载完成才能获取尺寸
					editor.html(editor.html().replace('<img src="' + url + '"','<img src="' + url + '" width="300"'))
				}
			}

		},//图片上传后，将上传内容同步到textarea中
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