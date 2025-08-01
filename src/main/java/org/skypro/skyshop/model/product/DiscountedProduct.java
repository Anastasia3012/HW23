package org.skypro.skyshop.model.product;

import java.util.NoSuchElementException;
import java.util.UUID;

public class DiscountedProduct extends Product {

    private double basePrice;
    private double discount;

    public DiscountedProduct(String name, double basePrice, double discount, UUID id) {
        super(name, id);
        if (basePrice <= 0) {
            throw new NoSuchElementException("Неверное значение суммы");
        }
        if (discount < 0 || discount > 100) {
            throw new NoSuchElementException("Неверные сумма скидки");
        }
        this.discount = discount;
        this.basePrice = basePrice;

    }

    @Override
    public double getPrice() {
        return basePrice - ((basePrice * discount) / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Товар со скидкой: " + getName() +
                ", Стоимость: " + getPrice() + " рублей. (скидка " + discount + "%)";

    }

}


