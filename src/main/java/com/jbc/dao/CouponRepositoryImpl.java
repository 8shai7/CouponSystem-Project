package com.jbc.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jbc.model.Coupon;
@Component
@Transactional
@Qualifier("CouponRepositoryImpl")
public class CouponRepositoryImpl {
	
	@Autowired
	@Qualifier("CouponRepository")
	CouponRepository repo;

	public void delete(Coupon c) {
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
	public Optional<Coupon> FindCouponById(int id) {
		return repo.findById(id);
	}
	public List<Coupon> findAllCoupons(){
		return repo.findAll();
	}
	public void addCoupon(Coupon Coupon) {
		repo.save(Coupon);
	}
	public void updateCoupon(Coupon Coupon) {
		repo.save(Coupon);
	}
}
