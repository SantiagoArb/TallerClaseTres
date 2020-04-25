package com.training.dos.demo.products.services;

import com.google.gson.Gson;
import com.training.dos.demo.products.Repositories.SqlProductsRepository;
import com.training.dos.demo.products.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private final Gson gson;
    private final SqlProductsRepository sqlProductsRepository;


    public ProductsService(Gson gson, SqlProductsRepository sqlProductsRepository) {
        this.gson = gson;
        this.sqlProductsRepository = sqlProductsRepository;

    }

    public ProductOperation insertOne(Name name, Description description, BasePrice basePrice, TaxRate taxRate, ProductStatus status, InventoryQuantity quantity) {
        ProductOperation resp = sqlProductsRepository.insertOne(ProductOperationRequest.of(name, description, basePrice, taxRate, status, quantity));
        return resp;
    }

    public ProductOperation findById(Long id) {
        ProductOperation resp = sqlProductsRepository.findById(id);
        return resp;
    }

    public List<Product> findAll() {
        List<Product> all = sqlProductsRepository.findAll();
        return all;
    }

    public ProductOperation updateOne(Long id, Name name, Description description, BasePrice basePrice, TaxRate taxRate, ProductStatus status, InventoryQuantity quantity) {
        ProductOperation request = sqlProductsRepository.updateOne(id, ProductOperationRequest.of(name, description, basePrice, taxRate, status, quantity));
        return request;
    }

    public ProductOperation deleteOne(Long id) {

        ProductOperation productOperation = sqlProductsRepository.deleteOne(id);
        return productOperation;
    }
}



