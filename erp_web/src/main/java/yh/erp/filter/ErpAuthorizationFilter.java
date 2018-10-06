package yh.erp.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 	自定义授权过滤器
 * @author GongYuHao
 *
 */
public class ErpAuthorizationFilter extends AuthorizationFilter {

	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        
        //如果为空
        if (perms == null || perms.length == 0) {
        	return true;
        }
        //不为空
        if (perms != null && perms.length > 0) {
            for(String p:perms) {
            	//包含一个就放行
            	if(subject.isPermittedAll(p)) {
            		return true;
            	}
            }
        }
        return false;
	}

}
