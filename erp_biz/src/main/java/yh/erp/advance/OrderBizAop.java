package yh.erp.advance;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import yh.erp.entity.Orders;
import yh.erp.exception.ErpException;

@Service
@Aspect
public class OrderBizAop {
	/**
	 * orderBiz 保存订单验证权限
	 * @param t
	 */
	@Before("pointcut()")
	public void saveOrUpdateBefore(JoinPoint  joinPoint) {
		Orders t= (Orders) joinPoint.getArgs()[0];
		Subject subject=SecurityUtils.getSubject();
		System.out.println("aop 验证权限。。。");
		if(Orders.TYPE_IN.equals(t.getType())) {
			if(!subject.isPermitted("我的采购订单")) {
				throw new ErpException("权限不足");
			}
		}else if(Orders.TYPE_OUT.equals(t.getType())) {
			if(!subject.isPermitted("我的销售订单")) {
				throw new ErpException("权限不足");
			}
		}else {
			throw new ErpException("非法参数");
		}
	}
	@Pointcut("execution (* yh.erp.biz.impl.OrdersBiz.saveOrUpdate(..))")
	public void pointcut() {};
}
