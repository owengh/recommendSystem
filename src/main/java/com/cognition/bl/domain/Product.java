package com.cognition.bl.domain;

/**
 * 一方
 * @author lenovo
 *
 */
public class Product {

	private Integer id;
	
	private String productName;
	
	private String price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
