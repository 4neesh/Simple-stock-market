package com.application.exceptions;

/**
 * Custom runtime exception for invalid Stock.
 * @author aneesh
 */
public class StockException extends IllegalArgumentException {
    public StockException(String message) {
        super(message);
    }
}
