package yh.erp.web;

import yh.erp.biz.IDepBiz;
import yh.erp.entity.Dep;

public class DepAction extends BaseAction<Dep>{
	private IDepBiz depBiz;
	
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		//给baseBiz设置Biz
		super.setBaseBiz(depBiz);
	}
	
}
