package com.cognition.bl.domain;


/**
 * 多方
 * @author lenovo
 *
 */
public class SalesOrder {
	
	private Integer id;
	
	private String orderNo;
	
	private String payAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
}
