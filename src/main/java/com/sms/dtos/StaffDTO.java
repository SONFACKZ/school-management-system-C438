package com.sms.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StaffDTO {
	private String empNumber;
	private String name;
	private String address;
	private String city;
	private String pin;
	private String state;
	private String phone;
	private String mobile;
	private String email;
	private String sex;
	private String m_status;
	private String dept;
	private String nature_of_job;
	private BigDecimal basic_pay;
	
	private LocalDate DOJ;
	
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getM_status() {
		return m_status;
	}
	public void setM_status(String m_status) {
		this.m_status = m_status;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getNature_of_job() {
		return nature_of_job;
	}
	public void setNature_of_job(String nature_of_job) {
		this.nature_of_job = nature_of_job;
	}
	public BigDecimal getBasic_pay() {
		return basic_pay;
	}
	public void setBasic_pay(BigDecimal basic_pay) {
		this.basic_pay = basic_pay;
	}
	public LocalDate getDOJ() {
		return DOJ;
	}
	public void setDOJ(LocalDate dOJ) {
		DOJ = dOJ;
	}
}
