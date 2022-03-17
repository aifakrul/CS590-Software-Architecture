package com.project.ShoppingCartCommandService.domain;

public class CartHolder {
    private String cartId;
    private String productNumber;
    private int quantity;

    public CartHolder(String cartId, String productNumber, int quantity) {
        this.cartId = cartId;
        this.productNumber = productNumber;
        this.quantity = quantity;
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
