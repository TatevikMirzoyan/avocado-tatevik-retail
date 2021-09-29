package com.avocado.tatevik.retail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=8080",
                "management.server.port=9090"
        })
public class AvocadoTatevikRetailApplicationTests {

    @Test
    void contextLoads() {
    }

    @LocalServerPort
    private Integer port;

//    @LocalManagementPort
    private Integer managementPort;

    protected static final String BASE_URL = "http://localhost:";

    @Test
    void printPortsInUse() {
        System.out.println(port); // 8080
        System.out.println(managementPort); // 9090
    }

    @ParameterizedTest
    @DisplayName("checks if the given string values ends with the alphabet r")
    @ValueSource(strings = {"radar","car","door"})
    void endsWithTest(String string){
        assertTrue(string.endsWith("r"));
    }
}
