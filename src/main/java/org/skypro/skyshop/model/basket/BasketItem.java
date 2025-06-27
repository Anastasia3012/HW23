package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.NoSuchElementException;

public class BasketItem {
    private Product product;
    private int quantity;


    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        if (quantity <= 0) {
            throw new NoSuchElementException("Число должно быть положительным!");
        }
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
