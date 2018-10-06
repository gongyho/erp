$(function(){
	$("#grid").datagrid({
		url:"role_getList",
		singleSelect:true,
		columns:[[
		    {field:'uuid',title:'角色编号',width:100},
		    {field:'name',title:'角色名称',width:100}
		]],
		onClickRow:function(rowIndex, rowData){
			$('#tree').tree({
				url:'role_readRoleMenus?uuid='+rowData.uuid
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
			url:"role_updateRoleMenus",
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