package yh.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import yh.erp.biz.IRoleBiz;
import yh.erp.dao.IMenuDao;
import yh.erp.dao.IRoleDao;
import yh.erp.entity.Emp;
import yh.erp.entity.Menu;
import yh.erp.entity.Role;
import yh.erp.entity.Tree;

public class RoleBiz extends BaseBiz<Role> implements IRoleBiz {
	private IRoleDao roleDao;
	private IMenuDao menuDao;
	private Jedis jedis;

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}
	/**
	 * 读取角色菜单
	 */
	public List<Tree> readRoleMenus(int uuid){
		List<Tree> treeList =new ArrayList<Tree>();
		Menu root=menuDao.get("0");
		
		Role role=roleDao.get(uuid);
		List<Menu> menuList=role.getMenus();
		
		Tree t1=null;
		Tree t2=null;
		
		
		for (Menu m : root.getMenus()) {
			t1=new Tree();
			t1.setId(m.getMenuid());
			t1.setText(m.getMenuname());
			t1.setChildren(new ArrayList<Tree>());
			for(Menu m1:m.getMenus()) {
				t2=new Tree();
				t2.setId(m1.getMenuid());
				t2.setText(m1.getMenuname());
				if(menuList.contains(m1)) {
					t2.setChecked(true);
				}
				t1.getChildren().add(t2);
			}
			treeList.add(t1);
		}
		return treeList;
	}
	/**
	 * 更新角色权限
	 */
	public void updateRoleMenus(int uuid,String[] checkedIds) {
		Role role=roleDao.get(uuid);
		role.setMenus(new ArrayList<Menu>());
		Menu menu=null;
		for(String id:checkedIds) {
			menu=menuDao.get(id);
			role.getMenus().add(menu);
		}
		
		List<Emp> emps = role.getEmps();
		try {
			for (Emp emp : emps) {
				jedis.del("menus_"+emp.getUuid());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	};
}
