package com.inventory.demo.service;



import com.inventory.demo.dto.ProductDTO;
import com.inventory.demo.dto.ProductInventoryDTO;
import com.inventory.demo.enums.ActiveStatus;

import com.inventory.demo.model.Product;
import com.inventory.demo.model.ProductInventory;

import com.inventory.demo.repository.HubRepository;
import com.inventory.demo.repository.InventoryRepository;
import com.inventory.demo.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HubRepository hubRepository;
    @Autowired
    private InventoryRepository productInventory;

//    public Optional<ProductDTO> getProductDetails(Long productId) {
//        // Fetch the product entity from the repository
//        Optional<Product> productEntityOpt = productRepository.findById(productId);
//        ProductDTO productDTO = new ProductDTO();
//        // Map the entity to DTO if present
//        if (productEntityOpt.isPresent()) {
//            Product product = productEntityOpt.get();
//
//            productDTO.setId(product.getId());
//            productDTO.setProductName(product.getProductName());
//            productDTO.setProductCategory(product.getProductCategory());
//            productDTO.setCreatedAt(product.getCreatedAt()); // Assuming createdAt is a Timestamp
//            productDTO.setUpdatedAt( (product.getUpdatedAt())); // Assuming updatedAt is a Timestamp
//            productDTO.setIsActive(ActiveStatus.valueOf(product.getIsActive().name())); // This should be fine if it's using enum
//
//
////            List<Hub> hubs = hubRepository.findAllByProductId(productId);
//            List<Hub> hubs=hubRepository.findAll();
//            List<ProductDetailDTO> hubDetails = new ArrayList<>();
//
//
//            for (Hub hub : hubs) {
//                ProductDetailDTO hubDetail = new ProductDetailDTO();
//                hubDetail.setHubId(hub.getId());
//                hubDetail.setPrice(hub.getProductPrice());
//                hubDetail.setHubName(hub.getHubName());
//                hubDetail.setHubAddress(hub.getHubAddress());
//                hubDetail.setProductId(hub.getProductId());
//                hubDetail.setCreatedAt(hub.getCreatedAt());
//                hubDetail.setCreatedBy(hub.getCreatedBy());
//                hubDetail.setUpdatedAt(hub.getUpdatedAt());
//                hubDetail.setUpdatedBy(hub.getUpdatedBy());
//
//                hubDetails.add(hubDetail);
//            }
//
//            productDTO.setProductDetails(hubDetails);
//
//        }
//        System.out.println("productDto"+productDTO.getIsActive());
//        return Optional.of(productDTO);
//    }

//    public Optional<ProductDTO> getProductAndHubDetails(Long productId, Long hubId) {
//        // Fetch the product entity from the repository
//        Optional<Product> productEntityOpt = productRepository.findById(productId);
//        // Check if the product is found
//        if (productEntityOpt.isPresent()) {
//            Product product = productEntityOpt.get();
//
//            // Create a new ProductDTO to map the product entity details
//            ProductDTO productDTO = new ProductDTO();
//
//            productDTO.setId(product.getId());
//            productDTO.setProductName(product.getProductName());
//            productDTO.setProductCategory(Long.valueOf(product.getProductCategory().toString()));
//            productDTO.setCreatedAt(LocalDateTime.parse(product.getCreatedAt().toString()));
//            productDTO.setUpdatedAt(LocalDateTime.parse(product.getUpdatedAt().toString()));
//            productDTO.setIsActive(ActiveStatus.valueOf(product.getIsActive().name()));
//
//            // Initialize product details and inventory details lists
//            productDTO.setProductDetails(new ArrayList<>());
//            productDTO.setInventoryDetails(new ArrayList<>());
//
//            // If hubId is present, fetch the corresponding hub details
//            if (hubId != null) {
//                Optional<Hub> hubEntityOpt = hubRepository.findById(hubId);
//                if (hubEntityOpt.isPresent()) {
//                    Hub hub = hubEntityOpt.get();
//                    ProductDetailDTO hubDetail = new ProductDetailDTO();
//                    hubDetail.setHubId(hub.getId());
//                    hubDetail.setPrice(hub.getProductPrice());
//                    hubDetail.setHubName(hub.getHubName());
//                    hubDetail.setHubAddress(hub.getHubAddress());
//                    hubDetail.setProductId(hub.getProductId());
//                    hubDetail.setCreatedAt(hub.getCreatedAt());
//                    hubDetail.setCreatedBy(hub.getCreatedBy());
//                    hubDetail.setUpdatedAt(hub.getUpdatedAt());
//                    hubDetail.setUpdatedBy(hub.getUpdatedBy());
//
//                    // Add the hub details to productDetails list
//                    productDTO.getProductDetails().add(hubDetail);
//                }
//            }
//
//            // Fetch inventory details based on productId
////            List<ProductInventory> inventories = productInventory.findByProductId(productId);
////            for (ProductInventory inventory : inventories) {
////                InventoryDetailDTO inventoryDetail = new InventoryDetailDTO();
////                inventoryDetail.setInventoryId(inventory.getId());
////                inventoryDetail.setQuantity(inventory.getProductQuantity());
////
////                // Add inventory details to inventoryDetails list
////                productDTO.getInventoryDetails().add(inventoryDetail);
////            }
//
//            return Optional.of(productDTO);
//        }
//
//        // Return empty Optional if the product is not found
//        return Optional.empty();
//    }

//    public Optional<ProductDTO> getProductAndHubDetails(Long productId, String hubId) throws CustomException {
//        // Check if productId is null and throw CustomException if it is
//        if (productId == null) {
//            throw new CustomException();
//        }
//
//
//        // Fetch the product entity from the repository
//        Optional<Product> productEntityOpt = productRepository.findById(productId);
//
//        // Check if the product is found
//        if (productEntityOpt.isPresent()) {
//            Product product = productEntityOpt.get();
//
//            // Create a new ProductDTO to map the product entity details
//            ProductDTO productDTO = new ProductDTO();
//
//            productDTO.setId(product.getId());
//            productDTO.setProductName(product.getProductName());
//            productDTO.setProductCategory(Long.valueOf(product.getProductCategory().toString()));
//            productDTO.setCreatedAt(LocalDateTime.parse(product.getCreatedAt().toString()));
//            productDTO.setUpdatedAt(LocalDateTime.parse(product.getUpdatedAt().toString()));
//            productDTO.setIsActive(ActiveStatus.valueOf(product.getIsActive().name()));
//
//            // Initialize product details and inventory details lists
//            productDTO.setProductDetails(new ArrayList<>());
//
//            productDTO.setInventoryDetails(new ArrayList<>());
//
//            // If hubId is present, fetch the corresponding hub details
//            if (hubId != null) {
//
//                List<Integer> hubs= new ArrayList<>();
//                Optional<Hub> hubEntityOpt = productInventory.findByHubId(hubId);
//                if (hubEntityOpt.isPresent()) {
//                    Hub hub = hubEntityOpt.get();
//                    ProductInventoryDTO hubDetail = new ProductInventoryDTO();
//                    hubDetail.setHubId(hub.getId());
////                    hubDetail.setPrice(hub.getProductPrice());
//                    hubDetail.setHubName(hub.getHubName());
//                    hubDetail.setHubAddress(hub.getHubAddress());
////                    hubDetail.setProductId(hub.getProductId());
//                    hubDetail.setCreatedAt(hub.getCreatedAt());
//                    hubDetail.setCreatedBy(hub.getCreatedBy());
//                    hubDetail.setUpdatedAt(hub.getUpdatedAt());
//                    hubDetail.setUpdatedBy(hub.getUpdatedBy());
//
//                    // Add the hub details to productDetails list
//                    productDTO.getProductDetails().add(hubDetail);
//                }
//            } else {
//                // If hubId is null, fetch all hubs for the product
//                List<Hub> hubs = productInventory.findAllByProductId(productId); // Method to get all hubs for this product
//                for (Hub hub : hubs) {
//                    ProductInventoryDTO hubDetail = new ProductInventoryDTO();
//                    hubDetail.setHubId(hub.getId());
////                    hubDetail.setPrice(hub.getProductPrice());
//                    hubDetail.setHubName(hub.getHubName());
//                    hubDetail.setHubAddress(hub.getHubAddress());
////                    hubDetail.setProductId(hub.getProductId());
//                    hubDetail.setCreatedAt(hub.getCreatedAt());
//                    hubDetail.setCreatedBy(hub.getCreatedBy());
//                    hubDetail.setUpdatedAt(hub.getUpdatedAt());
//                    hubDetail.setUpdatedBy(hub.getUpdatedBy());
//
//                    // Add each hub detail to the product details list
//                    productDTO.getProductDetails().add(hubDetail);
//                }
//            }
//
//            // Fetch inventory details based on productId (uncomment if needed)
//        /*
//        List<ProductInventory> inventories = productInventory.findByProductId(productId);
//        for (ProductInventory inventory : inventories) {
//            InventoryDetailDTO inventoryDetail = new InventoryDetailDTO();
//            inventoryDetail.setInventoryId(inventory.getId());
//            inventoryDetail.setQuantity(inventory.getProductQuantity());
//
//            // Add inventory details to inventoryDetails list
//            productDTO.getInventoryDetails().add(inventoryDetail);
//        }
//        */
//
//            return Optional.of(productDTO);
//        }
//
//        // Return empty Optional if the product is not found
//        return Optional.empty();
//    }


    public Optional<ProductDTO> getProductAndHubDetails(Long productId, String hubId)  {


         if (productId == null) {
             throw new ValidationException("Product ID can't be null");
        }

        // Fetch the product entity from the repository
        Optional<Product> productEntityOpt = productRepository.findById(productId);

         if(productEntityOpt.isEmpty()) {
            throw  new ValidationException("Invalid Product Id ");
        }

        Product product = productEntityOpt.get();

        // Create a new ProductDTO to map the product entity details
        ProductDTO.ProductDTOBuilder productDTOBuilder=ProductDTO.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .productCategory(product.getProductCategory())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .isActive(ActiveStatus.valueOf((product.getIsActive().getValue())));

        List<ProductInventoryDTO> productInventoryDTOs = new ArrayList<>();

        // If hubId is present, split and validate the hub IDs
        if (hubId != null && !hubId.trim().isEmpty()) {
            // Convert the hubId string into a list of Longs
            List<Long> hubIds = Arrays.stream(hubId.split(","))
                    .map(String::trim) // Trim any whitespace
                    .map(Long::valueOf) // Convert to Long
                    .toList();

          // Fetch the inventory details for this product and hub combination
            List<ProductInventory> inventories = productInventory.findByProductIdAndHubIdIn( productId,  hubIds);

               // If the inventories present, process further
                if (!inventories.isEmpty()) {

                    // Since inventories are already fetched, add inventory details
                        for (ProductInventory inventory : inventories) {
                            ProductInventoryDTO productInventoryDTO= ProductInventoryDTO.builder()
                                    .price(inventory.getProductPrice())
                                    .productQuantity(inventory.getProductQuantity())
                                    .hubId(inventory.getHubId())
                                    .build();

                            productInventoryDTOs.add(productInventoryDTO);
                        }
                }
        }
        else {

             List<ProductInventory>  productinventories = productInventory.findAllByProductId(productId);
             for (ProductInventory productInventory : productinventories) {

                 ProductInventoryDTO productInventoryDTO = ProductInventoryDTO.builder()
                        .hubId(productInventory.getHubId())
                        .price(productInventory.getProductPrice())
                        .productQuantity(productInventory.getProductQuantity())
                        .build();

                productInventoryDTOs.add(productInventoryDTO);

            }
        }
        productDTOBuilder.productInventories(productInventoryDTOs);

        ProductDTO productDTO = productDTOBuilder.build();
        return Optional.of(productDTO);

    }

}



