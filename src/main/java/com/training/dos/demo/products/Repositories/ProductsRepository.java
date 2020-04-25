package com.training.dos.demo.products.Repositories;

import com.training.dos.demo.products.domain.Product;
import com.training.dos.demo.products.domain.ProductOperation;
import com.training.dos.demo.products.domain.ProductOperationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository {

    ProductOperation insertOne(ProductOperationRequest operationRequest);

    ProductOperation findById(Long id);

    List<Product> findAll();

    ProductOperation updateOne(Long id, ProductOperationRequest operationRequest);

    ProductOperation deleteOne(Long id);

}
