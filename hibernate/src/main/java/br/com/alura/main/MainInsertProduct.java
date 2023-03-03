package br.com.alura.main;

import br.com.alura.dao.CategoryDao;
import br.com.alura.dao.ProductDao;
import br.com.alura.model.Category;
import br.com.alura.model.Product;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainInsertProduct {
    public static void main(String[] args) {
        Category categoryCellphone = new Category("Eletronic");
        Product cellphone = new Product("Xiaomi Poco x5 pro", "Ram 8GB, Memory 256GB", new BigDecimal("2199"), categoryCellphone);

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(entityManager);
        ProductDao productDao = new ProductDao(entityManager);
        //iniciar as transações
        entityManager.getTransaction().begin();
        categoryDao.insert(categoryCellphone);
        entityManager.flush();
        categoryDao.delete(categoryCellphone);

//        productDao.insert(cellphone);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
