package com.visu.align.ims.service;

import com.visu.align.ims.model.Product;

import java.math.BigInteger;
import java.util.List;

/**
 * Product Service Logic
 */
public interface ProductService {

    /**
     * Find product by specified id
     *
     * @param id Product id
     * @return {@link Product} with specifed {@link Product#id}
     */
    Product getProductById(BigInteger id);

    /**
     * Find products by specified name
     *
     * @param name name of product
     * @return list of {@link Product} with specified {@link Product#name}
     */
    List<Product> getProductsByName(String name);

    /**
     * Find products by specified name
     *
     * @param brand brand of product
     * @return list of {@link Product} with specified {@link Product#brand}
     */
    List<Product> getProductsByBrand(String brand);

    /**
     * Find products which quantity less than specified
     *
     * @return list of {@link Product}
     * with a little number of {@link Product#quantity} param
     * Threshold quantity is set in implementation
     */
    List<Product> getLeftovers();

    /**
     * Add product
     *
     * @param product to be added
     */
    void addProduct(Product product);

    /**
     * Update product
     *
     * @param id id of product to be updated
     * @param updProduct content of updated product
     */
    void updateProduct(BigInteger id, Product updProduct);

    /**
     * Delete product
     *
     * @param id id of product to be deleted
     */
    void deleteProduct(BigInteger id);
}
