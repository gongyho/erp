$(function(){
	var url='orders_getList?t1.type='+Request['type'];
	
	var btnText="";//新增按钮文本
	var inoutDlgText;//出入库窗口的按钮
	if(Request['oper']=='myorders'){
		if(Request['type'] * 1== 1){
			url='orders_myList?t1.type=1';
			document.title="我的采购订单";
			btnText="采购申请";
			$("#supplierText").html("供应商");
		}
		if(Request['type'] * 1== 2){
			url='orders_myList?t1.type=2&t1.state=0';
			document.title="我的销售订单";
			btnText="销售申请";
			$("#supplierText").html("客户");
		}
	}
	if(Request['oper']=='doCheck'){
		url+='&t1.state=0';
		document.title="订单审核";
	}
	if(Request['oper']=='doStart'){
		url+='&t1.state=1';
		document.title="订单确认";
	}
	if(Request['oper']=='doEnd'){
		url+='&t1.state=2';
		document.title="订单";
		inoutDlgText="订单入库";
	}
	if(Request['oper']=='outStore'){
		url+="&t1.state=0";
		inoutDlgText="出库";
		inoutDlgText="订单出库";
	}
	//添加导出按钮
    $("#ordersDlg").dialog({
        toolbar: [{
            text:'导出',
            iconCls: 'icon-excel',
            handler:function () {
				$.download("orders_export?uuid="+$("#uuid").html(),[]);
            }
        }]
    });
	
	//表格
	$('#grid').datagrid({
	    url:url,
	    columns:getColumns(),
	    singleSelect:true,//单选
	    pagination:true,//分页
	    fitColumns:true,
		//双击事件
	    onDblClickRow:function(rowIndex,rowData){
	    	$("#uuid").html("");
	    	$("#supplierName").html("");
	    	$("#state").html("");
	    	$("#createrName").html("");
	    	$("#checkerName").html("");
	    	$("#starterName").html("");
	    	$("#enderName").html("");
	    	$("#createTime").html("");
	    	$("#checkTime").html("");
	    	$("#startTime").html("");
	    	$("#endTime").html("");
	    	$("#waybill").html("");
	    	
	    	$("#uuid").html(rowData.uuid);
	    	$("#supplierName").html(rowData.supplierName);
	    	$("#state").html(getState(rowData.state));
	    	$("#createrName").html(rowData.createrName);
	    	$("#checkerName").html(rowData.checkerName);
	    	$("#starterName").html(rowData.starterName);
	    	$("#enderName").html(rowData.enderName);
	    	$("#createTime").html(formatDate(rowData.createTime));
	    	$("#checkTime").html(formatDate(rowData.checkTime));
	    	$("#startTime").html(formatDate(rowData.startTime));
	    	$("#endTime").html(formatDate(rowData.endTime));
	    	$("#waybill").html(rowData.wayBills);
	    	$("#itemgrid").datagrid("loadData",rowData.orderDetails);

            //获取工具栏按钮
            var toolbar =$('#ordersDlg').dialog('options').toolbar;
            var array=new Array();
            array.push(toolbar[0]);

            //如果为 销售订单 并且已经发货添加查询物流信息按钮
            if(rowData.state*1==1 && rowData.type*1 ==2){
                //判断是否已经添加过
                if($("#waybill").html().length>0){
                    array.push({
                    	text:'物流信息',
                        iconCls: 'icon-search',
                        handler:function(){
                    		$("#waybillgrid").datagrid({
                                url:'orders_waybillList?uuid='+$("#waybill").html(),
                                columns:[[
                                    {field:'exedate',title:'执行日期',width:100},
                                    {field:'exetime',title:'执行时间',width:100},
                                    {field:'info',title:'物流信息',width:100}
                                ]]
							});
                    		$("#waybillDlg").dialog("open");
						}


                    });
                }
            }

            //重新渲染 刷新url
            $('#ordersDlg').dialog({
                toolbar:array
            });
            //打开窗口
            $('#ordersDlg').dialog('open');
	    }
	});
	$("#itemgrid").datagrid({
		columns:[[
			{field:'uuid',title:'流水号',width:100},
			{field:'goodsUuid',title:'商品编号',width:100},
		    {field:'goodsName',title:'商品名称',width:100},
		    {field:'price',title:'单价',width:100},
		    {field:'num',title:'数量',width:100},
		    {field:'money',title:'金额',width:100},
		    {field:'state',title:'状态',width:100,formatter: function(value,row,index){
	        	return getDetailState(value);
	        }},
		]],
		singleSelect:true,//单选
		fitColumns:true
	});
	//初始化添加订单窗口
	$("#addOrdersDlg").dialog({
		title:btnText,
		width: 700,
		height: 400,
		closed: true,
		modal: true
	});
	
	//如果为审核订单 添加 审核按钮
	if(Request['oper']=='doCheck'){
		$("#ordersDlg").dialog({
			toolbar: [{
				text:'审核',
				iconCls: 'icon-search',
				handler:doCheck
			}]
		});
	}
	
	//如果为确认订单 添加 确认按钮
	if(Request['oper']=='doStart'){
		$("#ordersDlg").dialog({
			toolbar: [{
				text:'确认',
				iconCls: 'icon-search',
				handler:doStart
			}]
		});
	}
	
	//如果为出入库订单
	if(Request['oper']=='doEnd'||Request['oper']=='outStore'){
		$("#storeUuid").combobox({
		    url:'store_myList',
		    valueField:'uuid',
		    textField:'name'
		});
		//为订单详情添加双击事件
		$("#itemgrid").datagrid({
			onDblClickRow:function(rowIndex,rowData){
				$("#storeDlg").dialog('open');
				$("#detailUuid").html(rowData.uuid);
				$("#goodsUuid").html(rowData.goodsUuid);
				$("#goodsName").html(rowData.goodsName);
				$("#num").html(rowData.num);
			}
		});
		//出入库窗口
		$("#storeDlg").dialog({
			title: inoutDlgText,
			height: 320,
			width: 300,
			modal:true,
			buttons:[{
				text:inoutDlgText,
				iconCls:'icon-save',
				handler:doInOutStore
			}]
		});
	}
	//如果为我的订单
	if(Request['oper']=='myorders'){
		$('#grid').datagrid({
			toolbar:[{
				text:btnText,
				iconCls:'icon-add',
				handler:function(){
					$("#addOrdersDlg").dialog('open');
				}
			}]
		});
	}
});
//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
function getState(value){
	if(Request['type']*1==1){
		switch(value * 1 ){
			case 0:return '未审核';
			case 1:return '已审核';
			case 2:return '已确认';
			case 3:return '已入库';
			default:return'';
		}
	}
	if(Request['type']*1==2){
		switch(value * 1 ){
		case 0:return '未出库';
		case 1:return '已出库';
		default:return'';
		}
	}
}

//采购: 0=未入库，1=已入库
function getDetailState(value){
	if(Request['type']*1==1){
		switch(value * 1 ){
			case 0:return '未入库';
			case 1:return '已入库';
			default:return'';
		}
	}
	if(Request['type']*1==2){
		switch(value * 1 ){
		case 0:return '未出库';
		case 1:return '已出库';
		default:return'';
		}
	}
}

/**
 * 格式化日期
 * @param date
 * @returns
 */
function formatDate(date){
	return (new Date(date)).Format("yyyy-MM-dd");
}

/**
 * 审核
 * @returns
 */
function doCheck(){
	$.messager.confirm('确定','您确认想要审核订单吗？',function(yes){
	    if (yes){
	        $.ajax({
	        	url:'orders_doCheck?uuid='+$("#uuid").html(),
	        	dataType:'json',
	        	type:'post',
	        	success:function(rtn){
	        		if(rtn.success){
	        			$.messager.alert('消息','审核成功！','info');
	        			$("#ordersDlg").dialog('close');
	        			$('#grid').datagrid('reload');
	        		}else{
	        			$.messager.alert('消息',rtn.message,'info');
	        		}
	        	}
	        });
	    }    
	});  
}

/**
 * 确认
 * @returns
 */
function doStart(){
	$.messager.confirm('确定','您确认想要确认订单吗？',function(yes){
	    if (yes){
	        $.ajax({
	        	url:'orders_doStart?uuid='+$("#uuid").html(),
	        	dataType:'json',
	        	type:'post',
	        	success:function(rtn){
	        		if(rtn.success){
	        			$.messager.alert('消息','确认成功！','info');
	        			$("#ordersDlg").dialog('close');
	        			$('#grid').datagrid('reload');
	        		}else{
	        			$.messager.alert('消息',rtn.message,'info');
	        		}
	        	}
	        });
	    }    
	});  
}
/**
 * 出入库
 * @returns
 */
function doInOutStore(){
	if(Request['type']*1==1){
		$.messager.confirm('确定','您确认想要入库改商品吗？',function(yes){
		    if (yes){
		        $.ajax({
		        	url:'orderDetail_doEnd?uuid='+$("#detailUuid").html()+'&storeUuid='+$("#storeUuid").combobox("getValue"),
		        	dataType:'json',
		        	type:'post',
		        	success:function(rtn){
		        		if(rtn.success){
		        			$.messager.alert('消息','入库成功！','info');
		        			$('#grid').datagrid('reload');
		        			$("#itemgrid").datagrid('getSelected').state=1;
		        			var data=$("#itemgrid").datagrid('getData');
		        			$("#itemgrid").datagrid('loadData',data);
		        			$("#storeDlg").dialog('close');
		        		}else{
		        			$.messager.alert('消息',rtn.message,'info');
		        			$("#storeDlg").dialog('close');
		        		}
		        	}
		        });
		    }    
		});  
	}
	if(Request['type']*1==2){
		$.messager.confirm('确定','您确认想要出库改商品吗？',function(yes){
		    if (yes){
		        $.ajax({
		        	url:'orderDetail_doOutStore?uuid='+$("#detailUuid").html()+'&storeUuid='+$("#storeUuid").combobox("getValue"),
		        	dataType:'json',
		        	type:'post',
		        	success:function(rtn){
		        		if(rtn.success){
		        			$.messager.alert('消息','出库成功！','info');
		        			$('#grid').datagrid('reload');
		        			$("#itemgrid").datagrid('getSelected').state=1;
		        			var data=$("#itemgrid").datagrid('getData');
		        			$("#itemgrid").datagrid('loadData',data);
		        			$("#storeDlg").dialog('close');
		        		}else{
		        			$.messager.alert('消息',rtn.message,'info');
		        			$("#storeDlg").dialog('close');
		        		}
		        	}
		        });
		    }    
		});  
	}
}

function getColumns(){
	if(Request['type'] * 1 ==1){
		
		return [[
			{field:'uuid',title:'编号',width:100},
			{field:'createTime',title:'生成日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'checkTime',title:'审核日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'startTime',title:'确认日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'endTime',title:'入库日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'createrName',title:'下单员',width:100},
			{field:'checkerName',title:'审核员',width:100},
			{field:'starterName',title:'采购员',width:100},
			{field:'enderName',title:'库管员',width:100},
			{field:'supplierName',title:'供应商',width:100},
			{field:'totalMoney',title:'合计金额',width:100,formatter: function(value,row,index){
				return value.toFixed(2);
			}},
			{field:'state',title:'状态',width:100,formatter: function(value,row,index){
				return getState(value);
			}},
			{field:'wayBills',title:'运单号',width:100},
			]];
	}
	if(Request['type']*1 ==2){
		
		return [[
			{field:'uuid',title:'编号',width:100},
			{field:'createTime',title:'生成日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'endTime',title:'出库日期',width:100,formatter: function(value,row,index){
				return formatDate(value);
			}},
			{field:'createrName',title:'下单员',width:100},
			{field:'enderName',title:'库管员',width:100},
			{field:'supplierName',title:'客户',width:100},
			{field:'totalMoney',title:'合计金额',width:100,formatter: function(value,row,index){
				return value.toFixed(2);
			}},
			{field:'state',title:'状态',width:100,formatter: function(value,row,index){
				return getState(value);
			}},
			{field:'wayBills',title:'运单号',width:100},
			]];
	}
	
}