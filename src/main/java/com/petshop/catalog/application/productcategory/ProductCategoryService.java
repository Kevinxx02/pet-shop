package com.petshop.catalog.application.productcategory;

import com.petshop.catalog.domain.category.CategoryRepository;
import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.domain.productcategory.ProductCategoryReadRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductReadRepository;
import com.petshop.catalog.infrastructure.persistence.productcategory.ProductCategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductCategoryService {
    final ProductReadRepository productRepository;
    final CategoryRepository categoryRepository;
    final ProductCategoryRepository productCategoryRepository;
    final ProductCategoryReadRepository productCategoryReadRepository;

    public ProductCategoryService(ProductReadRepository productRepository, CategoryRepository categoryRepository, ProductCategoryRepository productCategoryRepository, ProductCategoryReadRepository productCategoryReadRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryReadRepository = productCategoryReadRepository;
    }

    public void assignCategoryToProduct(UUID productId, UUID categoryId) {
        Boolean product = productRepository.existsById(productId);
        Boolean category = categoryRepository.existsById(categoryId);

        if (product && category) {
            ProductCategory relation = ProductCategory.create(productId, categoryId);

            productCategoryRepository.save(relation);
        }
    }
    public List<ProductCategory> findAll() {
        return productCategoryReadRepository.findAll();
    }

    public UUID create(UUID productId, UUID categoryId) {
        return productCategoryRepository.create(productId, categoryId);
    }

    public void delete(UUID id) {
        productCategoryRepository.deleteById(id);
    }
}
