package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.exceptions.BestResultNotFoundException;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService(Map<UUID, Product> productStorage, Map<UUID, Article> articleStorage) {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
    }

    public Collection<Article> getAllArticle() {
        return Collections.unmodifiableCollection(articleStorage.values());
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(productStorage.values());
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(productStorage.values());
        result.addAll(articleStorage.values());
        return Collections.unmodifiableCollection(result);
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    private void testData() throws BestResultNotFoundException {
        Product banana = new SimpleProduct("Банан", 67, UUID.randomUUID());
        this.productStorage.put(banana.getId(), banana);
        Product apple = new SimpleProduct("Яблоко", 55, UUID.randomUUID());
        this.productStorage.put(apple.getId(), apple);
        Product pineapple = new SimpleProduct("Ананас", 619, UUID.randomUUID());
        this.productStorage.put(pineapple.getId(), pineapple);
        Product grape = new SimpleProduct("Виноград", 436, UUID.randomUUID());
        this.productStorage.put(grape.getId(), grape);

        Product milk = new DiscountedProduct("Молоко", 5, 5, UUID.randomUUID());
        this.productStorage.put(milk.getId(), milk);
        Product bread = new DiscountedProduct("Хлеб", 87, 10, UUID.randomUUID());
        this.productStorage.put(bread.getId(), bread);
        Product onion = new DiscountedProduct("Лук", 46, 15, UUID.randomUUID());
        this.productStorage.put(onion.getId(), onion);

        Product egg = new FixPriceProduct("Яйцо", UUID.randomUUID());
        this.productStorage.put(egg.getId(), egg);
        Product water = new FixPriceProduct("Вода", UUID.randomUUID());
        this.productStorage.put(water.getId(), water);
        Product cabbage = new FixPriceProduct("Капуста", UUID.randomUUID());
        this.productStorage.put(cabbage.getId(), cabbage);

        Article article1 = new Article("Тест Помада", "Как влияет цвет на ваше настроение?", UUID.randomUUID());
        this.articleStorage.put(article1.getId(), article1);
        Article article2 = new Article("Тест Молоко", "Какую жирность выбрать для выпечки?", UUID.randomUUID());
        this.articleStorage.put(article2.getId(), article2);
        Article article3 = new Article("Тест Лампочка", "Определяем мощность.", UUID.randomUUID());
        this.articleStorage.put(article3.getId(), article3);
        Article article4 = new Article("Покупаем Соль", "Полезные ванны с солью!", UUID.randomUUID());
        this.articleStorage.put(article4.getId(), article4);
    }


}
