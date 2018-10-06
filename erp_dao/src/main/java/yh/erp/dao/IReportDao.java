package yh.erp.dao;

import java.util.Date;
import java.util.List;

public interface IReportDao {
	List orderReport(Date startDate,Date endDate);
	public List trendReport(String year,String type);
	List getYearList(String type);
}
