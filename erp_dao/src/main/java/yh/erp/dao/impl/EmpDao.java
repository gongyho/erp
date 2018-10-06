package yh.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import yh.erp.dao.IEmpDao;
import yh.erp.entity.Emp;
import yh.erp.entity.Menu;

public class EmpDao extends BaseDao<Emp> implements IEmpDao{

	@Override
	public DetachedCriteria getDC(Emp t1, Emp t2, Object param) {
		DetachedCriteria dc=DetachedCriteria.forClass(getClazz());
		if(null!=t1) {
			if(null!=t1.getName()&&t1.getName().trim().length()>0) {
				dc.add(Restrictions.ilike("name", t1.getName(), MatchMode.ANYWHERE));
			}
			if(null!=t1.getTele()&&t1.getTele().trim().length()>0) {
				dc.add(Restrictions.ilike("tele", t1.getTele(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}

	@Override
	public Emp findByUsernameAndPwd(String userName, String pwd) {
		String hql="from Emp where userName=?0 and pwd=?1";
		List<Emp> list=(List<Emp>) getHibernateTemplate().find(hql, userName,pwd);
		
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updatePwd(int uuid, String newPed) {
		String hql="update Emp set pwd=?0 where uuid=?1";
		getHibernateTemplate().bulkUpdate(hql, newPed,uuid);
	}

	@Override
	public List<Menu> getEmpMenus(int uuid) {
		String hql="select m from Emp e join e.roles r join r.menus m where e.uuid=?0";
		return (List<Menu>) getHibernateTemplate().find(hql, uuid);
	}
	
}
