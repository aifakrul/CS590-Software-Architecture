package com.project.ShoppingCartService.domain;

public class CartHolder {
    private String cardId;
    private String productNumber;
    private int quantity;

    public CartHolder(String cardId, String productNumber, int quantity) {
        this.cardId = cardId;
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public String getCartId() {
        return cardId;
    }

    public void setCartId(String cardId) {
        this.cardId = cardId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}