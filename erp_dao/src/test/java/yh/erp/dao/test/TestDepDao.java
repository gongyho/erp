package yh.erp.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yh.erp.dao.impl.DepDao;
import yh.erp.entity.Dep;

public class TestDepDao {
	@Test
	public void testGetList() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext_dao.xml");
		DepDao depDao=(DepDao) ac.getBean("depDao");
		List<Dep> list= depDao.getList();
		for (Dep dep : list) {
			System.out.println(dep);
		}
	}

}
