package com.my.dto;

public class MyPagePost {
	
	/*Table PROJECT_TB */
	private int pNo; 	// 게시판번호	
	private String userNo; 		//유저 번호
	private String pTitle;	 		//제목
	private String pContent; 		//내용
	private String pCreateday;		//생성일		
	
	public MyPagePost() {
	}
	
	public MyPagePost(int pNo, String userNo, String pTitle, String pContent) {
		this.pNo = pNo;
		this.userNo = userNo;
		this.pTitle = pTitle;
		this.pContent = pContent;
	}
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpCreateday() {
		return pCreateday;
	}
	public void setpCreateday(String pCreateday) {
		this.pCreateday = pCreateday;
	}
	@Override
	public String toString() {
		return "MyPagePostDTO [pNo=" + pNo + ", userNo=" + userNo + ", pTitle=" + pTitle + ", pContent=" + pContent
				+ ", pCreateday=" + pCreateday + "]";
	}
	
	

}
