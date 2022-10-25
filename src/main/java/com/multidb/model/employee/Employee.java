package com.multidb.model.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String ename;
	private int age;
	private String department;
	private double salary;
	private String companyname;

	public Employee() {

	}

	public Employee(int eid, String ename, int age, String department, double salary, String companyname) {
		this.eid = eid;
		this.ename = ename;
		this.age = age;
		this.department = department;
		this.salary = salary;
		this.companyname = companyname;
	}

	public Employee(String ename, int age, String department, double salary, String companyname) {
		this.ename = ename;
		this.age = age;
		this.department = department;
		this.salary = salary;
		this.companyname = companyname;
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

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %-10s %-10s", this.eid, this.ename, this.department, this.age,
				this.salary, this.companyname);
	}
}
