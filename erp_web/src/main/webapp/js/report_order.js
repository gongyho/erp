$(function(){
	$('#grid').datagrid({
		url:'report_orderReport',
		columns:[[
			{field:'name',title:'商品种类',width:100},
			{field:'y',title:'销售额',width:100,formatter: function(value,row,index){
				return value.toFixed(2);
			}}
		]],
		singleSelect:true,//单选
		onLoadSuccess:function(data){
			//alert(JSON.stringify(data));
			showPie(data.rows);
		}
	})
	
	//搜索按钮
	$("#btnSearch").click(function(){
		var formData=$("#searchForm").serializeJSON();
		if(formData.endDate!=''){
			formData.endDate +=" 23:59:59";
		}
		$("#grid").datagrid('load',formData);
	});
	
	
});


/**
 * 绘图
 */
function showPie(data){
	Highcharts.chart('container', {
		chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				type: 'pie'
		},
		title: {
				text: '销售统计图'
		},
		tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
				pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
								enabled: true,
								format: '<b>{point.name}</b>: {point.percentage:.1f} %',
								style: {
										color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
								}
						}
				}
		},
		series: [{
				name: '比例',
				colorByPoint: true,
				data:data
		}]
	});
}

