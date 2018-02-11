package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product getProductByName(String name) {
        return null;
    }

    @Override
    public Product getProductByBrand(String brand) {
        return null;
    }

    @Override
    public List<Product> getLeftOvers() {
        return null;
    }

    @Override
    public void addProduct(Product product) {
    }

    @Override
    public void updateProduct(Product product) {
    }

    @Override
    public void deleteProduct(Product product) {

    }
}
