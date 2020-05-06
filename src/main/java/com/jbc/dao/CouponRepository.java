package com.jbc.dao;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.jbc.model.Coupon;
@Component
@Transactional
@Qualifier("CouponRepository")
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	@Transactional
	@Modifying
	@Query("delete from Coupon")
	public void deleteAll();
	
}
