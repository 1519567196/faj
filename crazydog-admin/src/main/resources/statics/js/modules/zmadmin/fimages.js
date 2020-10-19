$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fimages/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true },
			{ label: '图片名称', name: 'title', index: 'title', width: 80 }, 			
			// { label: '相册分类 1装修公司相册，2.楼盘相册', name: 'type', index: 'type', width: 80 },
			{ label: '所属企业', name: 'companyName', index: 'companyName', width: 80 },
			{ label: '图片', name: 'image', index: 'image',
				formatter: showPicture,
				edittype: 'file',
				editoptions: {enctype: "multipart/form-data"},
				width: 80 },
			{ label: '排序，越大越靠前', name: 'sort', index: 'sort', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 },
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
	return "<img src='" + cellvalue + "'  onclick=\'handlePictureBig(\"" + cellvalue + "\")\'  height='100' width='100'/>"
	// +
	// "<div id='Cover_Div' onClick=\'hideMax()\'></div>";
}
function handlePictureBig(fileurl) {
	console.log(fileurl)
	vm.dialogImageUrl = fileurl;
	vm.dialogVisible = true;

}
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fileList: [],
		selCompanyVal:[],
		dialogImageUrl: '',
		probablyDeleteImg:'',
		dialogVisible: false,
		fImages: {}
	},
	methods: {
		//图片上传
		onSuccess(res) {

			if (res.code == 0) {
				vm.fImages.image = res.urls[0].imgUrl;
				vm.probablyDeleteImg = res.urls[0].upload;
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
			vm.fImages.image = null;
			// console.log(vm.probablyDeleteImg);
			var delUrl = vm.probablyDeleteImg;

			$.get(baseURL + "upload/deleteFile/?delUrl=" + delUrl, function (r) {
				if (r.code == 0) {
					alert("删除成功")
				}

			});

		},
		//获取企业下拉数据
		getOptions3: function () {
			var url = "sys/ffitupcompany/onlyList";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify({}),
				success: function (r) {
					if (r.code === 0) {


						vm.selCompanyVal = r.list;


					} else {
						alert(r.msg);
					}
				}
			});
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.getOptions3()
			$('#form-fimage').data('bootstrapValidator',null);
			bootstrapVal()
			vm.fileList=[];
			vm.fImages = {};
		},
		update: function (event) {
			var itemid = getSelectedRow();
			if(itemid == null){
				return ;
			}
			$('#form-fimage').data('bootstrapValidator',null);
			bootstrapVal()

			vm.fileList=[];
			vm.getOptions3()
			vm.showList = false;
            vm.title = "修改";

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
				    url: baseURL + "sys/fimages/delete",
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
			$.get(baseURL + "sys/fimages/info/"+itemid, function(r){
                vm.fImages = r.fImages;
				/**
				 * 图片的回显
				 */

				var strm = r.fImages.image
				var split = strm.split("/");
				vm.fileList = [{
					name: split[split.length - 1],
					url: "http://localhost:81" + r.fImages.image
				}]

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
function bootstrapVal() {


	$("#form-fimage").bootstrapValidator({

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
			jingdu: {
				message: '经度',
				validators: {
					regexp: {//正则验证

						regexp: /^(((\d|[1-9]\d|1[1-7]\d|0)\.\d{0,4})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,4}|180)$/,

						message: '请输入正确格式的经度'

					},
				}
			},

			weidu: {
				message: '纬度',
				validators: {
					regexp: {//正则验证

						regexp: /^([0-8]?\d{1}\.\d{0,4}|90\.0{0,4}|[0-8]?\d{1}|90)$/,

						message: '请输入正确格式的纬度'

					},
				}
			},

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

						regexp: /^([1-5]?|[0-4]\.\d)$/,


						message: '满分5分,一位小数'

					},
					notEmpty: {
						message: '必填'
					}
				}
			},
			number5: {
				message: '数字',
				validators: {
					regexp: {//正则验证

						regexp: /^[1-9]\d{0,4}$/,


						message: '整数最多5位'

					}
				}
			},


		}

	});
}


$("#form-fimage").submit(function (ev) {
	if ((!$('#form-fimage').data('bootstrapValidator').isValid())) {
		return;
	}

	var url = vm.fImages.itemid == null ? "sys/fimages/save" : "sys/fimages/update";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(vm.fImages),
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
$("#form-fimage").on('success.form.bv', function (e) {
	e.preventDefault();
	var form = $(e.target);

	// console.log("   bvbvbv")
	/**提交代码**/
});