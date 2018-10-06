$(function(){
	$("#grid").datagrid({
		url:"emp_getList",
		singleSelect:true,
		columns:[[
		    {field:'uuid',title:'用户编号',width:100},
		    {field:'name',title:'用户名称',width:100}
		]],
		onClickRow:function(rowIndex, rowData){
			$('#tree').tree({
				url:'emp_readEmpRoles?uuid='+rowData.uuid
			});
		}
	});
	$('#tree').tree({
		animate:true,
		checkbox:true,
	});
	$("#btnSave").bind("click",function(){
		var checked=$('#tree').tree("getChecked");
		var ids=new Array();
		$(checked).each(function(i,item){
			ids.push(item.id);
		});
		var checkedStr=ids.join(",");
		
		$.ajax({
			url:"emp_updateEmpRoles",
			data:{
				uuid:uuid=$("#grid").datagrid("getSelected").uuid,
				checkedStr:checkedStr
			},
			type:'post',
			dataType:'json',
			success:function(rtn){
				$.messager.alert("提示",rtn.message,'info',function(){
					$('#tree').tree("reload");
				});
			}
		});
	});

});