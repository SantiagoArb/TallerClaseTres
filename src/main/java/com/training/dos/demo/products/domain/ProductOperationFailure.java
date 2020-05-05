package com.training.dos.demo.products.domain;


import com.training.dos.demo.products.exceptions.ProductDoesNotExist;
import com.training.dos.demo.products.exceptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationFailure implements ProductOperation {

    ProductDoesNotExist exception;
    @Override
    public Product value() {
        return null;
    }

    @Override
    public ProductException failure() {
        return exception;
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
