package com.application.exceptions;

/**
 * Custom runtime exception for invalid price argument.
 * @author aneesh
 */
public class PriceException extends IllegalArgumentException {
    public PriceException(String message) {
        super(message);
    }
}
