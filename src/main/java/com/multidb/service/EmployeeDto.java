package com.multidb.service;

import com.multidb.model.address.Address;

public class EmployeeDto {
	private int eid;
	private String ename;
	private int age;
	private String department;
	private double salary;
	private String companyname;
	private Address address;
	
	public EmployeeDto() {
		
	}
	public EmployeeDto(int eid, String ename, int age, String department, double salary, String companyname,
			Address address) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.age = age;
		this.department = department;
		this.salary = salary;
		this.companyname = companyname;
		this.address = address;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
