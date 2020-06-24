package com.ishang.wastedemo.admin.entity;

public class ResultData {
	
	private Integer id;
	private Integer count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "ResultData [id=" + id + ", count=" + count + "]";
	}
	
}
