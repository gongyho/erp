package yh.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import yh.erp.biz.IEmpBiz;
import yh.erp.dao.IEmpDao;
import yh.erp.dao.IMenuDao;
import yh.erp.dao.IRoleDao;
import yh.erp.entity.Emp;
import yh.erp.entity.Menu;
import yh.erp.entity.Role;
import yh.erp.entity.Tree;
import yh.erp.exception.ErpException;

public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {
	private IEmpDao empDao;
	private IRoleDao roleDao;
	private IMenuDao menuDao;
	private Jedis jedis;
	
	private int hashIterations=2;//加密迭代次数
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(empDao);
	}
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	/**
	 * 
	 */
	@Override
	public Emp findByUsernameAndPwd(String userName, String pwd) {
		pwd = md5Hash(pwd, userName);
		System.out.println(pwd);
		return empDao.findByUsernameAndPwd(userName, pwd);
	}
	
	/**
	 * 	重写保存 将
	 */
	@Override
	public void saveOrUpdate(Emp t) {
		if(t.getUuid()==null || t.getUuid()==0) {//添加
			t.setPwd(md5Hash(t.getUserName(), t.getUserName()));
		}else {
			Emp oldEmp=empDao.get(t.getUuid());//保存
			t.setPwd(oldEmp.getPwd());
		}
		super.saveOrUpdate(t);
	}
	/**
	 * 	加密
	 * @param source
	 * @param salt
	 * @return
	 */
	private String md5Hash(String source,String salt) {
		if(null!=source && null!=salt) {
			Md5Hash md5=new Md5Hash(source, salt, hashIterations);
			return md5.toString();
		}
		return null;
	}

	@Override
	public void updatePwd(int uuid, String oldPwd, String newPwd) {
		Emp emp=empDao.get(uuid);
		oldPwd =md5Hash(oldPwd, emp.getUserName());
		newPwd=md5Hash(newPwd, emp.getUserName());
		if(!emp.getPwd().equals(oldPwd)) {
			throw new ErpException("密码错误！");
		}
		empDao.updatePwd(uuid, newPwd);
	}
	/**
	 *	 读取员工角色
	 */
	public List<Tree> readEmpRoles(int uuid){
		List<Tree> treeList=new ArrayList<Tree>();
		Emp emp =empDao.get(uuid);
		//员工的角色
		List<Role> roleList=emp.getRoles();
		//所有的角色
		List<Role> list=roleDao.getList(null, null, null);
		Tree tree=null;
		
		for(Role role :list) {
			tree=new Tree();
			tree.setId(role.getUuid().toString());
			tree.setText(role.getName());
			if(roleList.contains(role)) {
				tree.setChecked(true);
			}
			treeList.add(tree);
		}
		return treeList;
	}
	
	/**
	 * 	更新员工角色
	 */
	public void updateEmpRoles(int uuid,String[] checkedIds) {
		Emp emp=empDao.get(uuid);
		//清空
		emp.setRoles(new ArrayList<Role>());
		
		Role role=null;
		for(String id :checkedIds) {
			role=roleDao.get(Integer.valueOf(id));
			emp.getRoles().add(role);
		}
		try {
			jedis.del("menus_"+uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
	};
	/**
	 *	 获取用户的所有菜单
	 */
	public List<Menu> getEmpMenus(int uuid){
		String jsonStr = jedis.get("menus_"+uuid);
		List<Menu> menuList=null;
		if(jsonStr!=null) {
			System.out.println("redis 中取。。。");
			menuList=JSON.parseArray(jsonStr, Menu.class);
		}else {
			System.out.println("mysql 中取。。。");
			menuList=empDao.getEmpMenus(uuid);
			jedis.set("menus_"+uuid, JSON.toJSONString(menuList));
		}
		return menuList;
	}
	
	
	@Override
	public Menu readEmpMenus(int uuid) {
		//用户所有的菜单
		List<Menu> empMenus = getEmpMenus(uuid);
		//获取所有菜单
		Menu root=menuDao.get("0");
		//要返回的菜单
		Menu menu=cloneMenu(root);
		
		Menu m1=null;
		Menu m2=null;
		
		for(Menu _m1 : root.getMenus()) {
			m1=cloneMenu(_m1);
			for(Menu _m2 :_m1.getMenus()) {
				if(empMenus.contains(_m2)) {
					m2=cloneMenu(_m2);
					m1.getMenus().add(m2);
				}
			}
			/**
			 * 如果一级菜单有二级菜单 添加到主菜单
			 */
			if(m1.getMenus().size()>0) {
				menu.getMenus().add(m1);
			}
		}
		return menu;
	}
	/**
	 * 克隆菜单
	 * @param src
	 * @return
	 */
	private Menu cloneMenu(Menu src) {
		Menu menu=new Menu();
		menu.setIcon(src.getIcon());
		menu.setMenuid(src.getMenuid());
		menu.setMenuname(src.getMenuname());
		menu.setUrl(src.getUrl());
		menu.setMenus(new ArrayList<Menu>());
		return menu;
	}

}
