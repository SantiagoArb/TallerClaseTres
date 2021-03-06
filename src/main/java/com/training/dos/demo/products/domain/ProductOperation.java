package com.training.dos.demo.products.domain;

import com.training.dos.demo.products.exceptions.ProductException;

public interface ProductOperation {

    Product value();

    ProductException failure();

    Boolean isValid();
}
