$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/fcommonattr/list',
        datatype: "json",
        colModel: [			
			{ label: '序号', name: 'itemid', index: 'itemid', width: 50, key: true },
			{ label: '属性名称', name: 'title', index: 'title', width: 80 }, 			
			// { label: '地区id', name: 'areaid', index: 'areaid', width: 80 },
			{ label: '类别 ', name: 'type', index: 'type', width: 80
			,	formatter:function (cellValue, grid, rows, state) {
				if (cellValue==1){
					return  '百科';
				}else if (cellValue==2){
					return  '装修公司案例';
				}else if (cellValue==3){
					return  '楼盘'
				}else if (cellValue==4){
					return  '装修公司';
				}{
					return  '未知'
				}

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

	//
	$("#form-commonattr").bootstrapValidator({

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

						regexp: /^[1-9]\d{0,7}(\.\d{1,2})?$/,


						message: '整数最多8位，小数最多两位'

					},
					notEmpty: {
						message: '必填'
					}
				}
			},


		}

	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		fCommonAttr: {title:''},
		type:'0'
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.fCommonAttr = {};
		},
		update: function (event) {
			var itemid = getSelectedRow();
			if (itemid<9){
				alert("没有权限")
				return;
			}
			if(itemid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(itemid)
		},
		saveOrUpdate: function (event) {
			if(!$('#form-commonattr').data('bootstrapValidator').isValid()){
				return ;
			}
			var url = vm.fCommonAttr.itemid == null ? "sys/fcommonattr/save" : "sys/fcommonattr/update";
			vm.fCommonAttr.type=vm.type;
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.fCommonAttr),
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
			for (let itemidsKey in itemids) {
				if (itemidsKey<9){
					alert("没有权限")
					return;
				}

			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/fcommonattr/delete",
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
			$.get(baseURL + "sys/fcommonattr/info/"+itemid, function(r){
                vm.fCommonAttr = r.fCommonAttr;
				vm.type=r.fCommonAttr.type+'';
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