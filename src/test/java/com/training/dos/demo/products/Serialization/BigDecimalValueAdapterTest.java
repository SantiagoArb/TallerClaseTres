package com.training.dos.demo.products.Serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.training.dos.demo.products.domain.BasePrice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalValueAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(BasePrice.class, new BigDecimalValueAdapter<>(BasePrice::of))
                .create();
    }

    @Test
    void deserialize() throws IOException {
        // organizar
        BigDecimal value = BigDecimal.valueOf(2500);
        String json = String.format("%s", value);


        //actuar
        TypeAdapter price = gson.getAdapter(BasePrice.class);
        BasePrice object = (BasePrice) price.fromJson(json);
        //comprueban
        BasePrice expected = BasePrice.of(value);
        assertEquals(object.toString(), expected.toString());
    }

    @Test
    void serialize() {
        // organizar
        BigDecimal price = BigDecimal.valueOf(2500);
        BasePrice basePrice = BasePrice.of(price);

        //actuar
        String actual = gson.toJson(basePrice);
        System.out.println("actual: "+actual);
        //comprueban
        String expected = String.format("%s", basePrice.getValue());
        assertEquals(actual, expected);
    }

}