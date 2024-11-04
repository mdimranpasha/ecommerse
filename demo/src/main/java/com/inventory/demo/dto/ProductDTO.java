package com.inventory.demo.dto;

import com.inventory.demo.enums.ActiveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO  {
    private Long id;
    private String productName;
    private Long productCategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ProductInventoryDTO> productInventories;



    @Enumerated(EnumType.STRING)
    @Column(name = "is_active", nullable = false)
    private ActiveStatus isActive = ActiveStatus.ACTIVE; // Default value























}

