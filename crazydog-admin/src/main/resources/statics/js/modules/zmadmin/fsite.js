$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fsite/list',
        datatype: "json",
        colModel: [			
			{ label: 'itemid', name: 'itemid', index: 'itemid', width: 50, key: true,hidden:true },
			{ label: '企业名称', name: 'company', index: 'company', width: 80 }, 			
			{ label: 'ICP', name: 'icp', index: 'ICP', width: 80 }, 			
			{ label: '联系电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '公司地址', name: 'address', index: 'address', width: 80 }, 			
			{ label: '工作时间', name: 'workTime', index: 'work_time', width: 80 }, 			
			{ label: '企业简介', name: 'briefIntroduction', index: 'brief_introduction', width: 80 }, 			
			{ label: '企业文化', name: 'culture', index: 'culture', width: 80 }, 			
			{ label: '公众号二维码', name: 'erCode', index: 'er_code', width: 80,formatter: function(value, options, row){

					if(value!=null && value !="" && value.length>15){
						return "<img style='width:130px;height:60px; border:1px solid darkgrey' src='"+value+"'/>";

					}else{
						return "";
					}
				} },
			//{ label: '备注1', name: 'remark1', index: 'remark1', width: 80 },
			//{ label: '备注2', name: 'remark2', index: 'remark2', width: 80 },
			//{ label: '图片1', name: 'image1', index: 'image1', width: 80 },
			//{ label: '图片2', name: 'image2', index: 'image2', width: 80 },
			//{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 },
			//{ label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 }
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
		fSite: {},
		img:[],
		mgs: [],


		imgData: {
			accept: 'image/gif, image/jpeg, image/png, image/jpg',
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fSite = {};
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
			var url = vm.fSite.itemid == null ? "sys/fsite/save" : "sys/fsite/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fSite),
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
				    url: baseURL + "sys/fsite/delete",
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
			$.get(baseURL + "sys/fsite/info/"+itemid, function(r){
                vm.fSite = r.fSite;
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
				vm.fSite.erCode=response.data.urls[0].url;
				console.log("qqqq==="+vm.fSite.erCode);
			}).catch(error => {
				alert('上传图片出错！');
			})


		}
	}
});

//查看详细
function clickth(id) {
	$.get(baseURL + "sys/fsite/info/" + id, function (r) {

		if(r.code==0){
			var strbug = "";

			strbug+="<table><tr><td>企业名称：</td><td>"+r.fSite.company+"</td></tr>";
			strbug+="<tr><td>ICP：</td><td>"+r.fSite.icp+"</td></tr>";
			strbug+="<tr><td>联系电话：</td><td>"+r.fSite.mobile+"</td></tr>";
			strbug+="<tr><td>工作时间：</td><td>"+r.fSite.workTime+"</td></tr></table>";
			strbug+="<table>公司地址："+r.fSite.address+"<br/></table>";
			strbug+="<table>企业简介："+r.fSite.briefIntroduction+"<br/></table>";
			strbug+="<table>企业文化："+r.fSite.culture+"<br/></table>";

			strbug+="<img src='http://192.168.6.232:81"+r.fSite.erCode+"'>";
			console.log("====="+r.fSite.erCode);



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