package com.my.dto;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
	private int c_no;
	private int p_no;
	private String user_no;
	private Customer c;
	private String c_content;
	private int c_class;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul" )
	private Date c_write_date;
	private Date c_update_date;
	private Date c_delete_date;

	public Comment() {
	
	}
	//Constructor with all data
	public Comment(int c_no, int p_no, Customer c, String c_content, int c_class, Date c_write_date,
			Date c_update_date, Date c_delete_date) {
		super();
		this.c_no = c_no;
		this.p_no = p_no;
		this.user_no = user_no;
		this.c = c;
		this.c_content = c_content;
		this.c_class = c_class;
		this.c_write_date = c_write_date;
		this.c_update_date = c_update_date;
		this.c_delete_date = c_delete_date;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public  String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public int getC_class() {
		return c_class;
	}
	public void setC_class(int c_class) {
		this.c_class = c_class;
	}
	public Date getC_write_date() {
		return c_write_date;
	}
	public void setC_write_date(Date c_write_date) {
		this.c_write_date = c_write_date;
	}
	public Date getC_update_date() {
		return c_update_date;
	}
	public void setC_update_date(Date c_update_date) {
		this.c_update_date = c_update_date;
	}
	public Date getC_delete_date() {
		return c_delete_date;
	}
	public void setC_delete_date(Date c_delete_date) {
		this.c_delete_date = c_delete_date;
	}
	
	
}