$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/ffitupcompany/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true },
			{ label: '企业名称', name: 'companyName', index: 'company_name', width: 80 }, 			
			{ label: '公司形象图', name: 'image', index: 'image',
				formatter: showPicture,
				edittype: 'file',
				editoptions: {enctype: "multipart/form-data"},
				width: 80 },
			{ label: '地址', name: 'address', index: 'address', width: 80 }, 			
			{ label: '联系电话', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '状态', name: 'status', index: 'status',
				formatter:function(cellvalue){
				if (cellvalue==0){
					return '正常'
				}else if (cellvalue==1){
					return '已删除'
				}
				},
				width: 80 },
			{ label: '施工方式（时长）', name: 'allHalfContracting', index: 'allHalfContracting',
				formatter:function(cellValue, grid, rows, state){
					if (cellValue==1){
						return '全包（'+rows.allorhalfTimes +'）  '
					}else if (cellValue==2){
						return '半包（'+rows.allorhalfTimes +'）'
					}else {
						return  '未知'
					}
				},
				width: 80 },

			// { label: '企业简介', name: 'content', index: 'content',		formatter: showContent, width: 80 },
			// { label: '售后质保', name: 'warranty', index: 'warranty', width: 80 },
			// { label: '营业时间', name: 'businessHours', index: 'business_hours', width: 80 },
			{ label: '评分', name: 'score', index: 'score', width: 80 },
			{ label: '案例数量', name: 'caseCount', index: 'case_count', width: 80 }, 			
			{ label: '设计师人数', name: 'designerCount', index: 'designer_count', width: 80 }, 			
			// { label: '标签2，存储方式{“到店礼”：“送手机”；}', name: 'tag2', index: 'tag2', width: 80 },
			// { label: '标签1，多个用;隔开', name: 'tag1', index: 'tag1', width: 80 },
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			{ label: '添加人', name: 'addUserName', index: 'addUserName', width: 80 },
			// { label: '修改时间', name: 'updatetime', index: 'updatetime', width: 80 }
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
}

function handlePictureBig(fileurl) {
	// console.log(fileurl)
	vm.dialogImageUrl = fileurl;
	vm.dialogVisible = true;

}

function   bootstrapVal(){


	$("#form-fitup-company").bootstrapValidator({

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

	});}
var vm = new Vue({
	el:'#rrapp',
	data:{
		attrandattrValue: [],
		attrs: [],
		attr: 0,
		attrValues: [],
		attrValue: [],
		activeNames: ['1'],
		tag2Title:'',
		tag2Content:'',
		tag2s:[],
		showList: true,
		title: null,
		dialogImageUrl: '',
		fileList: [],
		probablyDeleteImg:'',
		dynamicTags: [],
		inputVisible: false,
		bj:true,
		inputValue: '',
		dialogText: '',
		dialogVisible: false,
		dialogVisibleC: false,
		fFitupCompany: {}
	},
	methods: {
		selectAttr1: function () {
			vm.attrValue = [];
			console.log(vm.attr)
			$.ajax({
				type: "POST",
				url: baseURL + 'sys/fcommonattrvalue/onlylist',
				contentType: "application/json",
				data: JSON.stringify({attrId: vm.attr}),
				success: function (r) {
					if (r.code === 0) {

						vm.attrValues = r.list;


					} else {
						alert(r.msg);
					}
				}
			});


			for (var i = 0; i < vm.attrandattrValue.length; i++) {

				if (vm.attr == vm.attrandattrValue[i].attrId) {  //如果选择的属性已经有选择的属性值 则显示出来
					vm.attrValue.push(vm.attrandattrValue[i].attrValueId)
				}

			}


		},
		selectAttr: function () {

			if (vm.attr == 0) {
				alert('请先选择属性再选择属性值')
				return;
			}
			// vm.attrandattrValue=[];
			console.log(vm.attrandattrValue.length + '    qian')


			for (var i = 0; i < vm.attrandattrValue.length; i++) {
				// console.log(vm.attrandattrValue[0])
				// console.log(vm.attr)
				if (vm.attrandattrValue[i].attrId == vm.attr) {
					// console.log(1)
					vm.attrandattrValue.splice(vm.attrandattrValue.indexOf(vm.attrandattrValue[i]), 1);
					i = i - 1;
					// console.log(vm.attrandattrValue.length+'     nei'+i)
				}


			}

			// console.log(vm.attrandattrValue.length+'    hou')

			var attrValue = vm.attrValue;
			for (var i = 0; i < attrValue.length; i++) {
				var attrValue1 = {attrId: 0, attrValueId: 0, attr: '', attrValue: '', houseId: 0};
				attrValue1.attrValueId = attrValue[i];
				attrValue1.attrId = vm.attr;
				attrValue1.companyId = vm.fFitupCompany.itemid;

				for (var q = 0; q < vm.attrs.length; q++) {
					if (vm.attrs[q].itemid == vm.attr) {
						attrValue1.attr = vm.attrs[q].title;
					}
				}
				for (var e = 0; e < vm.attrValues.length; e++) {
					if (vm.attrValues[e].valueId == attrValue[i]) {
						attrValue1.attrValue = vm.attrValues[e].title;
					}
				}


				if (true) {
					vm.attrandattrValue.push(attrValue1)
				}


			}

		},
		attrandattrValues: function () {
			var url = "sys/fcommonattr/onlylist";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify({type: 4,bj:1}),
				success: function (r) {
					if (r.code === 0) {

						vm.attrs = r.list;
						// console.log('     cccc    '+vm.selVal.length)


					} else {
						alert(r.msg);
					}
				}
			});


		},

		saveTag2s:function(){
			if (vm.tag2Title==null||vm.tag2Title==''||vm.tag2Content==null||vm.tag2Content==''){
				return;
			}
			if (vm.tag2s.length==3){
				alert("最多3个")
				return;
			}
			var  tag2=vm.tag2Title+':'+vm.tag2Content;

			vm.tag2s.push(tag2);
			vm.tag2Content='';
			vm.tag2Title='';


		},


		showInput: function () {
			this.inputVisible = true;
			this.$nextTick(
				function () {
					this.$refs.saveTagInput.$refs.input.focus();
				}
			);
		},

		handleInputConfirm: function () {
			var inputValue = this.inputValue;
			if (inputValue) {
				this.dynamicTags.push(inputValue);
			}
			if (this.dynamicTags.length<3){
			this.inputVisible = false;

			}else {
				this.inputVisible = true;
				this.bj = false;
			}
			this.inputValue = '';
		},
		handleClose1: function (tag) {

			this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
			if (this.dynamicTags.length<3){
				this.inputVisible = false;
				this.bj = true;
			}




		},
		handleClose2: function (tag) {

			this.tag2s.splice(this.tag2s.indexOf(tag), 1);

		},
		handleClose3: function (tag) {

			this.attrandattrValue.splice(this.attrandattrValue.indexOf(tag), 1);


			//还要进行对应attrValue[]的修改，或者重覆
		},

		onSuccess(res) {

			if (res.code==0){
				vm.fFitupCompany.image=res.urls[0].imgUrl;
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
			vm.fFitupCompany.image=null;
			// console.log(vm.probablyDeleteImg);
			var delUrl=vm.probablyDeleteImg;

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
			$('#form-fitup-company').data('bootstrapValidator',null);
			bootstrapVal();
			vm.attr=0;
			vm.attrValue=0;
			vm.attrandattrValues();
			vm.showList = false;
			vm.title = "新增";
			vm.dynamicTags=[];
			vm.tag2s=[];
			vm.fFitupCompany = {};
			vm.tag2Title='';
			vm.tag2Content='';


			vm.attrandattrValue= [],
			vm.fileList=[];
		},
		update: function (event) {
			var itemid = getSelectedRow();
			if(itemid == null){
				return ;
			}
			$('#form-fitup-company').data('bootstrapValidator',null);
			bootstrapVal();
			vm.showList = false;
            vm.title = "修改";
			vm.fileList=[];
			vm.attrandattrValues();
			vm.dynamicTags=[];
			vm.tag2s=[];
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
				    url: baseURL + "sys/ffitupcompany/delete",
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
			$.get(baseURL + "sys/ffitupcompany/info/"+itemid, function(r){
                vm.fFitupCompany = r.fFitupCompany;
				/**
				 * 标签封装
				 */
				var   str1= r.fFitupCompany.tag1
				if (str1 == null) {
					vm.dynamicTags = [];
				} else {
					var split = str1.split(";");
					var re = split.filter(function (s) {
						return s && s.trim();
					})
					vm.dynamicTags = re;
				}
				var   str2= r.fFitupCompany.tag2
				if (str2 == null) {
					vm.tag2s = [];
				} else {
					var split = str1.split(";");
					var re = split.filter(function (s) {
						return s && s.trim();
					})
					vm.tag2s = re;
				}
				/**
				 * 图片的回显
				 */

				var  strm=r.fFitupCompany.image
				var split = strm.split("/");
				vm.fileList=[{name : split[split.length-1],url: "http://localhost:81"+r.fFitupCompany.image}]
				/**
				 * 属性  属性值封装
				 */
console.log(r.skus)
				vm.attrandattrValue = r.skus;




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
$("#form-fitup-company").submit(function(ev){
	if((!$('#form-fitup-company').data('bootstrapValidator').isValid())){
		return ;
	}

	/**
	 * 属性属性值封装
	 */
	vm.fFitupCompany.attrandattrValue = vm.attrandattrValue;
	console.log(vm.attrandattrValue)


	/**
	 * 标签封装
	 */
	if (vm.tag2s.length>0){
		var tag2s = '';
		vm.tag2s.forEach(function (value) {
			tag2s += (value + ';')

		})
		vm.fFitupCompany.tag2=tag2s;

	}

	if (vm.dynamicTags.length>0){
		var tag1s = '';
		vm.dynamicTags.forEach(function (value) {
			tag1s += (value + ';')

		})
		vm.fFitupCompany.tag1=tag1s;
	}



	var url = vm.fFitupCompany.itemid == null ? "sys/ffitupcompany/save" : "sys/ffitupcompany/update";
	$.ajax({
		type: "POST",
		url: baseURL + url,
		contentType: "application/json",
		data: JSON.stringify(vm.fFitupCompany),
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
$("#form-fitup-company").on('success.form.bv', function(e) {
	e.preventDefault();
	var form = $(e.target);

	// console.log("   bvbvbv")
	/**提交代码**/
});