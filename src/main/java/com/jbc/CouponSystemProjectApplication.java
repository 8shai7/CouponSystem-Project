package com.jbc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.jbc.dao.CompanyRepositoryImpl;
import com.jbc.facade.AdminFacade;
import com.jbc.tests.Test;

@SpringBootApplication
public class CouponSystemProjectApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CouponSystemProjectApplication.class, args);
		Test test = context.getBean(Test.class);
		test.setApplicationContext(context);
		test.testAll();
        ((ConfigurableApplicationContext)context).close();

	}

}
