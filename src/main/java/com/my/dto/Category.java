package com.my.dto;

public class Category {

	//db에선 컬럼명 catecode, name으로 설정함
	private int cateNo;
	private String cateName;
	
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
		}
		@Override
	public String toString() {
		return "Categori [cateNo=" + cateNo + ", cateName=" + cateName + "]";
	}
	
}
