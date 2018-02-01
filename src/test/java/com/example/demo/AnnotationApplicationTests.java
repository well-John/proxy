package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.MyCglibProxy;
import com.example.demo.entity.MyJDKProxy;
import com.example.demo.entity.MyTransactionManager;
import com.example.demo.service.TestCglibService;
import com.example.demo.service.TestServcieImpl;
import com.example.demo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationApplicationTests {

	@Autowired
	DataSource dataSource;
	@Autowired
	MyTransactionManager myTransactionManager;

	@Test
	public void contextLoads() throws Exception {
		Object object = new TestServcieImpl(dataSource);
		TestService testService = (TestService) new MyJDKProxy(myTransactionManager).proxyFor(object);
		testService.test2();
	}

	@Test
	public void TestCglib() throws Exception{
		TestCglibService testCglibService = (TestCglibService) new MyCglibProxy(myTransactionManager).proxyFor(TestCglibService.class);
		testCglibService.setDataSource(dataSource);
		testCglibService.setUserDao(new UserDao(dataSource));
		testCglibService.test2();
	}

}
