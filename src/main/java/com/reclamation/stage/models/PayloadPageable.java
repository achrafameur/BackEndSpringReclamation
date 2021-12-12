package com.reclamation.stage.models;

import javax.persistence.Column;

public class PayloadPageable {
	private Integer pageSize;
	
	private Integer pageNumber;
	
	public PayloadPageable(Integer pageSize, Integer pageNumber) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public PayloadPageable() {
		super();
	}
	
}