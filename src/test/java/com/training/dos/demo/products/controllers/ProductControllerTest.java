package com.training.dos.demo.products.controllers;

import com.google.gson.Gson;
import com.training.dos.demo.products.domain.*;
import com.training.dos.demo.products.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    ProductsService services;
    @Test
    void insertOne() {
    }

    @Test
    void findProductEmpty() throws Exception {
        // organizar....
        when(services.findById(anyLong()))
                .thenReturn(ProductOperationFailure.of(null));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findById() throws Exception {
        // organizar....

        Product product = Product.from(
                1L,
                Name.of("arroz2"),
                Description.of("description"),
                BasePrice.of(BigDecimal.valueOf(2500)),
                TaxRate.of(BigDecimal.valueOf(0.9)),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(50)
        );
        String productJson = this.gson.toJson(ProductOperationSuccess.of(product));
        System.out.println(productJson);
        when(services.findById(anyLong()))
                .thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }

    @Test
    void findProductsEmpty() throws Exception {
        // organizar....
        List<Product> products = new ArrayList();
        when(services.findAll())
                .thenReturn(products);

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void findProducts() throws Exception {
        // organizar....
        List<Product> products = new ArrayList<>();
        Product product = Product.from(
                1L,
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        products.add(product);
        String productJson = this.gson.toJson(products);
        System.out.println(productJson
        );
        when(services.findAll())
                .thenReturn(products);
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }
    @Test
    void insertProductFail() throws Exception {
        // organizar....
        ProductOperationRequest product = ProductOperationRequest.of(
                Name.of("Arroz"),
                Description.of("Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(1)
        );
        when(services.insertOne(product.getName(),product.getDescription(),product.getBasePrice(),product.getTaxRate(),product.getProductStatus(),product.getInventoryQuantity()))
                .thenReturn(null);

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/product/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void insertProducts() throws Exception {
        // organizar....
        ProductOperationRequest productRequest = ProductOperationRequest.of(
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        Product product = Product.from(
                3L,
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        String productJson = this.gson.toJson(ProductOperationSuccess.of(product));
        String requestJson = this.gson.toJson(productRequest);
        when(services.insertOne(productRequest.getName(),productRequest.getDescription(),productRequest.getBasePrice(),productRequest.getTaxRate(),productRequest.getProductStatus(),productRequest.getInventoryQuantity()))
                .thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }
    @Test
    void updateProductFail() throws Exception {
        // organizar....
        ProductOperationRequest product = ProductOperationRequest.of(
                Name.of("Arroz"),
                Description.of("Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(1)
        );
        when(services.updateOne(null,product.getName(),product.getDescription(),product.getBasePrice(),product.getTaxRate(),product.getProductStatus(),product.getInventoryQuantity()))
                .thenReturn(null);

        // act
        // assert
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/product/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateProducts() throws Exception {
        // organizar
        ProductOperationRequest productRequest = ProductOperationRequest.of(
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        Product product = Product.from(
                1L,
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        String productJson = this.gson.toJson(ProductOperationSuccess.of(product));
        String requestJson = this.gson.toJson(productRequest);
        System.out.println(productJson);
        when(services.updateOne(product.getId(),Name.of("Arrozupdated"),product.getDescription(),product.getBasePrice(),product.getTaxRate(),product.getProductStatus(),product.getInventoryQuantity()))
                .thenReturn(ProductOperationSuccess.of(product));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","1")
                .content(requestJson);
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }
    @Test
    void deleteProductFail() throws Exception {
        when(services.deleteOne(1L))
                .thenReturn(ProductOperationFailure.of(null));

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/product/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteProducts() throws Exception {
        Product product = Product.from(
               23L,
                Name.of("Arroz"),
                Description.of("Arroz Diana"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.valueOf("BORRADOR"),
                InventoryQuantity.of(12)
        );
        String productJson = this.gson.toJson(ProductOperationSuccess.of(product));
        when(services.deleteOne(1L))
                .thenReturn(ProductOperationSuccess.of(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/product/1")
                .param("id","1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }
}