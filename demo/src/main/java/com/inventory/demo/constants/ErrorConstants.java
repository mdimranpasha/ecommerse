package com.inventory.demo.constants;

public class ErrorConstants {

    public static final String PRODUCT_NOT_FOUND = "ERR001";
    public static final String INSUFFICIENT_QUANTITY = "ERR002";
    public static final String INVALID_QUANTITY = "ERR003";
    public static final String VALIDATION_ERROR = "ERR004";

    public static final String PRODUCT_NOT_FOUND_MSG = "Product ID %s not found in inventory list.";
    public static final String INSUFFICIENT_QUANTITY_MSG = "Insufficient quantity for product ID: %s.";
    public static final String INVALID_QUANTITY_MSG = "Please enter valid quantity: %s.";
    public static final String VALIDATION_ERROR_MSG = "Validation failed: %s.";

    private ErrorConstants() {


     }

}
