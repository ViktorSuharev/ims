package com.visu.align.ims.service;

import com.visu.align.ims.dao.ProductDao;
import com.visu.align.ims.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(BigInteger id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDao.getProductsByName(name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productDao.getProductsByBrand(brand);
    }

    @Override
    public List<Product> getLeftovers() {
        return productDao.getLeftovers();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(BigInteger id, Product updProduct) {
        updProduct.setId(id);
        productDao.updateProduct(updProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(BigInteger id) {
        Product product = productDao.getProductById(id);
        productDao.deleteProduct(product);
    }
}
