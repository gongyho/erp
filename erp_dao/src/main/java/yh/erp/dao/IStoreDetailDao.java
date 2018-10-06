package yh.erp.dao;

import java.util.List;

import yh.erp.entity.StoreAlert;
import yh.erp.entity.StoreDetail;

public interface IStoreDetailDao extends IBaseDao<StoreDetail> {
	List<StoreAlert> getStoreAlertList();
}
