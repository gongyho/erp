package yh.erp.biz;

import java.util.List;

import yh.erp.entity.Emp;
import yh.erp.entity.Menu;
import yh.erp.entity.Tree;

public interface IEmpBiz extends IBaseBiz<Emp> {
	Emp findByUsernameAndPwd(String userName,String pwd);

	void updatePwd(int uuid, String oldPwd, String newPwd);

	List<Tree> readEmpRoles(int uuid);
	void updateEmpRoles(int uuid,String[] checkedIds);
	
	List<Menu> getEmpMenus(int uuid);
	
	Menu readEmpMenus(int uuid);
}
