package com.project.OrderService.domain;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection ="order")
public class Order {
	@Id
	private String orderNumber;
	private Date date;
	private String status;
	private Customer customer;

	private ArrayList<OrderLine> orderLineList = new ArrayList<OrderLine>();

	public Order() {
	}

	public Order(String orderNumber, Date date, String status) {
		super();
		this.orderNumber = orderNumber;
		this.date = date;
		this.status = status;
	}

	public void print() {
		System.out.println("Content of the order:");
		System.out.println("OrderNr :"+ orderNumber +" , Date : "+date+" , Status : "+status);
		for (OrderLine oline : orderLineList) {
			System.out.println(oline.getQuantity() + " " + oline.getProduct().getProductNumber() + " "
					+ oline.getProduct().getDescription() + " " + oline.getProduct().getPrice());
		}
		System.out.println("Total price =" + getTotalPrice());
	}

	private double getTotalPrice() {
		double totalPrice = 0.0;
		for (OrderLine oline : orderLineList) {
			totalPrice=totalPrice+(oline.getProduct().getPrice() * oline.getQuantity());
		}
		return totalPrice;
	}
	
	public void addOrderLine(OrderLine oline) {
		orderLineList.add(oline);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(ArrayList<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}
	
	

}
