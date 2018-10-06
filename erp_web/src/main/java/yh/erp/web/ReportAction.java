package yh.erp.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import yh.erp.biz.IReportBiz;

public class ReportAction {
	private IReportBiz reportBiz;
	
	public void setReportBiz(IReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}
	
	
	private Date startDate;
	private Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取销售信息
	 */
	public void orderReport() {
		List list=reportBiz.orderRepoet(startDate,endDate);
		write(JSON.toJSONString(list));
	}
	
	private String year;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * 销售趋势
	 */
	public void trendReport() {
		List list=reportBiz.trendReport(year);
		write(JSON.toJSONString(list));
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void getYearList() {
		write(JSON.toJSONString(reportBiz.getYearList(type)));
	}
	/**
	  *    将传入的数据转化为json，响应给客户端
	 * @param object
	 */
	public void write(String jsonString) {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
