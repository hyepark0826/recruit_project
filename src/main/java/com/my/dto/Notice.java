package com.my.dto;

import java.util.Date;
import java.util.Objects;

public class Notice {
	private int n_no;//공지사항 글번보
	String admin_id;//관리자아이디
	String n_title;//공지사항 제목
	String n_content;//공지사항 글내용
	int n_view;//공지사항 조회수
	Date n_writeday;//작성일자
	Date n_updateday;//수정일자
	Date n_deleteday;//삭제일자
	
	public Notice(){}
	public Notice(int boardNo,String adminId,String title,String b_content,Date writeDate) {
		this.n_no=boardNo;
		this.admin_id=adminId;
		this.n_title=title;
		this.n_content=b_content;
		this.n_writeday=writeDate;
	}
	public Notice(int boardNo,String adminId,String title,String b_content,Date writeDate,Date updateDate) {
		this.n_no=boardNo;
		this.admin_id=adminId;
		this.n_title=title;
		this.n_content=b_content;
		this.n_writeday=writeDate;
		this.n_updateday=updateDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(n_no);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		return Objects.equals(n_no, other.n_no);//prodNo.equals(other.prodNo)..
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public int getN_view() {
		return n_view;
	}
	public void setN_view(int n_view) {
		this.n_view = n_view;
	}
	public Date getN_writeday() {
		return n_writeday;
	}
	public void setN_writeday(Date n_writeday) {
		this.n_writeday = n_writeday;
	}
	public Date getN_updateday() {
		return n_updateday;
	}
	public void setN_updateday(Date n_updateday) {
		this.n_updateday = n_updateday;
	}
	public Date getN_deleteday() {
		return n_deleteday;
	}
	public void setN_deleteday(Date n_deleteday) {
		this.n_deleteday = n_deleteday;
	}
	
}

