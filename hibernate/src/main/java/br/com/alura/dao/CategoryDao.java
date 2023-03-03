package br.com.alura.dao;

import br.com.alura.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void delete(Category category) {
        this.entityManager.remove(this.entityManager.merge(category));
    }

    public List<Category> getCategories() {
        String jpql = "SELECT c FROM Category c";
        return this.entityManager.createQuery(jpql, Category.class).getResultList();
    }


}
