package yh.erp.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.redsum.bos.ws.Waybilldetail;

import yh.erp.biz.IOrdersBiz;
import yh.erp.entity.Emp;
import yh.erp.entity.OrderDetail;
import yh.erp.entity.Orders;
import yh.erp.exception.ErpException;

public class OrdersAction extends BaseAction<Orders> {
	private IOrdersBiz ordersBiz;


	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		super.setBaseBiz(ordersBiz);
	}
	
	private String json;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * 我的订单
	 */
	public void myList() {
		if(null==getT1()) {
			setT1(new Orders());
		}
		getT1().setCreater(getLoginUser().getUuid());
		super.getList();
	}
	
	/**
	 * 保存订单
	 */
	public void saveOrUpdate() {
		Emp emp=getLoginUser();
		if(null==emp) {
			ajaxReturn(false, "你还没有登陆");
			return;
		}
		Orders orders=getT();
		List<OrderDetail> detailList = JSON.parseArray(json, OrderDetail.class);
		orders.setOrderDetails(detailList);
		orders.setCreater(emp.getUuid());
		try {
			ordersBiz.saveOrUpdate(orders);
			ajaxReturn(true, "添加成功");
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch(Exception e) {
			ajaxReturn(false, "添加失败");
		}
	}
	
	/**
	 * 	审核订单
	 */
	public void doCheck() {
		Emp loginUser=getLoginUser();
		if(null==loginUser) {
			ajaxReturn(false,"还没有登陆");
			return;
		}
		try {
			ordersBiz.doCheck(getUuid(), loginUser.getUuid());
			ajaxReturn(true, "");
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch(UnauthorizedException e) {
			ajaxReturn(false, "权限不足");
		}catch(Exception e) {
			ajaxReturn(false, "审核失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 	确认订单
	 */
	public void doStart() {
		Emp loginUser=getLoginUser();
		if(null==loginUser) {
			ajaxReturn(false,"还没有登陆");
			return;
		}
		try {
			ordersBiz.doStart(getUuid(), loginUser.getUuid());
			ajaxReturn(true, "");
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch(UnauthorizedException e) {
			ajaxReturn(false, "权限不足");
		}catch(Exception e) {
			ajaxReturn(false, "确认失败");
			e.printStackTrace();
		}
	}

	/**
	 * 导出
	 */
	public void export(){
		HttpServletResponse response=  ServletActionContext.getResponse();
		String fileName="订单详情.xls";
		try {
			response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes(),"ISO-8859-1"));
			ordersBiz.export(response.getOutputStream(),getUuid());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waybillList(){
		List<Waybilldetail> list =ordersBiz.waybillList(getUuid());
		write(JSON.toJSONString(list));
	}
}
