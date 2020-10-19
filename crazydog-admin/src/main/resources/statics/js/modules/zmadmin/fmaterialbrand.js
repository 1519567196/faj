$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fmaterialbrand/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true },
			{ label: 'logo', name: 'logo', index: 'logo',
				formatter: showPicture,
				edittype: 'file',
				editoptions: {enctype: "multipart/form-data"},width: 80 },
			{ label: '品牌名称', name: 'brandName', index: 'brand_name', width: 80 },
			{ label: '建材分类', name: 'materialTypeName', index: 'materialTypeName', width: 80 },
			{ label: '主图', name: 'image', index: 'image',
				formatter: showPicture,
				edittype: 'file',
				editoptions: {enctype: "multipart/form-data"},
				width: 80 },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
			{ label: '添加人', name: 'addUserName', index: 'addUserName', width: 80 },
			{ label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 }
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
function showPicture(cellvalue) {
	if (cellvalue==null){
		return "暂无数据"
	}
	return "<img src='" + cellvalue + "'  onclick=\'handlePictureBig(\"" + cellvalue + "\")\'  height='100' width='100'/>"
	// +
	// "<div id='Cover_Div' onClick=\'hideMax()\'></div>";
}
function handlePictureBig(fileurl) {
	// console.log(fileurl)
	vm.dialogImageUrl = fileurl;
	vm.dialogVisible = true;

}


function   bootstrapVal(){


	$("#form-materialbrand").bootstrapValidator({

		// live: 'submitted',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证

		// excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的

		// submitButtons: '.submitVR',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面

		message: '通用的验证失败消息',//好像从来没出现过

		feedbackIcons: {//根据验证结果显示的各种图标

			valid: 'glyphicon glyphicon-ok',

			invalid: 'glyphicon glyphicon-remove',

			validating: 'glyphicon glyphicon-refresh'

		},

		fields: {

			required: {
				message: '必填',
				validators: {
					notEmpty: {
						message: '必填'
					}
				}
			},
			number: {
				message: '数字',
				validators: {
					regexp: {//正则验证

						regexp: /^[1-9]\d{0,9}$/,


						message: '整数最多10位'

					},
					notEmpty: {
						message: '必填'
					}
				}
			},


		}

	});}
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		selMaterialTypeVal:[],
		selMaterialVal:[],
		title: null,
		fileList: [],
		fileListLogo: [],
		selOne:'0',
		dialogImageUrl: '',
		probablyDeleteImg:'',
		probablyDeleteImg1:'',
		materialTypeId:'0',
		dialogVisible: false,
		fMaterialBrand: {}
	},
	methods: {
		selectAttr1: function () {
			vm.selMaterialTypeVal = [];
			// console.log(vm.selOne)   //用selOne  而不是封装到对象里面 是因为 封装到对象里面获取不到
			$.ajax({
				type: "POST",
				url: baseURL + 'sys/fmaterialtype/onlyListByMainId',
				contentType: "application/json",
				data: JSON.stringify({mainId: vm.selOne}),
				success: function (r) {
					if (r.code === 0) {

						vm.selMaterialTypeVal = r.list;


					} else {
						alert(r.msg);
					}
				}
			});
		},
		getselMaterialVal(){
			$.ajax({
				type: "POST",
				url: baseURL + 'sys/fmaterial/onlyList',
				contentType: "application/json",
				success: function (r) {
					if (r.code === 0) {

						vm.selMaterialVal = r.list;


					} else {
						alert(r.msg);
					}
				}
			});
		},

		onSuccess(res) {

			if (res.code==0){
				vm.fMaterialBrand.image=res.urls[0].imgUrl;
				vm.probablyDeleteImg=res.urls[0].upload;
				this.$alert('上传成功！', '提示', {
					confirmButtonText: '确定',
					callback: action => {

					},
				})
			}

		},
		handleChange(file, fileList) {
			// console.log(fileList)
			if (fileList.length > 0) {
				vm.fileList = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件

			}

		},


		handlePictureCardPreview(file) {
			vm.dialogImageUrl = file.url;
			vm.dialogVisible = true;
		},

		handleRemove(file, fileList) {
			// console.log('    1213')
			vm.fMaterialBrand.image=null;
			// console.log(vm.probablyDeleteImg);
			var delUrl=vm.probablyDeleteImg;

			$.get(baseURL + "upload/deleteFile/?delUrl="+delUrl, function(r){
				if (r.code==0){
					alert("删除成功")
				}

			});

		},

		onSuccess1(res) {

			if (res.code==0){
				vm.fMaterialBrand.logo=res.urls[0].imgUrl;
				vm.probablyDeleteImg1=res.urls[0].upload;
				this.$alert('上传成功！', '提示', {
					confirmButtonText: '确定',
					callback: action => {

					},
				})
			}

		},
		handleChange1(file, fileList) {
			// console.log(fileList)
			if (fileList.length > 0) {
				vm.fileListLogo = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件

			}

		},


		// handlePictureCardPreview1(file) {
		// 	vm.dialogImageUrl = file.url;
		// 	vm.dialogVisible = true;
		// },

		handleRemove1(file, fileList) {
			// console.log('    1213')
			vm.fMaterialBrand.logo=null;
			// console.log(vm.probablyDeleteImg);
			var delUrl=vm.probablyDeleteImg1;

			$.get(baseURL + "upload/deleteFile/?delUrl="+delUrl, function(r){
				if (r.code==0){
					alert("删除成功")
				}

			});

		},
		query: function () {
			vm.reload();
		},
		add: function(){
			bootstrapVal();
			vm.getselMaterialVal();
			vm.showList = false;
			vm.title = "新增";
			vm.fMaterialBrand = {};
			vm.fileListLogo = [];
			vm.fileList = [];
			vm.selMaterialTypeVal=[];
		},
		update: function (event) {
			var itemid = getSelectedRow();
			if(itemid == null){
				return ;
			}
			bootstrapVal();
			vm.fileListLogo = [];
			vm.fileList = [];
			vm.getselMaterialVal();
			vm.showList = false;
            vm.title = "修改";
			vm.selMaterialTypeVal=[];

            vm.getInfo(itemid)
		},
		saveOrUpdate: function (event) {

		},
		del: function (event) {
			var itemids = getSelectedRows();
			if(itemids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fmaterialbrand/delete",
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
			$.get(baseURL + "sys/fmaterialbrand/info/"+itemid, function(r){
				vm.selMaterialTypeVal.push({itemid:r.fMaterialBrand.mainMateriaId,type:r.fMaterialBrand.materialTypeName})
				vm.materialTypeId=r.fMaterialBrand.mainMateriaId
				vm.fMaterialBrand = r.fMaterialBrand;
                vm.selOne=r.mainMateriaId;
				var  str=r.fMaterialBrand.image
				if(str==null){
					vm.fileList=[];
				}else {
				var split = str.split("/");
				vm.fileList=[{name : split[split.length-1],url: "http://localhost:81"+r.fMaterialBrand.image}]
				}
				var  str2=r.fMaterialBrand.logo
				if(str2==null){
					vm.fileListLogo=[];
				}else {
					var split2 = str2.split("/");
					vm.fileListLogo=[{name : split2[split2.length-1],url: "http://localhost:81"+r.fMaterialBrand.logo}]
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

$("#form-materialbrand").submit(function(ev){
	// console.log("   jjj")

	if(vm.fMaterialBrand.image==null||undefined ==vm.fMaterialBrand.image){
		alert("请上传图片");
		return ;
	}
	if((!$('#form-materialbrand').data('bootstrapValidator').isValid())){
		return ;
	}
vm.fMaterialBrand.materialTypeId=vm.materialTypeId;
	var url = vm.fMaterialBrand.itemid == null ? "sys/fmaterialbrand/save" : "sys/fmaterialbrand/update";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(vm.fMaterialBrand),
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


});

/**
 * 解决表单重复提交
 */
$("#form-materialbrand").on('success.form.bv', function(e) {
	e.preventDefault();
	var form = $(e.target);

	// console.log("   bvbvbv")
	/**提交代码**/
});