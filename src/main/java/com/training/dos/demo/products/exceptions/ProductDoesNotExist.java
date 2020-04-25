package com.training.dos.demo.products.exceptions;


import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class ProductDoesNotExist extends ProductException {

    Long  id;

    public ProductDoesNotExist(Long id) {
        super(String.format("El producto con id"+ id +" no existe"));
        this.id =id;

    }
}
