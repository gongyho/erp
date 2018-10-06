package yh.erp.web;

import java.util.List;

import com.alibaba.fastjson.JSON;

import yh.erp.biz.IRoleBiz;
import yh.erp.entity.Role;
import yh.erp.entity.Tree;

public class RoleAction extends BaseAction<Role> {
	private IRoleBiz roleBiz;

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(roleBiz);
	}
	/**
	 * 读取角色菜单
	 */
	public void readRoleMenus() {
		List<Tree> treeList = roleBiz.readRoleMenus(getUuid());
		write(JSON.toJSONString(treeList));
	}
	
	private String checkedStr;
	
	public String getCheckedStr() {
		return checkedStr;
	}
	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	
	
	/**
	 * 更新角色权限
	 */
	public void updateRoleMenus() {
		String [] checkedIds=null;
		if (null!=checkedStr && checkedStr.trim().length()>0) {
			checkedIds=checkedStr.split(",");
		}else {
			ajaxReturn(false, "请选择权限！");
			return;
		}
		try {
			roleBiz.updateRoleMenus(getUuid(), checkedIds);
			ajaxReturn(true, "保存成功！");
		}catch(Exception e){
			ajaxReturn(false, "保存失败！");
			e.printStackTrace();
		}
		
	}
}
