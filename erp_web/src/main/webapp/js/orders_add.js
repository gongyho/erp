var exitEditIndex=-1;
$(function(){
	$('#ordersgrid').datagrid({
	url:'',
	//只能选择一行
	singleSelect:true,
	//显示行脚
	showFooter:true,
	//显示列序号
	rownumbers:true,
	//选中行时事件
	onClickRow:function(index,data){
		$('#ordersgrid').datagrid('endEdit',exitEditIndex);
		$('#ordersgrid').datagrid('beginEdit',index);
		exitEditIndex=index;
		//绑定计算金额事件
		setBind();
	},
	columns:[[
	    {field:'goodsUuid',title:'商品编号',width:100,editor:{type:'numberbox',options:{disabled:true}}},
	    {field:'goodsName',title:'商品名称',width:100,editor:{type:'combobox',options:{
	    	url:'goods_list',
	        valueField:'name',
	        textField:'name',
	        //下拉选择框选择事件
	        onSelect:function(rec){
	        	$(getEditor('goodsUuid').target).val(rec.uuid);
	        	if(Request['type']*1==1){
	        		$(getEditor('price').target).val(rec.inPrice);
	        	}
	        	if(Request['type']*1==2){
	        		$(getEditor('price').target).val(rec.outPrice);
	        	}
	        	$(getEditor('num').target).select();
	        	//重新计算金额
	        	cal();
	        	sum();
	        }
	    }}},
	    {field:'price',title:'价格',width:100,editor:{type:'numberbox',options:{precision:2}}},
	    {field:'num',title:'数量',width:100,editor:'numberbox'}, 
	    {field:'money',title:'总金额',width:100,editor:{type:'numberbox',options:{precision:2,disabled:true}}},
	    {field:'-',title:'操作', width:80,
			formatter: function(value,row,index){
				if(row['num']!='合计'){
					return '<a href="javascript:void(0)" onclick="del('+index+')">删除</a>';
				}
			}
	    }
	]],
	toolbar: [{
		text:'添加',
		iconCls: 'icon-add',
		handler: function(){
			if(exitEditIndex !=-1){
				$('#ordersgrid').datagrid('endEdit',exitEditIndex);
			}
			$('#ordersgrid').datagrid('appendRow',{
				num:0,
				money:0,
				price:0
			});
			var rows=$('#ordersgrid').datagrid('getRows');
			//开始编辑
			$('#ordersgrid').datagrid('beginEdit',rows.length-1);
			exitEditIndex=rows.length-1;
			//绑定计算金额事件
			setBind();
		}
	},'-',{
		text:'保存',
		iconCls: 'icon-save',
		handler: function(){
			if(exitEditIndex!=-1){//存在编辑的关闭
				$('#ordersgrid').datagrid('endEdit',exitEditIndex);
			}
			var rows=$('#ordersgrid').datagrid('getRows');
			if(rows.length==0){
				return;
			}
			var formData=$('#orderForm').serializeJSON();
			formData.json=JSON.stringify(rows);
			//alert(JSON.stringify(formData));
			$.ajax({
				url:'orders_saveOrUpdate?t.type='+Request['type'],
				data:formData,
				dataType:'json',
				type:'post',
				success:function(rtn){
					$.messager.alert("提示",rtn.message,'info',function(){
						$('#supplier').combogrid('clear');
						$('#ordersgrid').datagrid('loadData',{total:1,rows:[],footer:[{num: '合计',money:0}]});
						$("#addOrdersDlg").dialog('close');
						$('#grid').datagrid('reload');
					});
				}
			});
		}
	}]
	});
	// 更新页脚行并载入新数据
	$('#ordersgrid').datagrid('reloadFooter',[
		{num: '合计',money: 0}
	]);
	//初始化下拉列表框
	$('#supplier').combogrid({    
	    panelWidth:600,    
	     
	    idField:'uuid',    
	    textField:'name',    
	    url:'supplier_list?t1.type='+Request['type'],    
	    columns:[[    
	    	{field:'name',title:'名称',width:100},
	        {field:'address',title:'地址',width:100},
	        {field:'contact',title:'联系人',width:100},
	        {field:'tele',title:'电话',width:100},
	        {field:'email',title:'邮件地址',width:100} 
	    ]],
	    mode:'remote'
	});  

});

//删除行
function del(index){
	//结束正在编辑行
	$('#ordersgrid').datagrid('endEdit',exitEditIndex);
	$('#ordersgrid').datagrid('deleteRow',index);
	//重新获取数据 重新加载表格
	var fromData =$('#ordersgrid').datagrid('getData');
	cal();
	sum();
	$('#ordersgrid').datagrid('loadData',fromData);
	exitEditIndex=-1;
}

/**
 * 计算合计
 */
function sum(){
	var rows=$('#ordersgrid').datagrid('getRows');
	if(rows==0){
		$('#ordersgrid').datagrid('reloadFooter',[
			{num: '合计',money:0}
		]);
	}
	var total= 0.0;
	$.each(rows,function(index,row){
		total+=parseFloat(row.money);
	})
	$('#ordersgrid').datagrid('reloadFooter',[
		{num: '合计',money: total.toFixed(2)}
	]);
}

/**
 * 绑定事件方法
 * @returns
 */
function setBind(){
	$(getEditor('num').target).bind('keyup',function(){
		cal();
		sum();
	});
	$(getEditor('price').target).bind('keyup',function(){
		cal();
		sum();
	});
}

/**
 * 计算金额
 * @returns
 */
function cal(){
	if(getEditor('num')==null){
		return;
	}else{
		var num=$(getEditor('num').target).val();
		var price=$(getEditor('price').target).val();
		var money=(num*price).toFixed(2);
		$(getEditor('money').target).val(money);
		var rows=$('#ordersgrid').datagrid('getRows');
		rows[exitEditIndex].money=money;
	}
}

/**
 * 得到当前编辑的行的编辑器
 * @param field
 * @returns
 */
function getEditor(field){
	return $('#ordersgrid').datagrid('getEditor',{index:exitEditIndex,field:field});
}