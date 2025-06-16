package com.main.restcalculator;

import com.main.restcalculator.pojo.Result;
import com.main.restcalculator.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for service to test only functionality of the methods without API connection
 */
@SpringBootTest
public class RestCalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    /**
     * Test valid positive numbers
     */
    @Test
    @DisplayName("sum_list_of_positive_numbers")
    void testAdditionOperation_listOfPositiveNumbers() {
        Result result = calculatorService.additionOperation("1,2,3");
        assertEquals((long) 6, result.getSum());
    }

    /**
     * Test valid single positive number
     */
    @Test
    @DisplayName("sum_single_positive_number")
    void testAdditionOperation_singleItem() {
        Result result = calculatorService.additionOperation("3");
        assertEquals((long)3, result.getSum());
    }

    /**
     * Test valid single negative number
     */
    @Test
    @DisplayName("sum_single_negative_number")
    void testAdditionOperation_singleNegativeItem() {
        Result result = calculatorService.additionOperation("-3");
        assertEquals((long)-3, result.getSum());
    }

    /**
     * Test valid negative numbers
     */
    @Test
    @DisplayName("sum_list_of_negative_number")
    void testAdditionOperation_listOfNegativeNumbers() {
        Result result = calculatorService.additionOperation("-1,-2,-3");
        assertEquals((long)-6, result.getSum());
    }

    /**
     * Test valid negative positive numbers
     */
    @Test
    @DisplayName("sum_list_of_number")
    void testAdditionOperation_listOfNumbers() {
        Result result = calculatorService.additionOperation("-1,2,3");
        assertEquals((long)4, result.getSum());
    }

    /**
     * Test empty operands throws exception
     */
    @Test
    @DisplayName("sum_empty_request")
    void testAdditionOperation_emptyRequest() {
        assertThrows(ResponseStatusException.class, () -> {
            calculatorService.additionOperation("");
        });
    }

    /**
     * Test invalid characters throws exception
     */
    @Test
    @DisplayName("sum_invalid_request_with_char")
    void testAdditionOperation_invalidNumber() {
        assertThrows(ResponseStatusException.class, () -> {
            calculatorService.additionOperation("1,c,3");
        });
    }

    /**
     * Test double values
     */
    @Test
    @DisplayName("sum_double_numbers")
    void testAdditionOperation_doubleNumbers() {
        Result result = calculatorService.additionOperation("-1,2.5,3");
        assertEquals(4.5, result.getSum());
    }
}
