package com.servlet_test2.vo;

import java.sql.Date;

public class MemberVO {
	private int idx;
	private String id;
	private String pw;
	private String name;
	private Date dates;
	private String state;
	private String value;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx2) {
		this.idx = idx2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
