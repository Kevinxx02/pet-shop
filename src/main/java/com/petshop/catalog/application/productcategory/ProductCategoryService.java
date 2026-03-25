package com.petshop.catalog.application.productcategory;

import com.petshop.catalog.domain.category.CategoryRepository;
import com.petshop.catalog.domain.product.ProductReadRepository;
import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.domain.productcategory.ProductCategoryReadRepository;
import com.petshop.catalog.domain.productcategory.ProductCategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCategoryService {
    final ProductReadRepository productRepository;
    final CategoryRepository categoryRepository;
    final ProductCategoryRepository productCategoryRepository;
    final ProductCategoryReadRepository productCategoryReadRepository;

    public ProductCategoryService(
            ProductReadRepository productRepository,
            CategoryRepository categoryRepository,
            ProductCategoryRepository productCategoryRepository,
            ProductCategoryReadRepository productCategoryReadRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryReadRepository = productCategoryReadRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductCategoryView> findAll() {
        return productCategoryReadRepository.findAll();
    }

    @Transactional
    public ProductCategoryView create(UUID productId, UUID categoryId) {
        if (!this.productRepository.existsById(productId)) {
            throw new RuntimeException("El producto no existe");
        }
        if (!this.categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("La categoria no existe");
        }

        if (this.productCategoryRepository.existsByProductIdAndCategoryId(productId, categoryId)) {
            throw new RuntimeException("La relacion ya existe");
        }

        final ProductCategory productCategory = ProductCategory.create(productId, categoryId);
        this.productCategoryRepository.save(productCategory);

        return new ProductCategoryView(productCategory.getId(), productCategory.getProductId(), productCategory.getCategoryId());
    }

    @Transactional
    public void delete(UUID id) {
        if(!this.productCategoryRepository.existsById(id)) {
            throw new RuntimeException("La relacion no existe");
        }

        productCategoryRepository.deleteById(id);
    }
}
