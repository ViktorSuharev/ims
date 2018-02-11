package com.visu.align.ims.dao;

import com.visu.align.ims.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    private static final String GET_PRODUCTS_BY_NAME_QUERY = "FROM Product as prod WHERE prod.name = :name";
    private static final String GET_PRODUCTS_BY_BRAND_QUERY = "FROM Product as prod WHERE prod.brand = :brand";
    private static final String GET_LEFTOVER_PRODUCTS_QUERY = "FROM Product as prod WHERE prod.quantity < :quantity";

    private static final Integer LEFTOVER_THRESHOLD = 5;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProductByName(String name) {
        return entityManager.createQuery(GET_PRODUCTS_BY_NAME_QUERY)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return entityManager.createQuery(GET_PRODUCTS_BY_BRAND_QUERY)
                .setParameter("brand", brand)
                .getResultList();
    }

    @Override
    public List<Product> getLeftovers() {
        return entityManager.createQuery(GET_LEFTOVER_PRODUCTS_QUERY)
                .setParameter("quantity", LEFTOVER_THRESHOLD)
                .getResultList();
    }

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void deleteProduct(Product product) {
        entityManager.remove(product);
    }
}
