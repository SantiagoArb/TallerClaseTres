package com.training.dos.demo.products.Serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.training.dos.demo.products.domain.Name;
import com.training.dos.demo.products.domain.ProductId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StringValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Name.class, new StringValueAdapter<>(Name::of))
                .create();
    }

    @Test
    void deserialize() throws IOException {
        // organizar
        String value = "Arroz";
        String json = String.format("\"%s\"", value);


        //actuar
        TypeAdapter price = gson.getAdapter(Name.class);
        Name object = (Name) price.fromJson(json);
        //comprueban
        Name expected = Name.of(value);
        assertEquals(object.toString(), expected.toString());
    }

    @Test
    void serialize() {
        // organizar
        String value = "Arroz";
        Name name = Name.of(value);

        //actuar
        String actual = gson.toJson(name);
        System.out.println("actual: "+actual);
        //comprueban
        String expected = String.format("\"%s\"", name.getValue());
        assertEquals(actual, expected);
    }

}