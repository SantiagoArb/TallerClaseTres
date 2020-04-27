package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InventoryQuantityTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of(35,3500,0)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> InventoryQuantity.of(value);
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
        return Stream.of(-5,Integer.min(-1,1),null)
                .map(value -> {
                    String testname = String.format("deberia ser valido para: ", value);
                    Executable executable = () -> InventoryQuantity.of(value);
                    return DynamicTest.dynamicTest(testname, () -> {
                        if(value == null ){
                            assertThrows(NullPointerException.class,executable);
                        }else{
                                assertThrows(NumberFormatException.class,executable);
                         }
                    });
                });
    }

}