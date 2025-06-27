package org.skypro.skyshop.controller;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> shopErrorResponseEntity(NoSuchProductException exception) {
        ShopError error = new ShopError(
                "NOT_FOUND_PRODUCT" ,
                "Продукт не найден! " + exception.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
