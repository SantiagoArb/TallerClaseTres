package com.training.dos.demo.products.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductSystemTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void createAndFindProduct() throws Exception {
        String name = "Arroz";
        String description = "Diana";
        BigDecimal basePrice = BigDecimal.valueOf(1500);
        BigDecimal taxRate = BigDecimal.valueOf(0.8);
        String productStatus = "BORRADOR";
        Integer invetoryQuantity  = 8;
        String json = String.format("{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String createdJson = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": %s, \"inventoryQuantity\": %s,\"id\":1}}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String findJson = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":1}}",name, description,basePrice.toString(),taxRate.toString(),productStatus,invetoryQuantity.toString());
        mockMvc.perform(
                post("/api/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(createdJson));
        mockMvc.perform(get("/api/v1/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJson));
    }
    @Test
    void createAndFindProducts() throws Exception {
        String name = "Arroz";
        String description = "Diana";
        BigDecimal basePrice = BigDecimal.valueOf(1500);
        BigDecimal taxRate = BigDecimal.valueOf(0.8);
        String productStatus = "BORRADOR";
        Integer invetoryQuantity  = 8;
        String json = String.format("{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String createdJson = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":1}}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String createdJsonDos = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":2}}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String findJsons = String.format("[{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":1},{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":2}]",name, description,basePrice.toString(),taxRate.toString(),productStatus,invetoryQuantity.toString(),name, description,basePrice.toString(),taxRate.toString(),productStatus,invetoryQuantity.toString());
        System.out.println(findJsons);
        mockMvc.perform(
                post("/api/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(createdJson));
        mockMvc.perform(
                post("/api/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(createdJsonDos));;
        mockMvc.perform(get("/api/v1/product/"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJsons));
    }
    @Test
    void createDeleteFindProducts() throws Exception {
        String name = "Arroz";
        String description = "Diana";
        BigDecimal basePrice = BigDecimal.valueOf(1500);
        BigDecimal taxRate = BigDecimal.valueOf(0.8);
        String productStatus = "BORRADOR";
        Integer invetoryQuantity  = 8;
        String json = String.format("{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String createdJson = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":1}}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String createdJsonDos = String.format("{\"product\":{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":2}}", name, description,basePrice,taxRate,productStatus,invetoryQuantity);
        String findJsons = String.format("[{\"name\": \"%s\", \"description\": \"%s\", \"basePrice\": %s, \"taxRate\": %s, \"productStatus\": \"%s\", \"inventoryQuantity\": %s,\"id\":2}]",name, description,basePrice.toString(),taxRate.toString(),productStatus,invetoryQuantity.toString(),name, description,basePrice.toString(),taxRate.toString(),productStatus,invetoryQuantity.toString());
        mockMvc.perform(
                post("/api/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(createdJson));
        mockMvc.perform(
                post("/api/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(createdJsonDos));
        mockMvc.perform(delete("/api/v1/product/1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/product/"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJsons));

    }
}
