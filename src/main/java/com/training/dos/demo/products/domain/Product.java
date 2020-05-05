package com.training.dos.demo.products.domain;

import lombok.Value;

@Value(staticConstructor = "from")
public class Product {

    Long id;
    Name name;
    Description description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    InventoryQuantity inventoryQuantity;

    private Product(Long id, Name name, Description description, BasePrice basePrice, TaxRate taxRate, ProductStatus productStatus, InventoryQuantity inventoryQuantity){
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.taxRate = taxRate;
        this.productStatus = productStatus;
        this.inventoryQuantity = inventoryQuantity;
    }
}
