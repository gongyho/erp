package yh.erp.biz.impl;

import yh.erp.biz.IDepBiz;
import yh.erp.dao.IDepDao;
import yh.erp.entity.Dep;

public class DepBiz extends BaseBiz<Dep> implements IDepBiz{
	IDepDao depDao;

	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		//给父类设置dao
		super.setBaseDao(depDao);
	}
	
	
}
