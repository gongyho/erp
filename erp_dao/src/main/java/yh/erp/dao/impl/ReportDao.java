package yh.erp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import yh.erp.dao.IReportDao;

public class ReportDao extends HibernateDaoSupport implements IReportDao {

	@Override
	public List orderReport(Date startDate,Date endDate) {
		String hql="SELECT new Map(gt.name as name,SUM(ol.money) as y) FROM Orders o,OrderDetail ol,Goods g, GoodsType gt " +
				"WHERE g.goodsTypeUuid=gt.uuid AND ol.goodsUuid=g.uuid AND ol.orders=o AND o.type=2 ";
		int i=0;
		List<Date> listParam=new ArrayList<Date>();
		if(null!=startDate) {
			hql+=" AND o.createTime>?"+i+" ";
			i++;
			listParam.add(startDate);
		}
		if(null!=endDate) {
			hql+=" AND o.createTime<?"+i+" ";
			i++;
			listParam.add(endDate);
		}
		hql+=" GROUP BY gt.uuid";
		return getHibernateTemplate().find(hql,listParam.toArray(new Date[] {}));
	}
	
	public List trendReport(String year,String type) {
		String hql="SELECT new Map(DATE_FORMAT(o.createTime,'%m') as name,SUM(ol.money) as y)FROM Orders o ,OrderDetail ol " + 
				"WHERE ol.orders=o AND o.type=?0 AND DATE_FORMAT(o.createTime,'%Y')=?1 GROUP BY DATE_FORMAT(o.createTime,'%m')";
		return getHibernateTemplate().find(hql, type,year);
	}

	@Override
	public List getYearList(String type) {
		String hql="SELECT new Map(DATE_FORMAT(o.createTime,'%Y') as year)FROM Orders o " + 
				"WHERE   o.type=?0 " + 
				"GROUP BY DATE_FORMAT(o.createTime,'%Y')";
		return getHibernateTemplate().find(hql, type);
	}
	
}
