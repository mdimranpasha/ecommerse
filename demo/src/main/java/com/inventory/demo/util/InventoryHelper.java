package com.inventory.demo.util;

import com.inventory.demo.constants.ErrorConstants;
import com.inventory.demo.dto.AddBackInventoryDTO;
import com.inventory.demo.dto.DeductInventoryDTO;
import com.inventory.demo.model.ProductInventory;
import com.inventory.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InventoryHelper {

    @Autowired
    InventoryRepository inventoryRepository;

    //helper methods
    // 1. Validate the request object
    public   <T> void validateRequest(T request) {

        if (Objects.isNull(request)) {
            throw new com.inventory.demo.Exception.ValidationException(ErrorConstants.VALIDATION_ERROR,
                    String.format(ErrorConstants.VALIDATION_ERROR_MSG,"RequestBody is Empty"),
                    HttpStatus.BAD_REQUEST);
        }

        if (request instanceof DeductInventoryDTO) {
            for (DeductInventoryDTO.ProductQuantity product : ((DeductInventoryDTO) request).getProducts()) {
                if (product.getProductId() == null || product.getQuantityToDeduct() == null) {
                    throw new com.inventory.demo.Exception.ValidationException(ErrorConstants.VALIDATION_ERROR,
                            String.format(ErrorConstants.VALIDATION_ERROR_MSG,"Both productId and quantityToAdd must be provided for each product."),
                            HttpStatus.BAD_REQUEST);
                }
            }
        } else if (request instanceof AddBackInventoryDTO) {
            for (AddBackInventoryDTO.ProductQuantity product : ((AddBackInventoryDTO) request).getProducts()) {

                if (product.getProductId() == null || product.getQuantityToAdd() == null) {
                    throw new com.inventory.demo.Exception.ValidationException(ErrorConstants.VALIDATION_ERROR,
                            String.format(ErrorConstants.VALIDATION_ERROR_MSG,"Both productId and quantityToAdd must be provided for each product."),
                            HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    // 2. Extract productIds from the list
    public List<Long> extractProductIds(List<? extends Object> products) {
        return products.stream()
                .map(product -> {
                    if (product instanceof DeductInventoryDTO.ProductQuantity) {
                        return ((DeductInventoryDTO.ProductQuantity) product).getProductId();
                    } else {
                        return ((AddBackInventoryDTO.ProductQuantity) product).getProductId();
                    }
                })
                .collect(Collectors.toList());
    }

    // 3. Extract quantities from the list
    public List<Integer> extractQuantities(List<? extends Object> products) {
        return products.stream()
                .map(product -> {
                    if (product instanceof DeductInventoryDTO.ProductQuantity) {
                        return ((DeductInventoryDTO.ProductQuantity) product).getQuantityToDeduct();
                    } else {
                        return ((AddBackInventoryDTO.ProductQuantity) product).getQuantityToAdd();
                    }
                })
                .collect(Collectors.toList());
    }

    // 4. Fetch and map inventory records by productId
    public Map<Long, ProductInventory> fetchAndMapInventory(List<Long> productIds, Long hubId) {
        List<ProductInventory> inventories = inventoryRepository.findByProductIdInAndHubId(productIds, hubId);

        if (inventories.isEmpty()) {

            throw new com.inventory.demo.Exception.ValidationException(ErrorConstants.VALIDATION_ERROR,
                    String.format(ErrorConstants.VALIDATION_ERROR_MSG,"Check Hub ID and product IDs"),
                    HttpStatus.BAD_REQUEST);

        }

        return inventories.stream()
                .collect(Collectors.toMap(ProductInventory::getProductId, Function.identity()));
    }

    //     5. Update inventory quantity (both add and deduct)
    public void updateInventoryQuantity(ProductInventory inventory, int quantityChange) {
        int newQuantity = inventory.getProductQuantity() + quantityChange;

        if (newQuantity < 0) {
            throw new RuntimeException("Insufficient stock for product ID: " + inventory.getProductId());
        }

        inventory.setProductQuantity(newQuantity);
        inventoryRepository.save(inventory);
    }
}
