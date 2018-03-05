package com.visu.align.ims.service;

import com.visu.align.ims.dao.ProductDao;
import com.visu.align.ims.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int LEFTOVERS_THRESHOLD_NUMBER = 5;

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(BigInteger id) {
        return productDao.findOne(id);
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
        return productDao.getLeftovers(LEFTOVERS_THRESHOLD_NUMBER);
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void updateProduct(BigInteger id, Product updProduct) {
        updProduct.setId(id);
        productDao.save(updProduct);
    }

    @Override
    public void deleteProduct(BigInteger id) {
        productDao.delete(id);
    }
}
