$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fmember/list',
        datatype: "json",
        colModel: [			
			{ label: 'memberId', name: 'memberId', index: 'member_id', width: 50, key: true,hidden:true },
			{ label: '真实姓名', name: 'memberName', index: 'member_name', width: 80 },
			{ label: '手机号', name: 'mobile', index: 'mobile', width: 80 }, 			
			//{ label: '密码', name: 'password', index: 'password', width: 80 },
			{ label: '头像', name: 'headImg', index: 'head_img', width: 80,formatter: function(value, options, row){

					if(value!=null && value !="" && value.length>15){
						return "<img style='width:130px;height:60px; border:1px solid darkgrey' src='"+value+"'/>";
					}else{
						return "";
					}
				} },
			{ label: '性别', name: 'sex', index: 'sex', width: 80,formatter:function(value, options, rows){
					return value == 1 ?
						'<span >男</span>' :
						'<span >女</span>';
				} },
			{ label: '昵称', name: 'nick', index: 'nick', width: 80 }, 			
			{ label: '发帖数量', name: 'invitationCount', index: 'invitation_count', width: 80 }, 			
			{ label: '状态', name: 'status', index: 'status', width: 80,formatter:function(value, options, rows){
					return value == 0 ?
						'<span class="label label-success">启用</span>' :
						'<span class="label label-danger">禁用</span>';
				} },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			//{ label: '所在地区id', name: 'areaid', index: 'areaid', width: 80 }
			{ label: '操作', name: 'status', index: 'status', width: 80 , formatter: function(value, options, rows) {
					var dd ="";
					//推荐
					if (value == 0) {
						dd = dd+'<input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.memberId + ')\' value="禁用"/>';
					}else{
						dd = dd + ' <input type="button" class="btn btn-primary" onclick=\'upDown(' + rows.memberId + ')\' value="启用"/>';

					}

					return dd;
				}
			}

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
		fMember: {},
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
			vm.fMember = {};
		},
		update: function (event) {
			var memberId = getSelectedRow();
			if(memberId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(memberId)
		},
		saveOrUpdate: function (event) {
			var url = vm.fMember.memberId == null ? "sys/fmember/save" : "sys/fmember/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fMember),
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
			var memberIds = getSelectedRows();
			if(memberIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fmember/delete",
                    contentType: "application/json",
				    data: JSON.stringify(memberIds),
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
		getInfo: function(memberId){
			$.get(baseURL + "sys/fmember/info/"+memberId, function(r){
                vm.fMember = r.fMember;
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
				vm.fMember.headImg=response.data.urls[0].url;
				console.log("qqqq==="+vm.fMember.headImg);
			}).catch(error => {
				alert('上传图片出错！');
			})
		}
	}
});

//是否禁用
function upDown(memberId) {

	$.get(baseURL + "sys/fmember/status/"+memberId, function(r){
		if (r.code == 0) {
			alert('操作成功', function (index) {
				//window.parent.frames[0].location = "/"+"admin/modules/zmadmin/fmember.html";
				vm.reload();

			});
		} else {
			alert(r.msg);
		}

	});
}

