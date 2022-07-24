package com.my.dto;

/**
 * @FileName : ResultVo.java
 * @프로그램 설명 : json 통신의 응답값을 셋팅하는 vo
 */
public class Result<E> {

	// <E>의미는 파라미터로 해당타입을 받을 수 있다.

	// 응답코드
	private int statusCode = 200;

	// 성공여부 메세지
	private String message = "success";

	// 성공여부
	private boolean check = true;

	// 화면에 넘길 json데이터 generic타입
	private E response;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public E getResponse() {
		return response;
	}

	public void setResponse(E response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "responseVo [statusCode=" + statusCode + ", message=" + message + ", check=" + check + ", response="
				+ response + "]";
	}
	
	

}
