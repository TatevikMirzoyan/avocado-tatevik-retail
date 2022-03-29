package com.avocado.tatevik.retail.integration.shop;

import com.avocado.tatevik.retail.common.exception.handler.GlobalExceptionHandler;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.shop.ShopCRUDController;
import com.avocado.tatevik.retail.controller.shop.dto.ShopCreationDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopUpdateDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShopCRUDControllerIntegrationTest {

    // TODO: 9/6/21 In the beforeEach  create some entities, run tests on them, and in the afterEach method delete them from db

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ShopCRUDController shopCRUDController;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.shopCRUDController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .apply(springSecurity())
                .build();
    }

    @ParameterizedTest
    @DisplayName("checks if shop entities exist with given id values")
    @ValueSource(ints = {1, 2})
    public void performGetRequestWithValidId_andResponseIsOk(int id) throws Exception {
        // In my case I have Shops with id 1 and 2
        mockMvc.perform(get("/shops/" + id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @ParameterizedTest
    @DisplayName("checks if Get request returns NotFound for invalid id values")
    @ValueSource(ints = {-5, 0, Integer.MAX_VALUE})
    public void performGetRequestWithInvalidId_andResponseIsNotFound(int id) throws Exception {
        mockMvc.perform(get("/shops/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Shop entity with id " + id + " does not exist.")))
                .andDo(print());
    }

    @ParameterizedTest
    @DisplayName("checks if Get request returns BadRequest for invalid id double values")
    @ValueSource(doubles = {-5.1, 2.5, 1.58})
    public void performGetRequestWithInvalidDoubleId_andResponseIsBadRequest(double id) throws Exception {
        mockMvc.perform(get("/shops/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'")))
                .andDo(print());
    }

    @ParameterizedTest
    @DisplayName("checks if Get request returns BadRequest for invalid id string values")
    @ValueSource(strings = {" ", "aaa ", " null"})
    public void performGetRequestWithInvalidStringId_andResponseIsBadRequest(String id) throws Exception {
        if (id.isBlank()) {
            MvcResult result = mockMvc.perform(get("/shops/" + id.trim()))
                    .andExpect(status().is(405))
                    .andDo(print())
                    .andReturn();
            Assertions.assertEquals(result.getResponse().getErrorMessage(), "Request method 'GET' not supported");
        } else {
            mockMvc.perform(get("/shops/" + id.trim()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("ERROR")))
                    .andExpect(content().string(containsString("UUTI_45")))
                    .andExpect(content().string(containsString("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'")))
                    .andDo(print());
        }
    }

    @Test
    public void performPostRequestWithValidFields_andResponseIsOk() throws Exception {
        int number = new Random().nextInt();
        ShopCreationDto shop = new ShopCreationDto("shop" + number, true, true);
        String json = mapper.writeValueAsString(shop);
        MvcResult result = mockMvc
                .perform(post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        GenericResponse<ShopDto> createdShop = mapper.readValue(response, new TypeReference<>() {
        });
        ShopDto shopDto = createdShop.getBody();
        Assertions.assertEquals("shop" + number, shopDto.getName());
    }

    @ParameterizedTest
    @DisplayName("checks if Post request returns BadRequest for invalid Shops")
    @MethodSource(value = "providedShopCreationDtosWithInvalidFields")
    public void performPostRequestWithInvalidFields_andResponseIsBadRequest(ShopCreationDto shop) throws Exception {
        String json = mapper.writeValueAsString(shop);
        mockMvc
                .perform(post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Shop name can not be null")))
                .andDo(print());
    }

    @ParameterizedTest
    @NullSource
    public void performPostRequestWithNull_andResponseIsBadRequest(ShopCreationDto shop) throws Exception {
        String json = mapper.writeValueAsString(shop);
        mockMvc
                .perform(post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Required request body is missing")))
                .andDo(print());
    }

    @Test
    public void performPutRequestWithValidFields_andResponseIsOk() throws Exception {
        int number = new Random().nextInt();
        ShopUpdateDto shop = new ShopUpdateDto(null, "shop" + number, true, false);
        String json = mapper.writeValueAsString(shop);
        MvcResult result = mockMvc
                .perform(put("/shops/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        GenericResponse<ShopDto> createdShop = mapper.readValue(response, new TypeReference<>() {
        });
        ShopDto shopDto = createdShop.getBody();
        Assertions.assertEquals("shop" + number, shopDto.getName());
    }

    @Test
    public void performPutRequestWithExistedNameField_andResponseIs4XX() throws Exception {
        // In my case I have Shop with name 'SAS' in db
        ShopUpdateDto shop = new ShopUpdateDto(null, "SAS", true, true);
        String json = mapper.writeValueAsString(shop);
        mockMvc
                .perform(put("/shops/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(422))
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Shop with name SAS already exist.")))
                .andDo(print());
    }

    @ParameterizedTest
    @DisplayName("checks if Delete request returns NotFound for invalid id values")
    @ValueSource(ints = {-5, 0, Integer.MAX_VALUE})
    public void performDeleteRequestWithInvalidId_andResponseIsNotFound(int id) throws Exception {
        mockMvc
                .perform(delete("/shops/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("ERROR")))
                .andExpect(content().string(containsString("UUTI_45")))
                .andExpect(content().string(containsString("Shop entity with id " + id + " does not exist.")))
                .andDo(print());
    }

    public static Stream<Arguments> providedShopCreationDtosWithInvalidFields() {
        ShopCreationDto shop1 = new ShopCreationDto("", false, false);
        ShopCreationDto shop2 = new ShopCreationDto(null, true, true);
        return Stream.of(
                Arguments.of(shop1),
                Arguments.of(shop2)
        );
    }
}
