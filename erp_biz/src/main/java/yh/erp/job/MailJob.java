package yh.erp.job;

import yh.erp.biz.IStoreDetailBiz;
import yh.erp.entity.StoreAlert;

import javax.mail.MessagingException;
import java.util.List;

public class MailJob {
    private IStoreDetailBiz storeDetailBiz;
    public void setStoreDetailBiz(IStoreDetailBiz storeDetailBiz) {
        this.storeDetailBiz = storeDetailBiz;
    }

    public void sendStoreAlertMail(){
        List<StoreAlert> list = storeDetailBiz.getStoreAlertList();
        if(list.size()>0){
            try {
                storeDetailBiz.sendStoreAlertMail();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
