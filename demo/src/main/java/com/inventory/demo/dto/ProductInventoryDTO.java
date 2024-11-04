package com.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryDTO {
    private Long hubId;
    private BigDecimal price;
//    private String hubName;
//    private String hubAddress;
//    private Long productId;
//    private String createdBy;
//    private String updatedBy;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private Integer productQuantity;































}

