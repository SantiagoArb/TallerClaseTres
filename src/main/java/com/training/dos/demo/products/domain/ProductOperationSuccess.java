package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.exceptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationSuccess implements ProductOperation {

    ProductOperationRequest product;

    @Override
    public ProductOperationRequest value() {
        return product;
    }

    @Override
    public ProductException failure() {
        return null;
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
