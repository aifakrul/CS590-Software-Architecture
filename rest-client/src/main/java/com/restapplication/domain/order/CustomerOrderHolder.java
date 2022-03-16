package com.restapplication.domain.order;

public class CustomerOrderHolder {
    private String customerNumber;
    private String orderNumber;

    public CustomerOrderHolder(String customerNumber, String orderNumber) {
        this.customerNumber = customerNumber;
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "CustomerOrderHolder{" +
                "customerNumber='" + customerNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
