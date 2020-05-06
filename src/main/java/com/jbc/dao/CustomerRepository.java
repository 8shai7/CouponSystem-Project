package com.jbc.dao;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.jbc.model.Customer;
@Component
@Transactional
@Qualifier("CustomerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Transactional
	@Modifying
	@Query("delete from Customer")
	public void deleteAll();
	
	public Customer findByEmailAndPassword(String email,String password);
}
