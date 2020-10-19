$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/flinkus/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true ,hidden:true},
			{ label: '地区', name: 'city', index: 'city', width: 80 }, 			
			{ label: '手机号', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '地址', name: 'address', index: 'address', width: 80 }, 			
			{ label: '主图', name: 'image', index: 'image', width: 80 ,formatter: function(value, options, row){

					if(value!=null && value !="" && value.length>15){
						return "<img style='width:130px;height:60px; border:1px solid darkgrey' src='"+value+"'/>";
					}else{
						return "";
					}
				}},
			{ label: '企业名称', name: 'company', index: 'company', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '添加人id', name: 'addUserid', index: 'add_userid', width: 80 },
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
		fLinkUs: {},
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
			vm.fLinkUs = {};
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
			var url = vm.fLinkUs.itemid == null ? "sys/flinkus/save" : "sys/flinkus/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fLinkUs),
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
				    url: baseURL + "sys/flinkus/delete",
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
			$.get(baseURL + "sys/flinkus/info/"+itemid, function(r){
                vm.fLinkUs = r.fLinkUs;
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
				vm.fLinkUs.image=response.data.urls[0].url;
				console.log("qqqq==="+vm.fLinkUs.image);
			}).catch(error => {
				alert('上传图片出错！');
			})
		}
	}
});