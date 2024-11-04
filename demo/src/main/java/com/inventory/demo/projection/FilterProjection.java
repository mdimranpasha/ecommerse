package com.inventory.demo.projection;

import java.math.BigDecimal;

public interface FilterProjection {

    Long getProductId();
    Long getHubId();
    BigDecimal getProductPrice();
    Integer getProductQuantity();
}
