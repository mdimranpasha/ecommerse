package com.inventory.demo.projection;

public interface ProductHubInventoryProjection {
    Long getProductId();
    Long getHubId();
    Double getPrice();
    Integer getQuantity();
}

