package com.inventory.demo.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHubInventoryProjection2 {
    private Long productId;
    private Long hubId;
    private BigDecimal productPrice;
    private Integer productQuantity;

}
