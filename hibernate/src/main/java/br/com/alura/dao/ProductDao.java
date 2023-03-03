package br.com.alura.dao;

import br.com.alura.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Product product) {
        this.entityManager.persist(product);
    }

    public Product getProductById(Long id) {
        return this.entityManager.find(Product.class, id);
    }

    public List<Product> getProducts() {
        String jpql = "SELECT p FROM Product p";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> getProductByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();

    }

    public List<Product> getProductByCategoryName(String categoryName) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :categoryName";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    public List<BigDecimal> getPriceOfProduct() {
        String jpql = "SELECT p.price FROM Product p";
        return this.entityManager.createQuery(jpql, BigDecimal.class).getResultList();
    }

    public BigDecimal getPriceOfProductByName(String productName) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :productName";
        return this.entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("productName", productName)
                .getSingleResult();
    }
}
