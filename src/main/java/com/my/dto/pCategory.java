package com.my.dto;

public class pCategory {
	
	private int p_no;
	private int catecode;
	
	public pCategory (int p_no, int catecode) {
		super();
		this.p_no = p_no;
		this.catecode = catecode;
	}
	
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getCatecode() {
		return catecode;
	}
	public void setCatecode(int catecode) {
		this.catecode = catecode;
	}
	
	@Override
	public String toString() {
		return "pCategory [p_no=" + p_no + ", catecode=" + catecode + "]";
	}
	
}
