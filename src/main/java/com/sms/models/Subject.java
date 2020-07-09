package com.sms.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sn;
	private String code; //e.g F1LIT 
	private String title; //i.e Literature in English Form 1
	
	@OneToMany(mappedBy = "subject")
	private List<StudentSubject> studSubjs;

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<StudentSubject> getStudSubjs() {
		return studSubjs;
	}

	public void setStudSubjs(List<StudentSubject> studSubjs) {
		this.studSubjs = studSubjs;
	}
	
}
