package com.jbc.dao;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.jbc.model.Company;
@Component
@Transactional
@Qualifier("CompanyRepository")
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	@Transactional
	@Modifying
	@Query("delete from Company")
	public void deleteAll();
	@Query("SELECT c FROM Company as c where c.id=:id")
	public Company findComapnyById(Integer id);
	@Query("SELECT c FROM Company as c where c.email=:email and c.password=:password")
	public Company findByEmailAndPassword(String email,String password);
	@Transactional
	@Modifying
	@Query("update Company as c set c.couponIds=:couponList where c.id=:companyId")
	public void updateCouponIds(ArrayList<Integer> couponList,int companyId);

}
