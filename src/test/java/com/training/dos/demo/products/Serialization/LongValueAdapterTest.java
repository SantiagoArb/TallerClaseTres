package com.training.dos.demo.products.Serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.training.dos.demo.products.domain.InventoryQuantity;
import com.training.dos.demo.products.domain.Product;
import com.training.dos.demo.products.domain.ProductId;
import com.training.dos.demo.time.serialization.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LongValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(ProductId.class, new LongValueAdapter<>(ProductId::of))
                .create();
    }

    @Test
    void deserialize() throws IOException {
        // organizar
        Long value = Long.valueOf(98);
        String json = String.format("%s", value);


        //actuar
        TypeAdapter price = gson.getAdapter(ProductId.class);
        ProductId object = (ProductId) price.fromJson(json);
        //comprueban
        ProductId expected = ProductId.of(value);
        assertEquals(object.toString(), expected.toString());
    }

    @Test
    void serialize() {
        // organizar
        Long price = Long.valueOf(98);
        ProductId id = ProductId.of(price);

        //actuar
        String actual = gson.toJson(id);
        System.out.println("actual: "+actual);
        //comprueban
        String expected = String.format("%s", id.getValue());
        assertEquals(actual, expected);
    }


}