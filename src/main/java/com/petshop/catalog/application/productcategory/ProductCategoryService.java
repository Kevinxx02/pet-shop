package com.petshop.catalog.application.productcategory;

import com.petshop.catalog.application.product.ProductView;
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

    @Transactional
    public void assignCategoryToProduct(UUID productId, UUID categoryId) {

        Boolean product = productRepository.existsById(productId);
        Boolean category = categoryRepository.existsById(categoryId);

        if (product && category) {
            ProductCategory relation = ProductCategory.create(productId, categoryId);

            productCategoryRepository.save(relation);
        }
    }

    @Transactional(readOnly = true)
    public List<ProductView> listProductsFromCategory(UUID categoryId) {
        return productCategoryReadRepository.listProductsFromCategory(categoryId);
    }

    @Transactional
    public Boolean updateCategoriesFromProduct(UUID productId, List<UUID> categories) {
        if (productId == null || categories == null || categories.isEmpty()) {
            return false;
        }

        /*  1) Eliminar todos los registros de Product Category que tengan productId ingresado  */
        productCategoryRepository.deleteByIdProductId(productId);
        
        /*  2) Iterar sobre las categories que han sido ingresadas */
        for (UUID categoryId : categories) {
            /*  Asignar categoria al product id*/
            this.assignCategoryToProduct(productId, categoryId);
        }

        return true;
    }
}
