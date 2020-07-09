package com.sms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SchoolYear {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sn;
	private String syFirst;
	private String sySecond;
	
	@Override
	public String toString() {
		return String.format("%s-%s", syFirst, sySecond);
	}
	
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getSyFirst() {
		return syFirst;
	}
	public void setSyFirst(String syFirst) {
		this.syFirst = syFirst;
	}
	public String getSySecond() {
		return sySecond;
	}
	public void setSySecond(String sySecond) {
		this.sySecond = sySecond;
	}
	
}
