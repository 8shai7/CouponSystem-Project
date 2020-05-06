package com.jbc.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;

//company class
@Entity
@Scope("prototype")
public class Company {
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Integer> couponIds;
	//getters and setters for the company build
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id",nullable=false)
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	@Column(name="Name",nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@many
	public List<Integer> getCouponIds() {
		return couponIds;
	}
	protected void setCouponIds(List<Integer> coupons) {
		this.couponIds = coupons;
	}
	//company toString 
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ couponIds + "]";
	}


}
