package com.sms.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StudentDTO implements Comparable<StudentDTO> {
	
	private String regNo;
	private int rollNo;
	private String fName;
	private String lName;
	private String address;
	private String city;
	private String pin;
	private String state;
	private String phone;
	
	private LocalDate dob;
	private LocalDate dor; //admission date


	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDate getDor() {
		return dor;
	}

	public void setDor(LocalDate dor) {
		this.dor = dor;
	}

	
	@Override
	public int compareTo(StudentDTO arg0) {
		if(this.dor.isBefore(arg0.getDor()))
			return -1;
		else if(this.dor.isAfter(arg0.getDor()))
			return 1;
		else
			return 0;
	}
}
