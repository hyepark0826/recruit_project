package com.my.dto;



import java.util.List;
import java.util.Objects;



public class Project {
	
	private int p_no;
	private String user_no;
	private String p_title;
	private String p_content;
	private int p_view;
	private String p_createday;
	private String p_deadlineday;
	private String p_updateday;
	private String p_deleteday;
	private List<pCategory> p_category; //카테고리 1~3개까지 받을곳 리스트형식


	public Project() {
	}
	//모든 데이터가 들어가는 생성자
	public Project(int p_no, String user_no, String p_title, String p_content, int p_view, 
			String p_createday, String p_deadlineday, String p_updateday, String p_deleteday) {
		this.p_no = p_no;
		this.user_no = user_no;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_view = p_view;
		this.p_createday = p_createday;
		this.p_deadlineday = p_deadlineday;
		this.p_updateday = p_updateday;;
		this.p_deleteday = p_deleteday;
	}
	//not null 값만 들어가는 생성자 (모집글 작성기준)
	public Project(String user_no, String p_title, String p_content, String p_deadlineday) {
		this.user_no = user_no;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_deadlineday = p_deadlineday;
	}
	public Project(int p_no, String user_no, String p_title, String p_content, String p_createday, String p_deadlineday) {
		this.p_no = p_no;
		this.user_no = user_no;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_createday = p_createday;
		this.p_deadlineday = p_deadlineday;
	}
	public Project(int p_no, String user_no, String p_title, String p_content, String p_deadlineday) {
		this.p_no = p_no;
		this.user_no = user_no;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_deadlineday = p_deadlineday;
	}

	public int getp_no() {
		return p_no;
	}

	public void setp_no(int p_no) {
		this.p_no = p_no;
	}

	public String getuser_no() {
		return user_no;
	}

	public void setuser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public int getP_view() {
		return p_view;
	}
				
	public void setP_view(int p_views) {
		this.p_view = p_views;
	}

	public String getP_createday() {
		return p_createday;
	}

	public void setP_createday(String p_createday) {
		this.p_createday = p_createday;
	}

	public String getP_deadlineday() {
		return p_deadlineday;
	}

	public void setP_deadlineday(String p_deadlineday) {
		this.p_deadlineday = p_deadlineday;
	}

	public String getP_updateday() {
		return p_updateday;
	}

	public void setP_updateday(String p_updateday) {
		this.p_updateday = p_updateday;
	}

	public String getP_deleteday() {
		return p_deleteday;
	}

	public void setP_deleteday(String p_deleteday) {
		this.p_deleteday = p_deleteday;
	}
	public Object getCategory() {
		return null;
	}

	public List<pCategory> getP_category() {
		return p_category;
	}
	public void setP_category(List<pCategory> p_category) {
		this.p_category = p_category;
	}
	

}
