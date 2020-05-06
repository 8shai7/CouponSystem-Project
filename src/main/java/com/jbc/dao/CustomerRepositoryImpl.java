package com.jbc.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.jbc.model.CouponPurchase;
import com.jbc.model.Customer;
@Component
@Transactional
@Qualifier("CustomerRepositoryImpl")
public class CustomerRepositoryImpl {
	
	@Autowired
	@Qualifier("CustomerRepository")
	CustomerRepository repo;
	@Autowired
	@Qualifier("CouponPurchaseRepository")
	CouponPurchaseRepository cprepo;

	public void delete(Customer c) {
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
	public Optional<Customer> FindCustomerById(int id) {
		return repo.findById(id);
	}
	public List<Customer> findAllCustomers(){
		return repo.findAll();
	}
	public void addCustomer(Customer Customer) {
		repo.save(Customer);
	}
	public void updateCustomer(Customer Customer) {
		repo.save(Customer);
	}
	public boolean existByEmailAndPassword(String email,String password) {
		return repo.findByEmailAndPassword(email, password) != null;
	}
	public void deleteCouponPurchase(CouponPurchase cpi) {
		cprepo.delete(cpi);
	}
	public void addCouponPurchase(CouponPurchase cp) {
		cprepo.save(cp);
	}
	public List<CouponPurchase> findAllCouponPurchase(){
		return cprepo.findAll();
	}
}
