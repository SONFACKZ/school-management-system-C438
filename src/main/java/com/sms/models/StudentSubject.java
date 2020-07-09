package com.sms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudentSubject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sn;
	private Double mark;
	
	@ManyToOne
	@JoinColumn(name = "student_stud_id")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "subject_sn")
	private Subject subject;

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
