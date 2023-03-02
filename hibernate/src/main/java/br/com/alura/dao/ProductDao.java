package br.com.alura.dao;

import br.com.alura.model.Product;

import javax.persistence.EntityManager;

public class ProductDao {

    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Product product) {
        this.entityManager.persist(product);
    }

}
