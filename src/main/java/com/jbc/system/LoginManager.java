package com.jbc.system;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jbc.facade.*;
import com.jbc.model.ClientType;
	//login manager class
@Component
@Scope("singleton")
public class LoginManager implements ApplicationContextAware{
	private ApplicationContext context;
	//constructor of a login manager 
	//client facade login - by the client type
	public ClientFacade login(String email,String password,ClientType type) {
		ClientFacade cf = null;
		switch(type) {
		case Administrator:
			AdminFacade cft = context.getBean(AdminFacade.class);
			if(cft.login(email, password))
				cf = context.getBean(AdminFacade.class);
			break;
		case company:
			CompanyFacade cft1 = context.getBean(CompanyFacade.class);
			if(cft1.login(email, password)) {
				cf = context.getBean(CompanyFacade.class);
				cf.setEmail(email);
				cf.setPassword(password);
			}
			break;
		case customer:
			CustomerFacade cft2 = context.getBean(CustomerFacade.class);
			if(cft2.login(email, password)) {
				cf = context.getBean(CustomerFacade.class);
				cf.setEmail(email);
				cf.setPassword(password);
			}
			break;
		default:
			break;
		}
		return cf;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
