package com.sms.dtos;

import java.time.LocalDate;

public class StudentFeePaymentDTO {

	private String registrationNumber;
	private LocalDate payementDate;
	private String classCode;
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public LocalDate getPayementDate() {
		return payementDate;
	}
	public void setPayementDate(LocalDate payementDate) {
		this.payementDate = payementDate;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
}
