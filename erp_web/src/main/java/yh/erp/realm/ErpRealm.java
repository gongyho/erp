package yh.erp.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import yh.erp.biz.IEmpBiz;
import yh.erp.entity.Emp;
import yh.erp.entity.Menu;

public class ErpRealm extends AuthorizingRealm {
	private IEmpBiz empBiz;
	
	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}
	/**
	 * 	授权操作
	 * @param principals
	 * @return
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权。。。");
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Emp emp=(Emp) principals.getPrimaryPrincipal();
		List<Menu> menus=empBiz.getEmpMenus(emp.getUuid());
		for (Menu menu : menus) {
			info.addStringPermission(menu.getMenuname());
		}
		return info;
	}
	/**
	 * 	认证操作
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upt=(UsernamePasswordToken) token;
		String pwd=new String(upt.getPassword());
		Emp emp = empBiz.findByUsernameAndPwd(upt.getUsername(),pwd);
		if(null!=emp) {
			//Object principal :主体 可以获得登陆的用用户, Object credentials：证书 , String realmName realm名称
			SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(emp,pwd,getName());
			return info;
		}
		return null;
	}

}
