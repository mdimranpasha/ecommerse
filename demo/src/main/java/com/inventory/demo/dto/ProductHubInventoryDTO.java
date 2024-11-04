package com.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ProductHubInventoryDTO {

    private Long productId;
    private Long hubId;
    private Double price;
    private Integer quantity;
}
