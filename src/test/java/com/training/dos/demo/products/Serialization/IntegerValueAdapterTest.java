package com.training.dos.demo.products.Serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.training.dos.demo.products.domain.BasePrice;
import com.training.dos.demo.products.domain.InventoryQuantity;
import com.training.dos.demo.products.domain.ProductId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.training.dos.demo.time.serialization.IntegerValueAdapter;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IntegerValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(InventoryQuantity.class, new IntegerValueAdapter<>(InventoryQuantity::of))
                .create();
    }

    @Test
    void deserialize() throws IOException {
        // organizar
        Integer value = Integer.valueOf(98);
        String json = String.format("%s", value);


        //actuar
        TypeAdapter price = gson.getAdapter(InventoryQuantity.class);
        InventoryQuantity object = (InventoryQuantity) price.fromJson(json);
        //comprueban
        InventoryQuantity expected = InventoryQuantity.of(value);
        assertEquals(object.toString(), expected.toString());
    }

    @Test
    void serialize() {
        // organizar
        Integer price = Integer.valueOf(98);
        InventoryQuantity inventory = InventoryQuantity.of(price);

        //actuar
        String actual = gson.toJson(inventory);
        System.out.println("actual: "+actual);
        //comprueban
        String expected = String.format("%s", inventory.getValue());
        assertEquals(actual, expected);
    }


}