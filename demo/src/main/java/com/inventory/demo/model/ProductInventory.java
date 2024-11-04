package com.inventory.demo.model;

import com.inventory.demo.model.common.Auditable;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_inventory")
@Builder
public class ProductInventory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hub_id", nullable = false)
    private Long hubId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_Price", precision = 10, scale = 2, nullable = false)
    private BigDecimal productPrice = BigDecimal.ZERO; // Default value


    @Column(name = "product_Quantity")
    private Integer productQuantity;

//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt = LocalDateTime.now(); // Set default value

    @Column(name = "created_by", length = 40)
    private String createdBy;






























    @Column(name = "updated_by", length = 40)
    private String updatedBy;

}
