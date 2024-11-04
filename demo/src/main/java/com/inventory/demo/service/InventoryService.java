package com.inventory.demo.service;

import com.inventory.demo.Exception.ResourceNotFoundException;
import com.inventory.demo.Exception.ValidationException;
import com.inventory.demo.constants.ErrorConstants;
import com.inventory.demo.dto.*;
import com.inventory.demo.model.ProductInventory;
import com.inventory.demo.projection.ProductHubInventoryProjection2;
import com.inventory.demo.repository.FilterRepository;
import com.inventory.demo.repository.InventoryRepository;
import com.inventory.demo.specifications.FilterSpecification;
import com.inventory.demo.util.InventoryHelper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventoryService {

    @Autowired
    private InventoryHelper inventoryHelper;
    @Autowired
   private InventoryRepository inventoryRepository;
    @Autowired
    private EntityManager entityManager;


    @Autowired
    private FilterRepository filterRepository;

//    private  final FilterRepository filterRepository;
//
//    public InventoryService(FilterRepository filterRepository) {
//        this.filterRepository = filterRepository;
//    }

    public String deductInventory(DeductInventoryDTO request) {
        inventoryHelper.validateRequest(request);

        Long hubId = request.getHubId();
        List<Long> productIds = inventoryHelper.extractProductIds(request.getProducts());
        List<Integer> quantities = inventoryHelper.extractQuantities(request.getProducts());

        Map<Long, ProductInventory> inventoryMap = inventoryHelper.fetchAndMapInventory(productIds, hubId);

        List<ProductInventory> updatedInventories = new ArrayList<>();

        for (Map.Entry<Long, ProductInventory> entry : inventoryMap.entrySet()) {

            Long productId = entry.getKey();
            ProductInventory inventory = entry.getValue();

            int index = productIds.indexOf(productId);
            if (index == -1) {
                throw new ResourceNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND,
                        String.format(ErrorConstants.PRODUCT_NOT_FOUND_MSG, productId),
                        HttpStatus.NOT_FOUND);
            }

            int quantitytoDeduct = quantities.get(index);

            if (quantitytoDeduct < 0) {
                throw new ValidationException(ErrorConstants.INVALID_QUANTITY,
                        String.format(ErrorConstants.INVALID_QUANTITY_MSG, quantitytoDeduct),
                        HttpStatus.BAD_REQUEST);
            }
            if (inventory.getProductQuantity() < quantitytoDeduct) {

                throw new ValidationException(ErrorConstants.INVALID_QUANTITY,
                        String.format(ErrorConstants.INVALID_QUANTITY_MSG, quantitytoDeduct),
                        HttpStatus.BAD_REQUEST);
            }

            inventory.setProductQuantity(inventory.getProductQuantity() - quantitytoDeduct);
            updatedInventories.add(inventory);
        }

        inventoryRepository.saveAll(updatedInventories);

        return "Successfully deducted the products from inventory";
    }

    public String addBackInventory(AddBackInventoryDTO request) {
        inventoryHelper.validateRequest(request);

        Long hubId = request.getHubId();
        List<Long> productIds = inventoryHelper.extractProductIds(request.getProducts());
        List<Integer> quantities = inventoryHelper.extractQuantities(request.getProducts());

        Map<Long, ProductInventory> inventoryMap = inventoryHelper.fetchAndMapInventory(productIds, hubId);
        List<ProductInventory> updatedInventories = new ArrayList<>();

        List<ProductInventory> updatedInventorie = new ArrayList<>();

        for (Map.Entry<Long, ProductInventory> entry : inventoryMap.entrySet()) {

            Long productId = entry.getKey();
            ProductInventory inventory = entry.getValue();

            int index = productIds.indexOf(productId);
            if (index == -1) {
                throw new ResourceNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND,
                        String.format(ErrorConstants.PRODUCT_NOT_FOUND_MSG, productId),
                        HttpStatus.NOT_FOUND);
            }

            int quantitytoAddBack = quantities.get(index);
            if (quantitytoAddBack < 0) {
                throw new ValidationException(ErrorConstants.INVALID_QUANTITY,
                        String.format(ErrorConstants.INVALID_QUANTITY_MSG, quantitytoAddBack),
                        HttpStatus.BAD_REQUEST);
            }
            if (inventory.getProductQuantity() < quantitytoAddBack) {

                throw new ValidationException(ErrorConstants.INVALID_QUANTITY,
                        String.format(ErrorConstants.INVALID_QUANTITY_MSG, quantitytoAddBack),
                        HttpStatus.BAD_REQUEST);

            }

            inventory.setProductQuantity(inventory.getProductQuantity() + quantitytoAddBack);
            updatedInventories.add(inventory);
        }

        inventoryRepository.saveAll(updatedInventories);


        return "Successfully added the products to the inventory";
    }




//    public List<ProductHubInventoryDTO> filterInventory(ProductHubInventoryFilterDTO filter) {
//
//        List<Long> productIds = CollectionUtils.isEmpty(filter.getProductIds()) ? null : filter.getProductIds();
//        List<Long> hubIds = CollectionUtils.isEmpty(filter.getHubIds()) ? null : filter.getHubIds();
//        Integer quantity = (filter.getQuantity() == null || filter.getQuantity() < 0) ? null : filter.getQuantity();
//        String hubName = (filter.getHubName() == null || filter.getHubName().isEmpty()) ? null : filter.getHubName();
//        String categoryName = (filter.getCategoryName() == null || filter.getCategoryName().isEmpty()) ? null : filter.getCategoryName();
//
//        System.out.println("productids" + productIds + "hubIds" + hubIds + "quantity" + quantity + "hubName" + hubName + "cate" + categoryName);
//
//        List<ProductHubInventoryProjection> projections = inventoryRepository.filterInventory(productIds, hubIds, quantity, hubName, categoryName);
//
//        return projections.stream()
//                .map(p -> new ProductHubInventoryDTO(p.getProductId(), p.getHubId(), p.getPrice(), p.getQuantity()))
//                .collect(Collectors.toList());
//
//
//
//    }


//    public List<ProductHubInventoryProjection2> filterInventory(ProductHubInventoryFilterDTO filter){
//        List<Long> productIds = filter.getProductIds();
//        List<Long> hubIds = filter.getHubIds();
//        Integer quantity = filter.getQuantity();
//        String hubName = filter.getHubName();
//        String categoryName = filter.getCategoryName();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProductHubInventoryProjection2> query = criteriaBuilder.createQuery(ProductHubInventoryProjection2.class);
//
//        Root<ProductInventory> root = query.from(ProductInventory.class);
//
//        // Root for Hub
//        Root<Hub> hubRoot = query.from(Hub.class);
//        Root<ProductCategory> categoryRoot=query.from(ProductCategory.class);
//        Root<Product> productRoot=query.from(Product.class);
//
//
//        Predicate predicate = criteriaBuilder.conjunction();
//        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("hubId"), hubRoot.get("id")));
//        predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("productId"),productRoot.get("id")));
//        predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(productRoot.get("productCategory"),categoryRoot.get("id")));
//        if (productIds != null && !productIds.isEmpty()) {
//            predicate = criteriaBuilder.and(predicate, root.get("productId").in(productIds));
//        }
//        if (hubIds != null && !hubIds.isEmpty()) {
//            predicate = criteriaBuilder.and(predicate, root.get("hubId").in(hubIds));
//        }
//        if (quantity != null) {
//            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(root.get("productQuantity"), quantity));
//        }
//        if (hubName != null && !hubName.isEmpty()) {
//
//            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(hubRoot.get("hubName"),hubName));
//        }
//        if (categoryName != null && !categoryName.isEmpty()) {
//            predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(categoryRoot.get("categoryName"),categoryName));
//         }
//
//        query.select(criteriaBuilder.construct(ProductHubInventoryProjection2.class,
//                        root.get("productId"),
//                        root.get("hubId"),
//                        root.get("productPrice"),
//                        root.get("productQuantity")))
//                .where(predicate)
//                .distinct(true);
//
//        TypedQuery<ProductHubInventoryProjection2> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//
//    }


//   public List<ProductHubInventoryProjection2> filterInventory(ProductHubInventoryFilterDTO filter){
//
//        List<Long>productIds=filter.getProductIds();
//        List<Long> hubIds=filter.getHubIds();
//        Integer quantity=filter.getQuantity();
//        String hubName=filter.getHubName();
//        String categoryName=filter.getCategoryName();
//
//        StringBuilder queryBuilder=new StringBuilder("SELECT new com.inventory.demo.projection.ProductHubInventoryProjection2(p.productId,p.hubId,p.productPrice,p.productQuantity) ");
//        queryBuilder.append("FROM ProductInventory p ");
//        queryBuilder.append("JOIN Hub h ON p.hubId=h.id ");
//        queryBuilder.append("JOIN Product pr ON p.productId=pr.id ");
//        queryBuilder.append("JOIN ProductCategory pc ON pr.productCategory=pc.id ");
//
//        if(productIds!=null || !productIds.isEmpty()){
//            queryBuilder.append("AND p.productId IN:productIds ");
//        }
//        if(hubIds!=null || !hubIds.isEmpty()){
//            queryBuilder.append("AND p.hubId IN:hubIds ");
//        }
//        if(quantity!=null){
//            queryBuilder.append("AND p.productQuantity>:quantity ");
//        }
//        if(hubName!=null || !hubName.isEmpty()){
//            queryBuilder.append("AND h.hubName=:hubName ");
//        }
//        if(categoryName!=null || !categoryName.isEmpty()){
//            queryBuilder.append("AND pc.categoryName=:categoryName ");
//        }
//
//        queryBuilder.append("GROUP BY p.productId, p.hubId,p.productPrice, p.productQuantity ");
//
//       //creating query
//       Query query=entityManager.createQuery(queryBuilder.toString(), ProductHubInventoryProjection2.class);
//
//       // Set parameters for each filter if they are present
//       if (productIds != null && !productIds.isEmpty()) {
//           query.setParameter("productIds", productIds);
//       }
//       if (hubIds != null && !hubIds.isEmpty()) {
//           query.setParameter("hubIds", hubIds);
//       }
//       if (quantity != null) {
//           query.setParameter("quantity", quantity);
//       }
//       if (hubName != null && !hubName.isEmpty()) {
//           query.setParameter("hubName", hubName);
//       }
//       if (categoryName != null && !categoryName.isEmpty()) {
//           query.setParameter("categoryName", categoryName);
//       }
//       return  query.getResultList();
//   }


public List<ProductHubInventoryProjection2> filterInventorySpecification(ProductHubInventoryFilterDTO filter){

   Specification<ProductInventory> specification= Specification.where(
            FilterSpecification.hasProductIds(filter.getProductIds())
                    .and(FilterSpecification.hasHubIds(filter.getHubIds())
                            .and(FilterSpecification.hasQuantityGreaterThan(filter.getQuantity()))
                            .and(FilterSpecification.hasHubName(filter.getHubName()))
                            .and(FilterSpecification.hasCategoryName(filter.getCategoryName()))
                    )
    );

    List<ProductInventory> results= filterRepository.findAll(specification);

    return results.stream()
            .map(pi -> new ProductHubInventoryProjection2(pi.getProductId(), pi.getHubId(), pi.getProductPrice(), pi.getProductQuantity()))
            .collect(Collectors.toList());
}
}





