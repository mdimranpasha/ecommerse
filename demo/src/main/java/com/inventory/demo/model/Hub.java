package com.inventory.demo.model;

import com.inventory.demo.model.common.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hub")
@Builder
public class Hub extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hub_name", length = 100)
    private String hubName;

    @Column(name = "hub_address", columnDefinition = "TEXT")
    private String hubAddress;

//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt = LocalDateTime.now(); // Default value

    @Column(name = "created_by", length = 40)
    private String createdBy;

//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 40)
    private String updatedBy;
}
