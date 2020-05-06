package com.jbc.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jbc.model.Company;
import com.jbc.model.Coupon;
import com.jbc.model.CouponPurchase;
import com.jbc.model.Customer;
@Service
public class AdminFacade extends ClientFacade{
	
	@Override
	public boolean login(String email, String password) {
		return ((email == "admin@admin.com" && password == "admin") ? true : false);
	}
	public void addCompany(Company company) {
		boolean check = true;
		for(Company c : CompanyDao.findAllCompnaies()) {
			if(c==null) {
				check = false;
				break;
			}
			if(c.getName() == company.getName() || c.getEmail() == company.getEmail()) {
				check = false;
				break;
			}
		}
		if(check)
			CompanyDao.addCompany(company);
	}
	public void updateCompany(Company company) {
		CompanyDao.updateCompany(company);
	}
	public void deleteCompany(Company company) {
		CompanyDao.delete(company);
	}
	public void addCustomer(Customer customer) {
		boolean check = true;
		for(Customer c : CustomerDao.findAllCustomers()) {
			if(c.getEmail() == customer.getEmail()) {
				check = false;
				break;
			}
		}
		if(check)
			CustomerDao.addCustomer(customer);
	}
	public void updateCustomer(Customer customer) {
		CustomerDao.updateCustomer(customer);
	}
	public void deleteCustomer(Customer customer) {
		for(Coupon c : CouponDao.findAllCoupons()) {
			CouponPurchase cpi = new CouponPurchase();
			cpi.setCouponId(c.getId());
			cpi.setCustomerId(customer.getId());
			CustomerDao.deleteCouponPurchase(cpi);
		}
		CustomerDao.delete(customer);
	}
	public Company findCompany(Company company) {
		return CompanyDao.FindCompanyById(company.getId());
	}
	public Company findCompany(int id) {
		return CompanyDao.FindCompanyById(id);
	}
	public Optional<Customer> findCustomer(Customer customer) {
		return CustomerDao.FindCustomerById(customer.getId());
	}
	public Optional<Customer> findCustomer(int id) {
		return CustomerDao.FindCustomerById(id);
	}
	public List<Company> findAllCompanies(){
		return CompanyDao.findAllCompnaies();
	}
	public List<Customer> findAllCustomers(){
		return CustomerDao.findAllCustomers();
	}
	
	
}
