package com.training.dos.demo.products.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {
    @TestFactory
    @DisplayName("Deberia Pasar")
    Stream<DynamicTest> validTest(){
        return Stream.of("Descripcion de producto",
                "descripcion que no va a superar los 280 caracteres porque debe pasar la validacion",
                "DESCRIPCION DE PRODUCTO EN MAYUSCULA SOSTENIDA")
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
        return Stream.of("","Contadordecaracteres.com es un contador automático de caracteres y " +
                "palabras en un texto. Solo colocque el cursor dentro de la caja de textos y comienze a " +
                "escribir y el contador de caracteres comenzara a contar sus palabras y caracteres a " +
                "medida de que usted vaya escribiendo......26565656",null)
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