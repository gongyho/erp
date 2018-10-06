package yh.erp.web;

import java.util.List;

import com.alibaba.fastjson.JSON;

import yh.erp.biz.IEmpBiz;
import yh.erp.entity.Emp;
import yh.erp.entity.Menu;
import yh.erp.entity.Tree;

public class EmpAction extends BaseAction<Emp> {
	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(empBiz);
	}
	
	private String oldPwd;
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	public void updatePwd() {
		Emp emp=getLoginUser();
		if(null==emp) {
			ajaxReturn(false,"请登陆！");
			return;
		}
		try {
			empBiz.updatePwd(emp.getUuid(),oldPwd,newPwd);
			ajaxReturn(true,"");
		}catch(Exception e) {
			ajaxReturn(false, e.getMessage());
		}
	}
	
	/**
	 * 读取员工角色
	 */
	public void readEmpRoles(){
		List<Tree> list=empBiz.readEmpRoles(getUuid());
		write(JSON.toJSONString(list));
	}
	
	
	private String checkedStr;
	
	public String getCheckedStr() {
		return checkedStr;
	}
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	/**
	 * 更新员工角色
	 */
	public void updateEmpRoles() {
		String [] checkedIds=null;
		if (null!=checkedStr && checkedStr.trim().length()>0) {
			checkedIds=checkedStr.split(",");
		}else {
			ajaxReturn(false, "请选择角色！");
			return;
		}
		
		try {
			empBiz.updateEmpRoles(getUuid(), checkedIds);
			ajaxReturn(true, "保存成功！");
		}catch(Exception e) {
			ajaxReturn(false, "保存失败！");
			e.printStackTrace();
		}
	
	};
}
