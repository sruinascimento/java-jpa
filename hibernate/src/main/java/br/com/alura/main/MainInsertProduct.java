package br.com.alura.main;

import br.com.alura.dao.ProductDao;
import br.com.alura.model.Product;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainInsertProduct {
    public static void main(String[] args) {
        Product cellphone = new Product();
        cellphone.setName("Xiaomi Poco x5 pro");
        cellphone.setDescription("Ram 8GB, Memory 256GB");
        cellphone.setPrice(new BigDecimal("2199"));


        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);
        //iniciar as transações
        entityManager.getTransaction().begin();
        productDao.insert(cellphone);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
