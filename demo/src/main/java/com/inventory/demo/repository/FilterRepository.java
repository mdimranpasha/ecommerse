package com.inventory.demo.repository;

import com.inventory.demo.model.ProductInventory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<ProductInventory,Long> , JpaSpecificationExecutor<ProductInventory> {

//    List<ProductHubInventoryProjection2> findBy(Specification<ProductInventory> specification, Class<ProductHubInventoryProjection2> productHubInventoryProjection2Class);

//    List<ProductHubInventoryProjection2> findAll(Specification<ProductInventory> specification);

    List<ProductInventory> findAll(Specification<ProductInventory> specification);


//    List<ProductHubInventoryProjection2> findAll(Specification<ProductInventory> , Class<ProductHubInventoryProjection2> );
}
