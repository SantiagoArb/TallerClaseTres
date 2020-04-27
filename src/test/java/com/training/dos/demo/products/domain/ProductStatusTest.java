package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of("BORRADOR","PUBLICADO")
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> ProductStatus.valueOf(value);
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
        return Stream.of("LIMITADO","BORRADO",null)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> ProductStatus.valueOf(value);
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