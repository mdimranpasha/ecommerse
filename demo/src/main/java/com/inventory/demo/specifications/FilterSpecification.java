package com.inventory.demo.specifications;

import com.inventory.demo.model.Hub;
import com.inventory.demo.model.Product;
import com.inventory.demo.model.ProductCategory;
import com.inventory.demo.model.ProductInventory;
import com.inventory.demo.projection.FilterProjection;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FilterSpecification {

    public static Specification<ProductInventory> hasProductIds(List<Long> productIds) {

        return (root, query, criteriaBuilder) -> productIds == null || productIds.isEmpty()
                ? criteriaBuilder.conjunction() :
                root.get("productId").in(productIds);
    }

    public static Specification<ProductInventory> hasHubIds(List<Long> hubIds) {

        return (root, query, criteriaBuilder) -> hubIds == null || hubIds.isEmpty()
                ? criteriaBuilder.conjunction() :
                root.get("hubId").in(hubIds);
    }

    public static Specification<ProductInventory> hasQuantityGreaterThan(Integer quantity) {

        return (root, query, criteriaBuilder) -> quantity == null
                ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("productQuantity"), quantity);
    }

    public static Specification<ProductInventory> hasHubName(String hubName) {

//        return (root, query, criteriaBuilder) -> hubName == null
//                ? criteriaBuilder.conjunction() :
//                criteriaBuilder.equal(root.join("ProductInventory").join("Hub").get("hubName"), hubName);

        return (root, query, criteriaBuilder) -> {
            if (hubName == null) {
                return criteriaBuilder.conjunction();
            }

            // Subquery to find hubId by hubName
            Subquery<Long> hubIdSubquery = query.subquery(Long.class);
            Root<Hub> hubRoot = hubIdSubquery.from(Hub.class);
            hubIdSubquery.select(hubRoot.get("Id"))
                    .where(criteriaBuilder.equal(hubRoot.get("hubName"), hubName));

            // Main query filter using the hubId from the subquery
            return criteriaBuilder.in(root.get("hubId")).value(hubIdSubquery);

        };
    }

    public static Specification<ProductInventory> hasCategoryName(String categoryName) {

//        return (root, query, criteriaBuilder) -> categoryName == null
//                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.join("product").join("productCategory").get("categoryName"), categoryName);

        return (root, query, criteriaBuilder) -> {
            if (categoryName == null) {
                return criteriaBuilder.conjunction();
            }

            // First subquery: Find categoryIds from ProductCategory table based on categoryName
            Subquery<Long> categoryIdSubquery = query.subquery(Long.class);
            Root<ProductCategory> categoryRoot = categoryIdSubquery.from(ProductCategory.class);
            categoryIdSubquery.select(categoryRoot.get("Id"))
                    .where(criteriaBuilder.equal(categoryRoot.get("categoryName"), categoryName));

            // Second subquery: Find productIds in Product table where categoryId matches the categoryIdSubquery
            Subquery<Long> productIdSubquery = query.subquery(Long.class);
            Root<Product> productRoot = productIdSubquery.from(Product.class);
            productIdSubquery.select(productRoot.get("Id"))
                    .where(productRoot.get("productCategory").in(categoryIdSubquery));

            // Main query: Filter ProductInventory records by productIds from productIdSubquery
            return root.get("productId").in(productIdSubquery);


        };
    }



//    // Method to return the projection of the ProductInventory to FilterProjection
//    public static Specification<FilterProjection> toFilterProjection() {
//        return (root, query, criteriaBuilder) -> {
//            CriteriaQuery<FilterProjection> projectionQuery = criteriaBuilder.createQuery(FilterProjection.class);
//            Root<ProductInventory> productRoot = projectionQuery.from(ProductInventory.class);
//
//            // Select fields for FilterProjection
//            projectionQuery.select(criteriaBuilder.construct(FilterProjection.class,
//                    productRoot.get("productId"),
//                    productRoot.get("hubId"),
//                    productRoot.get("productPrice"),
//                    productRoot.get("productQuantity")
//            ));
//
//            return criteriaBuilder.conjunction(); // No filtering here; projection handled separately
//        };
//
//
//    }
}
