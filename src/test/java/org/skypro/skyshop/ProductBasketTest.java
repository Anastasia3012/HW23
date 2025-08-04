package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductBasketTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket productBasket;

    @Mock
    private BasketService basketService;


    @Test
    public void addingNonexistentProductToBasketShouldThrowException() {
        //given
        UUID productID = UUID.randomUUID();
        when(storageService.getProductById(productID)).thenReturn(Optional.empty());

        //when
        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductToBasket(productID),
                "Должно выбрасываться исключение при добавлении несуществующего товара");

        verify(storageService).getProductById(productID);
        verifyNoInteractions(productBasket);
    }

    @Test
    public void addProductToBasket_CallMethodAddProduct() {
        //given
        UUID productID = UUID.randomUUID();
        Product product = mock(Product.class);

        when(storageService.getProductById(productID)).thenReturn(Optional.of(product));
        //when
        basketService.addProductToBasket(productID);

        //then
        verify(storageService).getProductById(productID);
        verify(productBasket).addProduct(productID);
    }

    @Test
    public void getUserBasketWhenBasketEmptyShouldReturnEmptyBasket() {
        // given
        when(productBasket.getAllProducts()).thenReturn((Map<UUID, Integer>) Collections.emptyList());

        // when
        UserBasket result = basketService.getUserBasket();

        // then
        assertNotNull(result, "Корзина не должна быть null");
        assertTrue(result.getItems().isEmpty(), "Корзина должна быть пустой");
        verify(productBasket).getAllProducts();
    }

    @Test
    public void getUserBasketWhenBasketHasProductsShouldReturnBasketWithProducts() {
        // given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        when(productBasket.getAllProducts()).thenReturn((Map<UUID, Integer>) List.of(product1, product2));

        // when
        UserBasket result = basketService.getUserBasket();

        // then
        assertNotNull(result, "Корзина не должна быть null");
        assertEquals(2, result.getItems().size(), "Корзина должна содержать 2 товара");
        assertTrue(result.getItems().contains(product1), "Корзина должна содержать product1");
        assertTrue(result.getItems().contains(product2), "Корзина должна содержать product2");
        verify(productBasket).getAllProducts();
    }


}
