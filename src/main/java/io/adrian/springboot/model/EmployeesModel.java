package io.adrian.springboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EmployeesModel {
	private String name;
	private String joindate;
	private int age;
	private String company;
	private String email;
	private String phone;
	private int salary;
	@JsonInclude(Include.NON_NULL)
	private AddressModel address;
	
	public EmployeesModel() {
		
	}
	
	
	public EmployeesModel(String name, String joindate, int age, String company, String email, String phone, int salary,
			AddressModel address) {
		super();
		this.name = name;
		this.joindate = joindate;
		this.age = age;
		this.company = company;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.address = address;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getjoindate() {
		return joindate;
	}
	public void setjoindate(String joindate) {
		this.joindate = joindate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
