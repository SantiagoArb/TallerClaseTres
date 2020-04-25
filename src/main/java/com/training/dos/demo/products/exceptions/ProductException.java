package com.training.dos.demo.products.exceptions;


public abstract class ProductException extends RuntimeException{

    public ProductException(String message){
        super(message);
    }

}
