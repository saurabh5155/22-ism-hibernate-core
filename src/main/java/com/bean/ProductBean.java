package com.bean;

import java.util.HashSet;
import java.util.Set;

public class ProductBean {
	private Integer productId;
	private String productName;
	private Integer price;
	private Set<CategoryBean> category=new HashSet<CategoryBean>();
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Set<CategoryBean> getCategory() {
		return category;
	}
	public void setCategory(Set<CategoryBean> category) {
		this.category = category;
	}
	
}
