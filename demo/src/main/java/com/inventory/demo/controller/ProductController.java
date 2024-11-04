package com.inventory.demo.controller;


import com.inventory.demo.constants.ApiRoutes;
import com.inventory.demo.dto.*;
//import com.inventory.demo.projection.ProductHubInventoryProjection;
import com.inventory.demo.projection.ProductHubInventoryProjection2;
import com.inventory.demo.response.ApiResponse;
import com.inventory.demo.service.InventoryService;
import com.inventory.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiRoutes.PRODUCT_BASE)
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable Long productId, @RequestParam(required = false) String hubId)  {

        Optional<ProductDTO> product;

        product = productService.getProductAndHubDetails(productId, hubId);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping("/filter")
    public ResponseEntity<ApiResponse<List<ProductHubInventoryProjection2>>> filterInventorySpecification(
            @RequestBody ProductHubInventoryFilterDTO filter) {


        List<ProductHubInventoryProjection2> results = inventoryService.filterInventorySpecification(filter);

        if (results.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(404, "No matching products found", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "Products retrieved successfully", results));
    }

//    @PostMapping("/filter")
//    public ResponseEntity<ApiResponse<List<ProductHubInventoryProjection2>>> filterInventory(
//            @RequestBody ProductHubInventoryFilterDTO filter) {
//
//        List<ProductHubInventoryProjection2> results = inventoryService.filterInventory(filter);
//
//        if (results.isEmpty()) {
//            return ResponseEntity.ok(new ApiResponse<>(404, "No matching products found", Collections.emptyList()));
//        }
//
//        return ResponseEntity.ok(new ApiResponse<>(200, "Products retrieved successfully", results));
//    }



//    @PostMapping("/filter")
//    public ResponseEntity<ApiResponse<List<ProductHubInventoryDTO>>> filterInventory(
//            @RequestBody ProductHubInventoryFilterDTO filter) {
//
//        List<ProductHubInventoryDTO> results = inventoryService.filterInventory(filter);
//
//        if (results.isEmpty()) {
//            return ResponseEntity.ok(new ApiResponse<>(404, "No matching products found", Collections.emptyList()));
//        }
//
//        return ResponseEntity.ok(new ApiResponse<>(200, "Products retrieved successfully", results));
//    }


}

