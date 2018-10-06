package yh.erp.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import yh.erp.biz.IStoreDetailBiz;
import yh.erp.dao.IStoreDetailDao;
import yh.erp.entity.StoreAlert;
import yh.erp.entity.StoreDetail;
import yh.erp.exception.ErpException;
import yh.erp.util.MailUtil;

public class StoreDetailBiz extends BaseBiz<StoreDetail> implements IStoreDetailBiz {
	private IStoreDetailDao storeDetailDao;
	private MailUtil mailUtil;
	private String to;
	private String subject;
	private String text;

	public void setTo(String to) {
		this.to = to;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	public void setStoreDetailDao(IStoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		super.setBaseDao(storeDetailDao);
	}
	@Override
	public List<StoreAlert> getStoreAlertList() {
		return storeDetailDao.getStoreAlertList();
	}

	@Override
	public void sendStoreAlertMail() throws MessagingException {
		List<StoreAlert> storeAlertList=storeDetailDao.getStoreAlertList();
		int count=storeAlertList==null?0:storeAlertList.size();
		if(count>0) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mailUtil.sendMail(to, subject.replace("[time]", sdf.format(new Date())), 
					text.replace("[count]", String.valueOf(count)));
		}else {
			throw new ErpException("没有要预警的商品");
		}
	}
}
