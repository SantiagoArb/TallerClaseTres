package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TaxRateTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of(BigDecimal.valueOf(0.1),BigDecimal.valueOf(0.5),BigDecimal.valueOf(1))
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> TaxRate.of(value);
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
        return Stream.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(1.1),null)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> TaxRate.of(value);
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