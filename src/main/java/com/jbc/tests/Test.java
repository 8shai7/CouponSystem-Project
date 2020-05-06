package com.jbc.tests;
import java.sql.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.jbc.dao.CompanyRepositoryImpl;
import com.jbc.dao.CouponRepositoryImpl;
import com.jbc.dao.CustomerRepositoryImpl;
import com.jbc.facade.AdminFacade;
import com.jbc.facade.ClientFacade;
import com.jbc.facade.CompanyFacade;
import com.jbc.facade.CustomerFacade;
import com.jbc.job.Job;
import com.jbc.model.Category;
import com.jbc.model.ClientType;
import com.jbc.model.Company;
import com.jbc.model.Coupon;
import com.jbc.model.Customer;
import com.jbc.system.LoginManager;
import com.jbc.utils.Utils;
@Component
public class Test implements ApplicationContextAware{

	private ApplicationContext context;
	@Autowired
	@Qualifier("CouponRepositoryImpl")
	CouponRepositoryImpl CouponDao;
	@Autowired
	@Qualifier("CustomerRepositoryImpl")
	CustomerRepositoryImpl CustomerDao;
	@Autowired
	@Qualifier("CompanyRepositoryImpl")
	CompanyRepositoryImpl CompanyDao;
	//method to test everything 
	public void testAll() {
		//Initialising Program
		CompanyDao.deleteAll();
		CustomerDao.deleteAll();
		CouponDao.deleteAll();
		Customer cust= new Customer();
		cust.setFirstName("Shai");
		cust.setLastName("Tal");
		cust.setEmail("test@test.com");
		cust.setPassword("test");
		Company comp = new Company();
		comp.setEmail("test@test.com");
		comp.setName("testComp");
		comp.setPassword("test");
		System.err.println(comp.getId());
		CompanyDao.addCompany(comp);
		CustomerDao.addCustomer(cust);
		//a
		Job j = context.getBean(Job.class);
		//j.start();
		//b
		LoginManager lm = context.getBean(LoginManager.class);
		lm.setApplicationContext(context);
		//adminTest(context.getBean(AdminFacade.class));
		ClientFacade admin = lm.login("admin@admin.com", "admin", ClientType.Administrator);
		if(!(admin == null))
			adminTest((AdminFacade)admin);
		ClientFacade company = lm.login("test@test.com", "test", ClientType.company);
		if(!(company == null)) {
			CompanyFacade comf = (CompanyFacade)company;
			comf.setCompanyId(comp.getId());
			companyTest(comf);
		}
		ClientFacade customer = lm.login("test@test.com", "test", ClientType.customer);
		if(!(customer == null)) {
			CustomerFacade cusf = (CustomerFacade)customer;
			cusf.setCustomerId(cust.getId());
			customerTest(cusf);
		}
		//j.quit();
	}
	//method for testing the admin build 
	private void adminTest(AdminFacade admin) {
		System.out.println(admin.findCompany(CompanyDao.findAllCompnaies().get(CompanyDao.findAllCompnaies().size()-1).getId()));
		System.out.println("a-----");
		for(Company c : admin.findAllCompanies()) {
			System.out.println(c);
		}
		System.out.println("b-----");
		Company c = admin.findCompany(CompanyDao.findAllCompnaies().get(CompanyDao.findAllCompnaies().size()-1).getId());
		c.setName("CompanyTest");
		admin.updateCompany(c);
		System.out.println(admin.findCompany(CompanyDao.findAllCompnaies().get(CompanyDao.findAllCompnaies().size()-1).getId()));
		System.out.println("c-----");
		admin.deleteCompany(c);
		for(Company co : admin.findAllCompanies()) {
			System.out.println(co);
		}
		System.out.println("d-----");
		Customer cust = new Customer();
		System.out.println(admin.findCustomer(CustomerDao.findAllCustomers().get(CustomerDao.findAllCustomers().size()-1).getId()));
		for(Customer cu : admin.findAllCustomers()) {
			System.out.println(cu);
		}
		System.out.println("e-----");
		Customer cu = admin.findCustomer(CustomerDao.findAllCustomers().get(CustomerDao.findAllCustomers().size()-1).getId()).get();
		cu.setFirstName("Test");
		admin.updateCustomer(cu);
		System.out.println(admin.findCustomer(CustomerDao.findAllCustomers().get(CustomerDao.findAllCustomers().size()-1).getId()));
		admin.deleteCustomer(cu);
		System.out.println("f-----");
		for(Customer cus : admin.findAllCustomers()) {
			System.out.println(cus);
		}
		System.out.println("-----<");
		cust.setFirstName("Shai");
		cust.setLastName("Tal");
		cust.setEmail("test@test.com");
		cust.setPassword("test");
		admin.addCustomer(cust);
		Company comp = new Company();
		comp.setEmail("test@test.com");
		comp.setName("testComp");
		comp.setPassword("test");
		admin.addCompany(comp);
	}
	//method to to test the company build 
	private void companyTest(CompanyFacade company) {
		System.out.println(company.getCompanyDetails());
		System.out.println("----->");
		Coupon coup = new Coupon();
		coup.setCompanyId(company.getCompanyDetails().getId());
		coup.setCategory(Category.Tools);
		coup.setDescription("Gives You Shivers every time kirito slice an enemy");
		coup.setTitle("100% SAO");
		coup.setStartDate(new Date(2010,10,5));
		coup.setEndDate(new Date(2022, 10, 31));
		coup.setAmount(10000);
		coup.setPrice(250f);
		coup.setImage("KiritoImg");
		company.addCoupon(coup);
		System.out.println(company.getCompanyCoupons().toString());
		System.out.println("a-----");
		coup.setPrice(350f);
		company.updateCoupon(coup);
		System.out.println(company.getCompanyCoupons().toString());
		System.out.println("b-----");
		System.out.println(company.getCompanyCoupons().toString());
		System.out.println("c-----");
		System.out.println(company.getCompanyCoupons(Category.Tools).toString());
		System.out.println("d-----");
		System.out.println(company.getCompanyCoupons(300f).toString());
		System.out.println("e-----");
		System.out.println(company.getCompanyCoupons(400f).toString());
		System.out.println("f-----");
		company.deleteCoupon(CouponDao.FindCouponById(CouponDao.findAllCoupons().get(CouponDao.findAllCoupons().size()-1).getId()).get().getId());
		System.out.println(company.getCompanyCoupons().toString());
		company.addCoupon(coup);
		System.out.println("-----<");
	}
	//method to test the customer build 
	private void customerTest(CustomerFacade customer) {
		System.out.println("----->");
		customer.purchaseCoupon(CouponDao.FindCouponById(CouponDao.findAllCoupons().get(CouponDao.findAllCoupons().size()-1).getId()).get());
		System.out.println(customer.getCustomerCoupons().toString());
		System.out.println("a-----");
		System.out.println(customer.getCustomerCoupons(Category.Tools));
		System.out.println("b-----");
		System.out.println(customer.getCustomerCoupons(300f));
		System.out.println("c-----");
		System.out.println(customer.getCustomerCoupons(400f));
		System.out.println("d-----");
		if(customer.getCustomerDetails()!=null)
			System.out.println(customer.getCustomerDetails().toString());
		System.out.println("-----<");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
