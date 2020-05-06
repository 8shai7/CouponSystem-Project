package com.jbc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.jbc.utils.Utils;

@Entity
@Scope("prototype")
@Qualifier("Coupon")
//coupon class 
public class Coupon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private float price;
	private String image;
	
	//getters and setters 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id",nullable=false)
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	@Column(name="companyId",nullable = false)
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	@Enumerated
	@Column(name="Category",nullable=false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Column(name="Title",nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="Description",nullable=true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="StartDate",nullable=false)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = Utils.addDate(startDate);
	}
	@Temporal(TemporalType.DATE)
	@Column(name="EndDate",nullable=false)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = Utils.addDate(endDate);
	}
	@Column(name="Amount",nullable=false)
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Column(name="Price",nullable=false)
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name="Image",nullable=true)
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	//coupon hashCode 
	@Override
	public int hashCode() {
		int param = 17;
		int hash = 0;
		hash += this.id*param;
		hash += (int) (this.amount+this.price*param);
		hash += this.companyId*param;
		hash += this.category.hashCode()*param;
		hash += (this.description.hashCode()+this.endDate.hashCode()+this.startDate.hashCode())*param;
		hash += this.image.hashCode()-param;
		return hash;
	}
	//the usage of equals to determine the equality between the coupons
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Coupon)) {
			return false;
		}
		Coupon tmp = (Coupon)obj;
		return tmp.hashCode() == this.hashCode();
	}
	//coupon toString 
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}


}
