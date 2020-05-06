package com.jbc.dao;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.jbc.model.CouponPurchase;
@Component
@Transactional
@Qualifier("CouponPurchaseRepository")
public interface CouponPurchaseRepository extends JpaRepository<CouponPurchase, Integer> {
	
}
