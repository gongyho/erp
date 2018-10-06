package yh.erp.web;

import javax.mail.MessagingException;

import com.alibaba.fastjson.JSON;

import yh.erp.biz.IStoreDetailBiz;
import yh.erp.entity.StoreDetail;
import yh.erp.exception.ErpException;

public class StoreDetailAction extends BaseAction<StoreDetail> {
	private IStoreDetailBiz storeDetailBiz;

	public void setStoreDetailBiz(IStoreDetailBiz storeDetailBiz) {
		this.storeDetailBiz = storeDetailBiz;
		super.setBaseBiz(storeDetailBiz);
	}
	
	public void getStoreAlertList() {
		write(JSON.toJSONString(storeDetailBiz.getStoreAlertList()));
	}
	
	public void sendStoreAlertMail() {
		try {
			storeDetailBiz.sendStoreAlertMail();
			ajaxReturn(true, "邮件发送成功！");
		} catch (MessagingException e) {
			ajaxReturn(false, "构建邮件失败！");
			e.printStackTrace();
		}catch(ErpException e) {
			ajaxReturn(false, e.getMessage());
		}catch (Exception e) {
			ajaxReturn(false, "邮件发送失败");
			e.printStackTrace();
		}
	}
}
