package com.main.restcalculator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import okhttp3.mockwebserver.MockResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller with mocking the server to test connection and response
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestCalculatorApplication.class)
@AutoConfigureMockMvc
public class RestCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static MockWebServer mockWebServer;

    /**
     * Before each test, initialize the server
     */
    @BeforeEach
    void initialize() {
        mockWebServer = new MockWebServer();
    }

    /**
     * After each test, shutdown the server
     * @throws Exception exception is needed to shut down
     */
    @AfterEach
    void afterEach() throws Exception {
        mockWebServer.shutdown();
    }

    /**
     * Test response format and result.
     *
     * @throws Exception exception is needed for mock web server
     */
    @Test
    @DisplayName("sum_valid_numbers")
    void testAdditionEndpoint_validOperands() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400));
        mockWebServer.start(8080);

        MvcResult result = this.mockMvc.perform(
                get("/calculator/add").param("operands", "7,8,10")
                        .contentType("application/json")).andExpect(status().isOk()).andReturn();//.andExpect(status().isOk()).andReturn();
        String functionResult = result.getResponse().getContentAsString();
        String expectedResult = "{\"sum\":25}";

        assertEquals(expectedResult,functionResult);
    }

    /**
     * Test response code and error message for empty request
     *
     * @throws Exception exception is needed for mock web server
     */
    @Test
    @DisplayName("sum_empty_numbers")
    void testAdditionEndpoint_emptyOperands() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400));
        mockWebServer.start(8080);

        MvcResult result = this.mockMvc.perform(
                get("/calculator/add").param("operands", "")
                        .contentType("application/json")).andExpect(status().isBadRequest()).andReturn();//.andExpect(status().isOk()).andReturn();
        int functionResult = result.getResponse().getStatus();
        int expectedResult = 400;
        String expectedResultMessage = "Operands cannot be empty";
        assertEquals(expectedResult,functionResult);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(result.getResponse().getContentAsString());
        String error = root.path("error").asText();

        assertEquals(expectedResultMessage,error);
    }

    /**
     * Test response code and error message for invalid numbers
     * @throws Exception exception is needed for mock web server
     */
    @Test
    @DisplayName("sum_invalid_numbers")
    void testAdditionEndpoint_invalidOperands() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400));
        mockWebServer.start(8080);

        MvcResult result = this.mockMvc.perform(
                get("/calculator/add").param("operands", "7,a,10")
                        .contentType("application/json")).andExpect(status().isBadRequest()).andReturn();//.andExpect(status().isOk()).andReturn();
        int functionResult = result.getResponse().getStatus();
        int expectedResult = 400;
        String expectedResultMessage = "Invalid number format";
        assertEquals(expectedResult,functionResult);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(result.getResponse().getContentAsString());
        String error = root.path("error").asText();

        assertEquals(expectedResultMessage,error);
    }
}
