$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fadvert/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '广告位位置', name: 'adPlaceId', index: 'ad_place_id', width: 80,formatter:function(value, options, rows){
					switch (value) {
						case 1:
							return '<span >首页楼层1</span>'
							break;
						case 2:
							return '<span >首页楼层2</span>'
							break;
						case 3:
							return '<span >首页楼层3</span>'
							break;
						case 4:
							return '<span >首页右侧1</span>'
							break;
						case 5:
							return '<span >首页右侧2</span>'
							break;
						case 6:
							return '<span >首页右侧3</span>'
							break;
						case 7:
							return '<span >我家方案顶部</span>'
							break;
						case 8:
							return '<span >装修百科顶部（列表+详情）</span>'
							break;
						case 9:
							return '<span >房产资讯顶部</span>'
							break;
						case 10:
							return '<span >新房顶部</span>'
							break;
						case 11:
							return '<span >楼盘首页楼层1</span>'
							break;
						case 12:
							return '<span >楼盘首页楼层2</span>'
							break;
						case 13:
							return '<span >楼盘首页楼层3</span>'
							break;
						case 14:
							return '<span >楼盘首页楼层4</span>'
							break;
						case 15:
							return '<span >主材顶部</span>'
							break;
						case 16:
							return '<span > 案例顶部（家装+工装）</span>'
							break;
						case 17:
							return '<span >楼盘销售排行顶部</span>'
							break;
						case 18:
							return '<span >装修公司顶部</span>'
							break;
						case 19:
							return '<span >论坛首页顶部</span>'
							break;
						case 20:
							return '<span > 房产买卖流程百科列表</span>'
							break;


					}
				} },
			{ label: '图片', name: 'imgurl', index: 'imgurl', width: 80,formatter: function(value, options, row){

					if(value!=null && value !="" && value.length>15){
						return "<img style='width:130px;height:60px; border:1px solid darkgrey' src='"+value+"'/>";
					}else{
						return "";
					}
				} },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '跳转链接', name: 'tourl', index: 'tourl', width: 80 }, 			
			{ label: '排序', name: 'sort', index: 'sort', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-success">启用</span>' :
						'<span class="label label-danger">禁用</span>';
				}
			},
			{ label: '添加时时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			{ label: '操作人id', name: 'adduserid', index: 'adduserid', width: 80,hidden: true },
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
		beforeSelectRow: beforeSelectRow,
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

function beforeSelectRow()
{
	$("#jqGrid").jqGrid('resetSelection');
	return(true);
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fAdvert: {},
		typeList:{},
		typeList:[],
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
			vm.fAdvert = {};

			//获取分类
			vm.getTypeList();
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
			var url = vm.fAdvert.itemid == null ? "sys/fadvert/save" : "sys/fadvert/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fAdvert),
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
				    url: baseURL + "sys/fadvert/delete",
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
			$.get(baseURL + "sys/fadvert/info/"+itemid, function(r){
                vm.fAdvert = r.fAdvert;
            });
		},

		getTypeList: function(){
			$.ajax({
				type: "POST",
				url: baseURL + "sys/fadvert/typeList",
				contentType: "application/json",
				data: JSON.stringify(vm.fAdvert),
				success: function (r) {
					console.log("dddd=="+JSON.stringify(r));
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
			var typeId=$("#typeid").val();
			console.log("dddd=="+typeId);
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData: {

					'fAdvert.adPlaceId': typeId
				},
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
			vm.fAdvert.imgurl=response.data.urls[0].url;
			console.log("qqqq==="+vm.fAdvert.imgurl);
		}).catch(error => {
				alert('上传图片出错！');
		})


		}

	}
});

//状态：启用或禁用
function upDown(itemid) {

	$.get(baseURL + "sys/fadvert/recommend/"+itemid, function(r){
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
