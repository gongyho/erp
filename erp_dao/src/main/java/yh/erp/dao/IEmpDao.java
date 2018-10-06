package yh.erp.dao;

import java.util.List;

import yh.erp.entity.Emp;
import yh.erp.entity.Menu;

public interface IEmpDao extends IBaseDao<Emp> {
	Emp findByUsernameAndPwd(String userName,String pwd);

	void updatePwd(int uuid,String newPwd);

	List<Menu> getEmpMenus(int uuid);
}
