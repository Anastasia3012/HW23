package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {

    private final String name;
    private final UUID id;

    public Product(String name, UUID id) {
        this.id = getId();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя некорректно");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchContent() {
        return "PRODUCT";
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }
}

