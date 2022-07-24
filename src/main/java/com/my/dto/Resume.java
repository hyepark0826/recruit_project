package com.my.dto;

public class Resume {
	private int resume_no;
	private String resume_title;
	private int resume_age;
	private String resume_major;
	private String resume_email;
	private String resume_introduce;
	public Resume(int resume_no, int resume_age, String resume_title, String resume_major, String resume_email, String resume_introduce) {
		this.resume_no=resume_no;
		this.resume_age=resume_age;
		this.resume_title=resume_title;
		this.resume_major=resume_major;
		this.resume_email=resume_email;
		this.resume_introduce=resume_introduce;
	}
	public String getResume_email() {
		return resume_email;
	}

	public void setResume_email(String resume_email) {
		this.resume_email = resume_email;
	}
	
	public Resume() {
		super();
	}
	//S\

	public int getResume_no() {
		return resume_no;
	}

	public void setResume_no(int resume_no) {
		this.resume_no = resume_no;
	}

	public String getResume_title() {
		return resume_title;
	}

	public void setResume_title(String resume_title) {
		this.resume_title = resume_title;
	}

	public int getResume_age() {
		return resume_age;
	}

	public void setResume_age(int resume_age) {
		this.resume_age = resume_age;
	}

	public String getResume_major() {
		return resume_major;
	}

	public void setResume_major(String resume_major) {
		this.resume_major = resume_major;
	}

	public String getResume_introduce() {
		return resume_introduce;
	}

	public void setResume_introduce(String resume_introduce) {
		this.resume_introduce = resume_introduce;
	}
}
