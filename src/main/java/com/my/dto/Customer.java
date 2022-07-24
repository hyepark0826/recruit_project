package com.my.dto;
import java.util.Objects;

public class Customer{
    private int user_no;//회원번호
	private String user_id;//회원아이디
	private String user_pwd;//회원비밀번호
	private String user_name;//회원이름
	private String user_email;//회원 이메일 
	public Customer() {}

	public Customer(int user_no, String user_id, String user_pwd, String user_name, String user_email) {
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_email = user_email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(user_no);
	}
	public boolean equals(Object obj) {
		if(this == obj){
	        return true;
	    }
		if (obj == null){
	        return false;
	    }
		if (getClass() != obj.getClass()){
	        return false;
	    }
		Customer other = (Customer) obj;
		return Objects.equals(user_no,other.user_no);
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

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
}