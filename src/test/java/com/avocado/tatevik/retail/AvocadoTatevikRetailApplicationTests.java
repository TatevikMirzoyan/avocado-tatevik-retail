package com.avocado.tatevik.retail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=8080",
                "management.server.port=9090"
        })
@WithMockUser
public class AvocadoTatevikRetailApplicationTests {

    @Test
    void contextLoads() {
    }

    @LocalServerPort
    private Integer port;

    protected static final String BASE_URL = "http://localhost:";

    @Test
    void printPortsInUse() {
        System.out.println("URL: " + BASE_URL);
        System.out.println("port: " + port);
    }
}
