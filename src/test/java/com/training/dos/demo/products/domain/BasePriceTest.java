package com.training.dos.demo.products.domain;

import org.hamcrest.number.IsNaN;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasePriceTest {

    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of(BigDecimal.valueOf(1.0),BigDecimal.valueOf(120000),BigDecimal.TEN)
                .map(decimal -> {
            String testname = String.format("deberia ser valido para: ", decimal);
            Executable executable = () -> BasePrice.of(decimal);
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
        return Stream.of(BigDecimal.valueOf(-2), BigDecimal.valueOf(-1.1),null)
                .map(decimal -> {
                    String testname = String.format("deberia ser valido para: ", decimal);
                    Executable executable = () -> BasePrice.of(decimal);
                    return DynamicTest.dynamicTest(testname, () -> {
                        if(decimal == null ){
                            assertThrows(NullPointerException.class,executable);
                        }else{
                            assertThrows(IllegalArgumentException.class,executable);
                        }
                    });
                });
    }

}