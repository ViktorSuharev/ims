package com.visu.align.ims.service;

import com.visu.align.ims.entity.Product;

import java.math.BigInteger;
import java.util.List;

public interface ProductService {

    Product getProductById(BigInteger id);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrand(String brand);

    List<Product> getLeftovers();

    void addProduct(Product product);

    void updateProduct(BigInteger id, Product updProduct);

    void deleteProduct(BigInteger id);
}
