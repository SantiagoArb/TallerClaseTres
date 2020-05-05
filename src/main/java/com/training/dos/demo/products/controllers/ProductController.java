package com.training.dos.demo.products.controllers;

import com.training.dos.demo.products.domain.Product;
import com.training.dos.demo.products.domain.ProductOperation;
import com.training.dos.demo.products.domain.ProductOperationRequest;
import com.training.dos.demo.products.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService service;


        @PostMapping("/")
        public ResponseEntity<ProductOperation> insertOne(@RequestBody ProductOperationRequest operationRequest) {
        ProductOperation respuesta = service.insertOne(operationRequest.getName(), operationRequest.getDescription(), operationRequest.getBasePrice(), operationRequest.getTaxRate(),
                operationRequest.getProductStatus(), operationRequest.getInventoryQuantity());

        if (respuesta.isValid()){
            return ResponseEntity.ok(respuesta);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOperation> findById(@PathVariable Long id) {
        ProductOperation resp = service.findById(id);
        if (resp.isValid()){
            return ResponseEntity.ok(resp);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        System.out.println("findall");
       List list =  service.findAll();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
        }else{
            return ResponseEntity.ok(list);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOperation> updateOne(@PathVariable Long id, @RequestBody ProductOperationRequest operationRequest) {
        ProductOperation resp = service.updateOne(id, operationRequest.getName(), operationRequest.getDescription(), operationRequest.getBasePrice(), operationRequest.getTaxRate(),
                operationRequest.getProductStatus(), operationRequest.getInventoryQuantity());
        if (resp.isValid()){
            return ResponseEntity.ok(resp);
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductOperation> deleteOne(@PathVariable Long id) {
        ProductOperation respuesta = service.deleteOne(id);
        if (respuesta.isValid()){
            return ResponseEntity.ok(respuesta);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }
}
