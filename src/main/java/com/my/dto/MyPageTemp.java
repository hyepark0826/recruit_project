package com.my.dto;

public class MyPageTemp {
	
	
	/*Table TEMP_PROJECT_TB */
	private int ptempNo; 	//임시테이블 게시판번호	
	private String userNo; 		//유저 번호
	private String ptempTitle;	 		//제목
	private String ptempContent; 		//내용
	private String ptempCreateday;		//생성일		
	private String ptempUpdateday;		//수정일		
	private String ptempDeadline; 		//종료일	
	private String ptempDeleteday;		//삭제일
	
	public MyPageTemp() {
	}
	
	public MyPageTemp(int ptempNo, String userNo, String ptempTitle, String ptempContent) {
		this.ptempNo = ptempNo;
		this.userNo = userNo;
		this.ptempTitle = ptempTitle;
		this.ptempContent = ptempContent;
	}
	
	public int getPtempNo() {
		return ptempNo;
	}
	public void setPtempNo(int ptempNo) {
		this.ptempNo = ptempNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPtempTitle() {
		return ptempTitle;
	}
	public void setPtempTitle(String ptempTitle) {
		this.ptempTitle = ptempTitle;
	}
	public String getPtempContent() {
		return ptempContent;
	}
	public void setPtempContent(String ptempContent) {
		this.ptempContent = ptempContent;
	}
	public String getPtempCreateday() {
		return ptempCreateday;
	}
	public void setPtempCreateday(String ptempCreateday) {
		this.ptempCreateday = ptempCreateday;
	}
	public String getPtempUpdateday() {
		return ptempUpdateday;
	}
	public void setPtempUpdateday(String ptempUpdateday) {
		this.ptempUpdateday = ptempUpdateday;
	}
	public String getPtempDeadline() {
		return ptempDeadline;
	}
	public void setPtempDeadline(String ptempDeadline) {
		this.ptempDeadline = ptempDeadline;
	}
	public String getPtempDeleteday() {
		return ptempDeleteday;
	}
	public void setPtempDeleteday(String ptempDeleteday) {
		this.ptempDeleteday = ptempDeleteday;
	}
	
	@Override
	public String toString() {
		return "MyPagePostDTO [ptempNo=" + ptempNo + ", userNo=" + userNo + ", ptempTitle=" + ptempTitle
				+ ", ptempContent=" + ptempContent + ", ptempCreateday=" + ptempCreateday + ", ptempUpdateday="
				+ ptempUpdateday + ", ptempDeadline=" + ptempDeadline + ", ptempDeleteday=" + ptempDeleteday + "]";
	}

	
}
