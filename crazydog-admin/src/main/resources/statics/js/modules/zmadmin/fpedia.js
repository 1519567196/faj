var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "itemid",
			pIdKey: "parentid",
			rootPId: 0
		},
		key: {
			//url:"nourl",
			name:"title"
		}
	}
};
var ztree;


$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fpedia/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true, hidden:true },
			{ label: '标题名称', name: 'title', index: 'title', width: 80 },
			{ label: ' 点击量', name: 'views', index: 'views', width: 80 },
			{ label: '来源', name: 'from', index: 'from', width: 80 }, 			
			{ label: '百科分类', name: 'typeName', index: 'typeName', width: 80 },
			{ label: '标签名称，多个用;隔开', name: 'tags', index: 'tags', width: 80 },
			// { label: '点赞数', name: 'goods', index: 'goods', width: 80 },
			// { label: '摘要', name: 'subTitle', index: 'sub_title', width: 80 },
			{ label: '类别', name: 'subject', index: 'subject', width: 80,
				formatter: function (value, options, rows) {
					return value == 1 ?'房产百科' :'装修百科';
				} },
			{ label: '是否推荐', name: 'recommend', index: 'recommend', width: 80,
				formatter: function (value, options, rows) {
					return value == 0 ?
						'<span class="label label-success">否</span>' :
						'<span class="label label-danger">已推荐</span>';
				} },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 }
			// { label: '所属地区', name: 'areaid', index: 'areaid', width: 80 }
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
		fPedia: {
			typeid:""
		},
		img:[],
		fPediaType: {
			itemid:"",
			title:"",
			parentid:"",
			sort:"",
			subject:"",
			updatetime:"",
			areaid:""
		},
		mgs: [],
		imgData: {
			accept: 'image/gif, image/jpeg, image/png, image/jpg',
		},
	},
	methods: {

		// getCat: function(){
		// 	//加载部门树
		// 	$.get(baseURL + "sys/fpediatype/list", function(r){
		//
		// 		ztree = $.fn.zTree.init($("#fpediatype"), setting, r);
		// 		var node = ztree.getNodeByParam("parentid", vm.fPediaType.parentid);
		// 		ztree.selectNode(node);
		// 		if(vm.fPediaType.parentid != 0){
		// 			$("#parentName").val(node.title);
		// 		}
		//
		// 	})
		// },

		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fPedia = {};
			$.get(baseURL + "sys/fpediatype/list", function(r){
				console.log("ddddd="+JSON.stringify(r));
				ztree = $.fn.zTree.init($("#fPediaType"), setting, r);
				// var node = ztree.getNodeByParam("id", vm.zmNews.cid);
				// ztree.selectNode(node);
				// console.log(node);
				// $("#catName").val(node.catname);

			})
			$("#activity_detail").val("");
			vm.reflusheidt();
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
			var url = vm.fPedia.itemid == null ? "sys/fpedia/save" : "sys/fpedia/update";
			vm.fPedia.content = encodeURIComponent($("#activity_detail").val());;//editor.html();
			console.log("1111111111111="+vm.fPedia.content);
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fPedia),
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
				    url: baseURL + "sys/fpedia/delete",
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
			$.get(baseURL + "sys/fpedia/info/"+itemid, function(r){
                vm.fPedia = r.fPedia;

				$("#activity_detail").val(vm.fPedia.contents);
				$.get(baseURL + "sys/fpediatype/list", function(r){
					ztree = $.fn.zTree.init($("#fPediaType"), setting, r);
					var node = ztree.getNodeByParam("id", vm.fPedia.typeid);
					ztree.selectNode(node);
					console.log(node);
					$("#parentName").val(node.title);

				})
				vm.reflusheidt();
            });
		},

		catPage: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择分类",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#catLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					console.log("3333333333==="+JSON.stringify(node));
					vm.fPedia.typeid = node[0].itemid;
					$("#parentName").val(node[0].title);

					layer.close(index);
				}
			});
		},

		reflusheidt:function () {
			var t;
			clearTimeout(t);
			//if(vm.ktChapter.hasOwnProperty("content"))
			KindEditor.remove('textarea[name="fPedia.content"]');
			t = setTimeout(function (){
				kedit('textarea[name="fPedia.content"]');
			}, 1000);
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
				vm.fPedia.imgUrl=response.data.urls[0].url;
				console.log("qqqq==="+vm.fPedia.imgUrl);
		}).catch(error => {
				alert('上传图片出错！');
		})


		}

	}
});



var editor;
//文本编辑器
function kedit(kedit) {
	editor = KindEditor.create(kedit, {
		width: '680px',
		height: '400px',
		resizeMode: 0,
		filePostName:"file",
		allowPreviewEmoticons: false,
		allowImageUpload: true,//允许上传图片
		allowFileManager: false, //允许对上传图片进行管理
		uploadJson: baseURL+'/upload/fwbUpload', //上传图片的java代码
		fileManagerJson: baseURL+'/proUpload/',
		formatUploadUrl:false,
		afterUpload: function (url, data, name) {
			this.sync();
			console.log("dsdsds=="+url);
			if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
				var img = new Image(); img.src = url;
				img.onload = function(){ //图片必须加载完成才能获取尺寸
					editor.html(editor.html().replace('<img src="' + url + '"','<img src="' + url + '" width="300"'))
				}
			}

		},
		afterCreate: function () {  //要取值设置这里 这个函数就是同步KindEditor的值到textarea文本框
			this.sync();
		},
		afterBlur: function () {
			this.sync();
		}, //失去焦点时，将上传内容同步到textarea中

		items: [
			'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'media','underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			'table', 'hr', 'emoticons', 'baidumap'
		]
	});
}

