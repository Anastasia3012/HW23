package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.*;

public class BasketService {

    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void addProductToBasket(UUID productId) {
        Optional<Product> productOptional = storageService.getProductById(productId);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Продукта с таким ID " + productId + " нет на складе");
        }
        productBasket.addProduct(productId);
    }

    public Map<UUID, Integer> getProductInBasket() {
        return productBasket.getAllProducts();
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productQuantities = productBasket.getAllProducts();
        List<BasketItem> items = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : productQuantities.entrySet()) {
            UUID productId = entry.getKey();
            int quantity = entry.getValue();

            Optional<Product> productOptional = storageService.getProductById(productId);
            productOptional.ifPresent(product ->
                    items.add(new BasketItem(product, quantity)));
        }
        return new UserBasket(items);
    }

}
