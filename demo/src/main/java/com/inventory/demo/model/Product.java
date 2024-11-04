package com.inventory.demo.model;

import com.inventory.demo.enums.ActiveStatus;
import com.inventory.demo.model.common.Auditable;
import lombok.*;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Builder
@Data
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 60)
    private String productName;

    @Column(name = "product_Category", nullable = false)
    private Long productCategory;

    @Column(name = "created_by", length = 40)
    private String createdBy;

    @Column(name = "updated_by", length = 40)
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_active", nullable = false)
    private ActiveStatus isActive = ActiveStatus.ACTIVE; // Default value

















}
