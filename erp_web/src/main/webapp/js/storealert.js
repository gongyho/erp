$(function(){
	//表格
	$('#grid').datagrid({    
	    url:'storeDetail_getStoreAlertList',
	    columns:[[
	        {field:'uuid',title:'商品编号',width:100},
	        {field:'goodsName',title:'商品名称',width:100},
	        {field:'storeNum',title:'库存数量',width:100},
	        {field:'outNum',title:'待出库数量',width:100}
	    ]],
	    singleSelect:true,
	    toolbar: [{
	    	text:'发送预警邮件',
			iconCls: 'icon-edit',
			handler: function(){
				$.ajax({
					url:'storeDetail_sendStoreAlertMail',
					dataType:'json',
					type:'post',
					success:function(rtn){
						$.messager.alert("提示",rtn.message,'info');
					}
				});
			}
		}]

	});
});