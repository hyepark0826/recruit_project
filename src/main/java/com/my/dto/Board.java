package com.my.dto;

import java.util.Date;
import java.util.Objects;

public class Board {
	private int user_no;
	private String user_id;
	private int b_no;
	private String b_title;
	private String b_content;
	private Date b_writeday;
	private Date b_updateday;
	private Date b_deleteday;

	public Board() {
	}

	public Board(int user_no, String user_id, int b_num, String b_title ,String context, Date writeDate) {
		this.user_no = user_no;
		this.user_id = user_id;
		this.b_no = b_num;
		this.b_title=b_title;
		this.b_content = context;
		this.b_writeday = writeDate;
	}
	public Board(int userNo, String userId, int boardNo, String context, Date writeDate, Date updateDate, Date deleteDate){
		this.user_no = userNo;
		this.user_id = userId;
		this.b_no = boardNo;
		this.b_content = context;
		this.b_writeday = writeDate;
		this.b_updateday = updateDate;
		this.b_deleteday = deleteDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(b_no);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(b_no, other.b_no);//prodNo.equals(other.prodNo)..
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public Date getB_writeday() {
		return b_writeday;
	}
	
	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public void setB_writeday(Date b_writeday) {
		this.b_writeday = b_writeday;
	}

	public Date getB_updateday() {
		return b_updateday;
	}

	public void setB_updateday(Date b_updateday) {
		this.b_updateday = b_updateday;
	}

	public Date getB_deleteday() {
		return b_deleteday;
	}

	public void setB_deleteday(Date b_deleteday) {
		this.b_deleteday = b_deleteday;
	}
	
	
	
}