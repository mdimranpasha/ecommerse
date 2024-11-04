package com.inventory.demo.constants;


public class ApiRoutes {


    public static final String BASE_API = "/api";

    public static final String PRODUCT_BASE = BASE_API + "/products";
    public static final String GET_PRODUCT_BY_ID = PRODUCT_BASE + "/{productId}";
    public static final String GET_ALL_PRODUCTS = PRODUCT_BASE;
    public static final String CREATE_PRODUCT = PRODUCT_BASE + "/create";
    public static final String UPDATE_PRODUCT = PRODUCT_BASE + "/update/{productId}";
    public static final String DELETE_PRODUCT = PRODUCT_BASE + "/delete/{productId}";


}
