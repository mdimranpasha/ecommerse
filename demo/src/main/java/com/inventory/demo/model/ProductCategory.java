package com.inventory.demo.model;

import com.inventory.demo.model.common.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category")
@Builder
public class ProductCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_Name", nullable = false, length = 60)
    private String categoryName;

    @Column(name = "category_Description", columnDefinition = "TEXT")
    private String categoryDescription;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 40)
    private String createdBy;

//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 40)
    private String updatedBy;
}

