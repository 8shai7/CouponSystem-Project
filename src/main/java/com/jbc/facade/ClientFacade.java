package com.jbc.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jbc.dao.CompanyRepositoryImpl;
import com.jbc.dao.CouponRepositoryImpl;
import com.jbc.dao.CustomerRepositoryImpl;
@Service
public abstract class ClientFacade {
	@Autowired
	@Qualifier("CouponRepositoryImpl")
	CouponRepositoryImpl CouponDao;
	@Autowired
	@Qualifier("CustomerRepositoryImpl")
	CustomerRepositoryImpl CustomerDao;
	@Autowired
	@Qualifier("CompanyRepositoryImpl")
	CompanyRepositoryImpl CompanyDao;
	
	String email;
	String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public abstract boolean login(String email, String password);

}
