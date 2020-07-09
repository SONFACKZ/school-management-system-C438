package com.sms.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sn;
	private String code;
	private String name;
	
	@ManyToOne
	private SchoolYear year;
	
	@OneToMany(mappedBy = "sclass")
	private List<Student> students;

	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SchoolYear getsYear() {
		return year;
	}

	public void setsYear(SchoolYear sYear) {
		this.year = sYear;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
