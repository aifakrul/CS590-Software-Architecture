package com.restapplication.domain.cart;

public class CartHolder {
    private String cartId;
    private String productNumber;
    private int quantity;

    public CartHolder(String cartId, String productNumber, int quantity) {
        this.cartId = cartId;
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public CartHolder(String cartId) {
        this.cartId = cartId;
    }

    public CartHolder(String cartId, String productNumber) {
        this.cartId = cartId;
        this.productNumber = productNumber;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cardId) {
        this.cartId = cardId;
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
