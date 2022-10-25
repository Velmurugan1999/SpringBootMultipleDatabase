package com.multidb.model.address;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	@Id
	private int doorno;
	private String street;
	private String city;
	private int pincode;
	private int eid;
	public Address() {
		
	}
	public Address(int doorno, String street, String city, int pincode, int eid) {
		this.doorno = doorno;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.eid = eid;
	}
	public int getDoorno() {
		return doorno;
	}
	public void setDoorno(int doorno) {
		this.doorno = doorno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %-10s %-10s", this.getDoorno(), this.getStreet(), this.getCity(), this.getPincode(),
				this.getEid());
	}
}
