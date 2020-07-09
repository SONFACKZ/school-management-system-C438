package com.sms.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studID;
	private String fName;
	private String lName;
	private int age;
	private short status;
	private LocalDate regDate;
	private String address;
	private String city;
	private String pin;
	private String state;
	private String phone;
	private LocalDate DOB;
	
	@Column(length = 6)
	private String regNumber;

	@ManyToOne
	@JoinColumn(name = "sclass_sn")
	private SClass sclass;
	
	@OneToOne
	private Fees fee;
	
	@OneToMany(mappedBy = "student")
	private List<StudentSubject> studSubs;

	
	public long getStudID() {
		return studID;
	}

	public void setStudID(long studID) {
		this.studID = studID;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public SClass getSclass() {
		return sclass;
	}

	public void setSclass(SClass sclass) {
		this.sclass = sclass;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public Fees getFee() {
		return fee;
	}

	public void setFee(Fees fee) {
		this.fee = fee;
	}

	public List<StudentSubject> getStudSubs() {
		return studSubs;
	}

	public void setStudSubs(List<StudentSubject> studSubs) {
		this.studSubs = studSubs;
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

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	
}
