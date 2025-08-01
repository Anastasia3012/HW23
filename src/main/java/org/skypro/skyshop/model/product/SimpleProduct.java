package org.skypro.skyshop.model.product;

import java.util.NoSuchElementException;
import java.util.UUID;

public class SimpleProduct extends Product {

    private int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new NoSuchElementException("Сумма некорректна");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "Товар: " + getName() +
                ", Стоимость: " + getPrice() + " рублей.";
    }

}
