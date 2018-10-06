package yh.erp.web;

import com.alibaba.fastjson.JSON;

import yh.erp.biz.IEmpBiz;
import yh.erp.biz.IMenuBiz;
import yh.erp.entity.Menu;

public class MenuAction extends BaseAction<Menu> {
	private IMenuBiz menuBiz;
	private IEmpBiz empBiz;
	

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}
	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(menuBiz);
	}
	/**
	 * 获取目录树
	 */
	public void getMenuTree() {
		Menu rootMenu=empBiz.readEmpMenus(getLoginUser().getUuid());
		write(JSON.toJSONString(rootMenu));
	}
}
