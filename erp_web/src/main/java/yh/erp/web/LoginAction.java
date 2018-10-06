package yh.erp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import yh.erp.entity.Emp;

public class LoginAction {
	private String userName;
	private String pwd;
	private Jedis jedis;
	
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 登陆
	 */
	public void checkLogin() {
		try {
			/*Emp loginUser=empBiz.findByUsernameAndPwd(userName, pwd);
			if(null!=loginUser) {
				ActionContext.getContext().getSession().put("loginUser", loginUser);
				ajaxReturn(true,"");
			}else {
				ajaxReturn(false, "用户名会密码错误！");
			}*/
			UsernamePasswordToken upt=new UsernamePasswordToken(userName,pwd);
			//获得主题
			Subject subject =SecurityUtils.getSubject();
			subject.login(upt);
			ajaxReturn(true, "");
		}catch(Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "登陆失败！");
		}
	}
	/**
	 * 显示用户名
	 */
	public void showUsername() {
		//Emp emp=(Emp) ActionContext.getContext().getSession().get("loginUser");
		Subject subject =SecurityUtils.getSubject();
		Emp emp=(Emp)subject.getPrincipal();
		if(null!=emp) {
			ajaxReturn(true,emp.getName());
		}else {
			ajaxReturn(false, "");
		}
	}
	
	/**
	 * 登出
	 */
	public void loginOut() {
		//ActionContext.getContext().getSession().remove("loginUser");
		Subject subject=SecurityUtils.getSubject();
		Emp emp=(Emp) subject.getPrincipal();
		try {
			jedis.del("menus_"+emp.getUuid());
		}catch(Exception e) {
			e.printStackTrace();
		}
		subject.logout();
	}
	
	/**
	 * 	状态返回
	 * @param success
	 * @param message
	 */
	private void ajaxReturn(boolean success,String message) {
		//{success:true,message:''}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success",success);
		map.put("message",message);
		write(JSON.toJSONString(map));
	}
	/**
	  *    将传入的数据转化为json，响应给客户端
	 * @param object
	 */
	private void write(String jsonString) {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
