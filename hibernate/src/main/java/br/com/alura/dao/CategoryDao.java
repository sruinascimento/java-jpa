package br.com.alura.dao;

import br.com.alura.model.Category;

import javax.persistence.EntityManager;

public class CategoryDao {
    private EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Category category) {
        this.entityManager.persist(category);
    }
    public void update(Category category) {
        this.entityManager.merge(category);
    }
}
