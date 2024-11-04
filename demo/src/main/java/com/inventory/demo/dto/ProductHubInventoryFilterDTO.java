package com.inventory.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductHubInventoryFilterDTO {
    private List<Long> productIds;
    private List<Long> hubIds;
    private Integer quantity;
    private String hubName;
    private String categoryName;
}
