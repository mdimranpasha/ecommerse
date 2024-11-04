package com.inventory.demo.repository;

import com.inventory.demo.model.ProductInventory;
//import com.inventory.demo.projection.ProductHubInventoryProjection;
import com.inventory.demo.projection.ProductHubInventoryProjection;
import com.inventory.demo.projection.ProductHubInventoryProjection2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<ProductInventory,Long> {

//    List<ProductInventory> findByProductId(Long productId);

//    Optional<Hub> findByHubId(Long hubId);

    List<ProductInventory> findAllByProductId(Long productId);

//    List<ProductInventory> findByProductIdAndHubId(Long productId, Long id);

    List<ProductInventory> findByProductIdAndHubIdIn(Long productId, List<Long> hubIds);


    List<ProductInventory> findByProductIdInAndHubId(List<Long> productIds, Long hubId);




//    @Query(value = "SELECT pi.product_id AS productId, pi.hub_id AS hubId, pi.product_Price AS price, pi.product_Quantity AS quantity " +
//            "FROM product_inventory pi " +
//            "JOIN hub h ON pi.hub_id=h.id " +
//            "JOIN product_category pc ON pi.product_Category=pc.id " +
//            "WHERE (COALESCE(?1) IS NULL OR pi.product_id IN (?1)) " +
//            "AND (COALESCE(?2) IS NULL OR pi.hub_id IN (?2)) " +
//            "AND (?3 IS NULL OR pi.product_Quantity > ?3)"+
//            "AND(?4 IS NULL OR h.hub_name IN (?4))"+
//            "AND(?5 IS NULL OR pc.category_Name IN(?5))", nativeQuery = true)

    @Query(value = "SELECT pi.product_id AS productId, pi.hub_id AS hubId, pi.product_Price AS price, pi.product_Quantity AS quantity " +
            "FROM product_inventory pi " +
            "JOIN product p ON pi.product_id = p.id " +
            "JOIN product_category pc ON p.product_category = pc.id " +
            "JOIN hub h ON pi.hub_id = h.id " +
            "WHERE (COALESCE(?1) IS NULL OR pi.product_id IN (?1)) " +
            "AND (COALESCE(?2) IS NULL OR pi.hub_id IN (?2)) " +
            "AND (?3 IS NULL OR pi.product_Quantity > ?3) " +
            "AND (?4 IS NULL OR h.hub_name IN (?4)) " +
            "AND (?5 IS NULL OR pc.category_Name IN (?5))",
            nativeQuery = true)
    List<ProductHubInventoryProjection> filterInventory(
            List<Long> productIds,
            List<Long> hubIds,
            Integer quantity,
             String hubName,
            String categoryName);


}
