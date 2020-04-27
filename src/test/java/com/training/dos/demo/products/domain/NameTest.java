package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of("arrozdiana",
                "POLLOCONpapas",
                "NOMBRE EN MAYUSCULA SOSTENIDA")
                .map(desc -> {
                    String testname = String.format("deberia ser valido para: ", desc);
                    Executable executable = () -> Description.of(desc);
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
        return Stream.of("",null)
                .map(desc -> {
                    String testname = String.format("deberia ser valido para: ", desc);
                    Executable executable = () -> Description.of(desc);
                    return DynamicTest.dynamicTest(testname, () -> {
                        if(desc == null ){
                            assertThrows(NullPointerException.class,executable);
                        }else{
                            if (desc.isEmpty()){
                                assertThrows(EmptyStackException.class,executable);
                            }else{
                                assertThrows(NumberFormatException.class,executable);
                            }

                        }
                    });
                });
    }

}