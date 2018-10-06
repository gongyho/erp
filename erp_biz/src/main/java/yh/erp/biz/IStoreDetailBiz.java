package yh.erp.biz;

import java.util.List;

import javax.mail.MessagingException;

import yh.erp.entity.StoreAlert;
import yh.erp.entity.StoreDetail;

public interface IStoreDetailBiz extends IBaseBiz<StoreDetail> {
	List<StoreAlert> getStoreAlertList();
	void sendStoreAlertMail()throws MessagingException;
}
