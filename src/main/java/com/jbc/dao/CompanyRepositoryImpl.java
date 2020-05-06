package com.jbc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jbc.model.Company;
@Component
@Transactional
@Qualifier("CompanyRepositoryImpl")
public class CompanyRepositoryImpl {
	
	@Autowired
	@Qualifier("CompanyRepository")
	CompanyRepository repo;

	public void delete(Company c) {
		repo.delete(c);
	}
	public void deleteAll() {
		repo.deleteAll();
	}
	public void deleteById(int id) {
		repo.deleteById(id);;
	}
	public boolean existById(int id) {
		return repo.existsById(id);
	}
	public Company FindCompanyById(int id) {
		return repo.findComapnyById(id);
	}
	public List<Company> findAllCompnaies(){
		return repo.findAll();
	}
	public void addCompany(Company company) {
		repo.save(company);
	}
	public void updateCompany(Company company) {
		repo.save(company);
	}
	public boolean existByEmailAndPassword(String email,String password) {
		return repo.findByEmailAndPassword(email, password) != null;
	}
	public void addCompanyCoupon(ArrayList<Integer> couponList,int companyId) {
		System.err.println(couponList);
		repo.updateCouponIds(couponList,companyId);
	}
}
