$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fprogrammehouse/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '小区', name: 'programmeVillageName', index: 'programmeVillageName', width: 80 },
			{ label: '户型结构', name: 'houseStructure', index: 'houseStructure', width: 80 },
			{ label: '图片地址', name: 'image', index: 'image',
				formatter: showPicture,
				edittype: 'file',
				editoptions: {enctype: "multipart/form-data"},
				width: 80 },
			{ label: '是否有全景,默认0,1有', name: 'isVr', index: 'is_vr',
				formatter:function(cellvalue){
				if (cellvalue==0){
					return  '无'
				}else if (cellvalue==1){
					return '有'
				}else {
					return '未知'
				}

				},
				width: 80 },
			{ label: '建筑面积', name: 'builtArea', index: 'built_area', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', index: 'addtime', width: 80 }, 			
			{ label: '添加人', name: 'addUserName', index: 'addUserName', width: 80 },
			// { label: '所属地区id', name: 'areaid', index: 'areaid', width: 80 },
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



    $("#form-programme-house").bootstrapValidator({

        // live: 'submitted',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证

        // excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的

        // submitButtons: '#submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面

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
        fileList: [],
		dialogImageUrl: '',
        selVillageVal: [],
		dialogVisible: false,
		title: null,
		fProgrammeHouse: {image:''}
	},
	methods: {
        onSuccess(res) {

            if (res.code==0){
                vm.fProgrammeHouse.image=res.urls[0].imgUrl;
                this.$alert('上传成功！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {

                    },
                })
            }

        },
        handleChange(file, fileList) {
            console.log(fileList)
            if (fileList.length > 0) {
                vm.fileList = [fileList[fileList.length - 1]]  // 这一步，是 展示最后一次选择的csv文件

            }
            console.log(vm.fileList.length+'   cc')
            // if (vm.fileList.length==0){
            //     console.log(0)
            //     vm.fProgrammeHouse.image=null;
            // }
        },


        handlePictureCardPreview(file) {
            vm.dialogImageUrl = file.url;
            vm.dialogVisible = true;
        },

        handleRemove(file, fileList) {
            vm.fProgrammeHouse.image=null;
            console.log(file, fileList);
        },
        getOptions3: function () {
            var url = "sys/fprogrammevillage/onlyList";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({}),
                success: function (r) {
                    if (r.code === 0) {


                        vm.selVillageVal = r.list;


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
            vm.getOptions3();
			vm.showList = false;
			vm.title = "新增";
			vm.fProgrammeHouse = {};
            vm.fileList=[];
		},
		update: function (event) {
            vm.getOptions3();
			var itemid = getSelectedRow();
			if(itemid == null){
				return ;
			}
			vm.fileList=[];
            vm.fProgrammeHouse = {};
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
				    url: baseURL + "sys/fprogrammehouse/delete",
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
			$.get(baseURL + "sys/fprogrammehouse/info/"+itemid, function(r){
                vm.fProgrammeHouse = r.fProgrammeHouse;
                var  str=r.fProgrammeHouse.image
                var split = str.split("/");
                vm.fileList=[{name : split[split.length-1],url: "http://localhost:81"+r.fProgrammeHouse.image}]
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

$("#form-programme-house").submit(function (ev) {
    if(!$('#form-programme-house').data('bootstrapValidator').isValid()){
        return ;
    }
    var url = vm.fProgrammeHouse.itemid == null ? "sys/fprogrammehouse/save" : "sys/fprogrammehouse/update";
    $.ajax({
        type: "POST",
        url: baseURL + url,
        contentType: "application/json",
        data: JSON.stringify(vm.fProgrammeHouse),
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
$("#form-programme-house").on('success.form.bv', function (e) {
    e.preventDefault();
    var form = $(e.target);

    // console.log("   bvbvbv")
    /**提交代码**/
});