package yh.erp.biz;

import java.util.Date;
import java.util.List;

public interface IReportBiz {
	List orderRepoet(Date startDate,Date endDate);
	List trendReport(String year);
	List getYearList(String type);
}
