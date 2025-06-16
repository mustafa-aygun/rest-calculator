package com.main.restcalculator.controller;

import com.main.restcalculator.pojo.Result;
import com.main.restcalculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class of the calculator endpoint.
 */
@RestController
@RequestMapping(value = "/calculator/")
public class CalculatorController {

    private final CalculatorService calculatorService;

    /**
     * Constructor of the CalculatorController with the service instance
     *
     * @param calculatorService service instance for the CalculatorService class
     */
    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * GetMapping for addition operation to direct call to its related service
     *
     * @param operands String parameter to catch given list of numbers from the request
     * @return a custom Result in a JSON style
     */
    @GetMapping("add")
    public Result additionOperation(@RequestParam String operands){
        return calculatorService.additionOperation(operands);
    }
}
