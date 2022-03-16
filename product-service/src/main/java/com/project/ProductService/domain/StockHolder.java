package com.project.ProductService.domain;

public class StockHolder {
    private String productNumber;
    private int quantity;
    private String locationCode;

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocationCode() {
        return locationCode;
    }
}
