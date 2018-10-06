package yh.erp.biz.impl;

import yh.erp.biz.IMenuBiz;
import yh.erp.dao.IMenuDao;
import yh.erp.entity.Menu;

public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {
	private IMenuDao menuDao;

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(menuDao);
	}
}
