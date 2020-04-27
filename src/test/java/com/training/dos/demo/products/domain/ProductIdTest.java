package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of(Long.valueOf(25),99L,1L)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> ProductId.of(value);
                    return DynamicTest.dynamicTest(testname, () -> {
                        assertAll(
                                () -> assertDoesNotThrow(executable),
                                () -> assertNotNull(executable)
                        );
                    });
                });
    }

    @TestFactory
    @DisplayName("Deberia Fallar")
    Stream<DynamicTest> invalidTest(){
        return Stream.of(Long.valueOf(-2),Long.valueOf(0),null)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> ProductId.of(value);
                    return DynamicTest.dynamicTest(testname, () -> {
                        if(value == null ){
                            assertThrows(NullPointerException.class,executable);
                        }else{
                            assertThrows(IllegalArgumentException.class,executable);
                        }
                    });
                });
    }
}