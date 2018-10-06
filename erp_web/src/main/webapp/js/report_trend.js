$(function(){
	$('#grid').datagrid({
		url:'report_trendReport',
		columns:[[
			{field:'name',title:'月份',width:100},
			{field:'y',title:'销售额',width:100,formatter: function(value,row,index){
				return value.toFixed(2);
			}}
		]],
		singleSelect:true,//单选
		onLoadSuccess:function(data){
			showPie(data.rows);
		}
	})
	
	$('#yearText').combobox({    
		url:'report_getYearList?type=2',
		valueField:'year',
		textField:'year',
		onSelect:function(record){
			$("#grid").datagrid('load',{'year':record.year});
		},
		onLoadSuccess:function(){
			$('#yearText').combobox('select',new Date().getFullYear())
		}
	});  
	
	
	//搜索按钮
	$("#btnSearch").click(function(){
		var formData=$("#searchForm").serializeJSON();
		$("#grid").datagrid('load',formData);
	});
});


/**
 * 绘图
 */
function showPie(data){
	var chart = Highcharts.chart('container', {
		title: {
				text: $("#yearText").combobox("getValue")+'年销售趋势图'
		},
		subtitle: {
				text: '数据来源：yhoo.fun'
		},
		yAxis: {
				title: {
						text: '销售额'
				}
		},
		legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
		},
		 xAxis: {  //x轴 
			categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月','11月', '12月'],
			gridLineWidth: 1, //设置网格宽度为1 
			lineWidth: 2,  //基线宽度 
			labels:{y:26}  //x轴标签位置：距X轴下方26像素 
		}, 
		series: [{
				name: '销售额',
				data: data
		}],
		responsive: {
				rules: [{
						condition: {
								maxWidth: 500
						},
						chartOptions: {
								legend: {
										layout: 'horizontal',
										align: 'center',
										verticalAlign: 'bottom'
								}
						}
				}]
		}
});
}

