package yh.erp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;

import yh.erp.biz.IBaseBiz;
import yh.erp.entity.Emp;

public class BaseAction<T> {
private IBaseBiz<T> baseBiz;
	
	public void setBaseBiz(IBaseBiz<T> baseBiz) {
		this.baseBiz = baseBiz;
	}
	
	
	//条件信息 t2 和param扩展参数 如有截至日期，或者复选框信息，接口不变方便扩展
	private T t1;
	private T t2;
	private Object param;
	public T getT1() {
		return t1;
	}
	public void setT1(T t1) {
		this.t1 = t1;
	}
	public T getT2() {
		return t2;
	}
	public void setT2(T t2) {
		this.t2 = t2;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}

	//分页信息
	private int page;
	private int rows;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 *   获取t 数据
	 */
	public void getList() {
		int firstResult =(page-1)*rows;
		//获取数据
		List<T> dataList=baseBiz.getList(t1,t2,param,firstResult,rows);
		//获取结果数目
		long total =baseBiz.getCount(t1,t2,param);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", dataList);
		write(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
	}

	/**
	 * 加载所有信息
	 */
	public void list() {
		write(JSON.toJSONString(baseBiz.getList(t1,null,null)));
	}
	
	//封装添加 编辑信息
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	/**
	 * 添加
	 */
	public void saveOrUpdate() {
		try {
			baseBiz.saveOrUpdate(t);
			ajaxReturn(true,"操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false,"操作失败");
		}
	}
	//删除id
	private int uuid;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	/**
	 * 删除
	 */
	public void delete() {
		try {
			baseBiz.delete(uuid);
			ajaxReturn(true,"删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false,"删除失败");
		}
	}
	/**
	 * 加载数据
	 */
	public void get() {
		T t=(T) baseBiz.get(uuid);
		String jsonString =JSON.toJSONStringWithDateFormat(t,"yyyy-MM-dd");
		Map<String,Object> map=addPerfix(jsonString, "t");
		write(JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd"));
	}
	
	/**
	 * 	给参数名称添加前缀
	 * @param jsonString
	 * @param perfix
	 * @return
	 */
	public Map<String,Object> addPerfix(String jsonString,String perfix){
		Map<String,Object> map=(Map<String, Object>) JSON.parseObject(jsonString);
		Map<String,Object> dataMap=new HashMap<String,Object>();
		for(String key:map.keySet()) {
			if(map.get(key) instanceof Map) {
				Map<String,Object> map2=(Map<String,Object>)map.get(key);
				for(String key2:map2.keySet()) {
					dataMap.put(perfix + "." +key+"."+key2, map2.get(key2));
				}
			}else {
				dataMap.put(perfix + "." +key, map.get(key));
			}
		}
		return dataMap;
	}
	
	public Emp getLoginUser() {
		//Emp emp = (Emp) ActionContext.getContext().getSession().get("loginUser");
		return (Emp)SecurityUtils.getSubject().getPrincipal();
		
	}
	
	/**
	 * 	状态返回
	 * @param success
	 * @param message
	 */
	public void ajaxReturn(boolean success,String message) {
		//{success:true,message:''}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success",success);
		map.put("message",message);
		write(JSON.toJSONString(map));
	}
	/**
	  *    将传入的数据转化为json，响应给客户端
	 * @param jsonString
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
