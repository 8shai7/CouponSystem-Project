package com.jbc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
@Entity
@Scope("prototype")
//customer class 
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Coupon> coupons;
	public Customer() {
		
	}
	//getters and setters 
	@Column(name="Id",nullable=false)
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	@Column(name="FirstName",nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="LastName",nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="Email",nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="Password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="Coupons",nullable=false)
	public List<Coupon> getCoupons() {
		return coupons;
	}
	protected void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	//customer toString
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons + "]";
	}


}
