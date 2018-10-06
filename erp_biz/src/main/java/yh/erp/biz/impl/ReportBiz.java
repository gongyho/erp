package yh.erp.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yh.erp.biz.IReportBiz;
import yh.erp.dao.IReportDao;

public class ReportBiz implements IReportBiz {
	private IReportDao reportDao;
	
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public List orderRepoet(Date startDate,Date endDate) {
		return reportDao.orderReport(startDate,endDate);
	}

	@Override
	public List trendReport(String year) {
		//[{"name":"01","y":521.0},{"name":"02","y":423.0},{"name":"05","y":798.0},{"name":"07","y":316.5}]
		List<Map<String,Object>> dataList=reportDao.trendReport(year, "2");
		List<Map<String,Object>> rtnList=new ArrayList<Map<String,Object>>();
		boolean conf=false;
		for(int i=1;i<=12;i++) {
			for(Map<String,Object> map :dataList) {
				if(map.get("name").equals(formatToString(i))) {
					Map<String,Object> tempMap=new HashMap<String,Object>();
					tempMap.put("name",i+"月");
					tempMap.put("y",map.get("y"));
					rtnList.add(tempMap);
					conf =true;
					break;
				}
			}
			if(!conf) {
				Map<String,Object> tempMap=new HashMap<String,Object>();
				tempMap.put("name",i+"月");
				tempMap.put("y",0);
				rtnList.add(tempMap);
			}
			conf=false;
		}
		return rtnList;
	}
	@Override
	public List getYearList(String type) {
		return reportDao.getYearList(type);
	}
	/**
	 * 将 数字 （1位 2位）转化为2位字符串
	 * @param i
	 * @return
	 */
	private String formatToString(int i) {
		if(i<10) {
			return 0+""+i;
		}else{
			return i+"";
		}
	}

}
