var listParam='';
var saveParam='';
$(function(){
	//表格
	$('#grid').datagrid({    
	    url:name+'_getList'+listParam,
	    columns:columns,
	    singleSelect:true,
	    pagination:true,
	    toolbar: [{
	    	text:'新增',
			iconCls: 'icon-add',
			handler: function(){//点击新增打开窗口
				$('#editForm').form('clear');//清除 缓存 避免保留主键不能保存
				$('#editDlog').dialog('open');
			}
		},"-",{
            text:'导出',
            iconCls: 'icon-excel',
            handler: function(){
            	var data=$("#searchForm").serializeJSON();
                $.download(name+'_export'+listParam,data);
            }
        },"-",{
            text:'导入',
            iconCls: 'icon-add',
            handler: function(){
            	var importDlg=document.getElementById("importDlg");
            	if(importDlg){
                    $('#importDlg').dialog('open');
                }
            }
        }]

	});
	//搜索按钮
	$("#btnSearch").click(function(){
		var formData=$("#searchForm").serializeJSON();
		//按源url 添加参数 重新加载数据
		$("#grid").datagrid('load',formData);
		//alert(JSON.stringify($("#grid").datagrid('getData')));
		//alert(JSON.stringify(formData));
	});
	
	//默认编辑窗口大小
	var w=300;
	var h=200;
	if("undefined" != typeof(width)){
		w=width;
	}
	if("undefined" != typeof (height)){
		h=height;
	}
	
	//编辑窗口
	$('#editDlog').dialog({    
	    title: '编辑',
	    width: w,
	    height: h,
	    closed: true, //窗口是否为关闭 
	    modal: true
	});
	//初始化 选择文件窗口
    $('#importDlg').dialog({
		title:"导入文件",
		width:300,
		height:100,
		closed:true,
		modal:true,
        buttons:[{
            text:'保存',
            handler:function(){
				$.ajax({
					url:name+"_upload",
					type:'post',
					data:new FormData($("#importForm")[0]),
					dataType:'json',
					processData:false,
					contentType:false,
					success:function (rtn) {
                        $.messager.alert("提示",rtn.message,'info',function(){
                            if(rtn.success==true){
                                $('#grid').datagrid('reload');
                                $('#importDlg').dialog('close');
                            }
                        });
                    }
				});
			}
        }]

    });

	//保存按钮
	$("#btnSave").click(function(){
		var formData=$("#editForm").serializeJSON();
		$.ajax({
			url:name+'_saveOrUpdate'+saveParam,
			data:formData,
			dataType:'json',
			type:'post',
			success:function(result){
				$.messager.alert("提示",result.message,'info',function(){
					if(result.success==true){
						$('#grid').datagrid('reload');
						$('#editDlog').dialog('close');
					}
				});
			}
		});
	});
});

//删除
function del(uuid){
	$.messager.confirm('提示信息', '是否确定删除？', function(r){
		if (r){
			$.ajax({
				url:name+'_delete?uuid='+uuid,
				dataType:'json',
				type:'post',
				success:function(result){
					$.messager.alert("提示",result.message,'info',function(){
						if(result.success==true){
							$('#grid').datagrid('reload');
						}
					});
				}
			});
		}
	});
}
//编辑
function edit(uuid) {
	$('#editDlog').dialog('open');
	$('#editForm').form('clear');
	$('#editForm').form('load',name+'_get?uuid='+uuid);
}