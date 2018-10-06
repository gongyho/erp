package yh.erp.biz;

import java.util.List;

import yh.erp.entity.Role;
import yh.erp.entity.Tree;

public interface IRoleBiz extends IBaseBiz<Role> {
	List<Tree> readRoleMenus(int uuid);
	void updateRoleMenus(int uuid,String[] checkedIds);
}
