package com.main.restcalculator.service;

import com.main.restcalculator.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;

/**
 * Service class of the calculator endpoint.
 */
@Service
public class CalculatorService {

    /**
     * Default constructor of the CalculatorService
     */
    public CalculatorService() {

    }
    /**
     * Service function for the addition operation. Takes operands as a string,
     * convert it to a list of integers and sum them with mappings and stream
     *
     * @param operands String parameter to catch given list of numbers from the request
     * @return a custom Result in a JSON style
     */
    public Result additionOperation(String operands){
        try {
            if (operands == null || operands.trim().isEmpty()) {
                throw new IllegalArgumentException("Operands cannot be empty");
            }

            if (!operands.matches("^\\s*-?\\d+(\\.\\d+)?(\\s*,\\s*-?\\d+(\\.\\d+)?)*\\s*$")) {
                throw new NumberFormatException("Invalid number format");
            }
            List<Double> numbers = Arrays.stream(operands.split(","))
                    .map(String::trim)
                    .map(Double::parseDouble)
                    .toList();
            double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
            /* Get a generic double to handle also float numbers since tasks ask for Numbers, and do type casting to have only 15 as in the example if they are integer.*/
            Number result;
            if (sum % 1 == 0){
                result = (long ) sum;
            }
            else{
                result = sum;
            }
            return new Result(result);

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid number format");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operands cannot be empty");
        }
    }
}
