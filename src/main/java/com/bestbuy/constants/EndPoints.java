package com.bestbuy.constants;

public class EndPoints {
    // This is Endpoints of PRODUCTS
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/{id}";
    public static final String UPDATE_PRODUCT_BY_ID = "/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/{id}";

    // This is Endpoints of STORES
    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/{storeID}";
    public static final String UPDATE_STORE_BY_ID = "/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/{storeID}";
}
