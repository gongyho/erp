package yh.erp.biz;

import com.redsum.bos.ws.Waybilldetail;
import yh.erp.entity.Orders;

import java.io.OutputStream;
import java.util.List;

public interface IOrdersBiz extends IBaseBiz<Orders> {
	void doCheck(int uuid,int empUuid);
	void doStart(int uuid,int empUuid);
	void export(OutputStream out,int uuid);
	List<Waybilldetail> waybillList(int sn);
}
