package com.main.restcalculator.pojo;

/**
 * A custom return template for the result of the calculator
 */
public class Result {
    private Number sum;

    /**
     * Constructor of the Result object with the sum value
     *
     * @param sum the result of the sum operation
     */
    public Result(Number  sum){
        this.sum = sum;
    }

    /**
     * Getter function for the sum
     *
     * @return return integer sum value
     */
    public Number getSum() {
        return sum;
    }

    /**
     * Setter function for the sum
     *
     * @param sum it gets an integer sum value and assign it to object instance
     */
    public void setSum(Number  sum) {
        this.sum = sum;
    }
}
