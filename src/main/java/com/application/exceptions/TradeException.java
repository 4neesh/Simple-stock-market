package com.application.exceptions;

/**
 * Custom runtime exception for invalid Trade.
 * @author aneesh
 */
public class TradeException extends RuntimeException{

    public TradeException(String message) {
        super(message);
    }
}
