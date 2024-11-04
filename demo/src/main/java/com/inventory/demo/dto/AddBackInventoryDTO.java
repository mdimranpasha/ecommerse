package com.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBackInventoryDTO {
    private Long hubId;
    private List<ProductQuantity> products;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public static class ProductQuantity {
        private Long productId;
        private Integer quantityToAdd;


    }
}
