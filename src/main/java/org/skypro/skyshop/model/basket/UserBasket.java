package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> items;
    private double total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = calculateTotal(items);
    }

    private double calculateTotal(List<BasketItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
