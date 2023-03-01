package br.com.alura.main;

import br.com.alura.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class MainInsertProduct {
    public static void main(String[] args) {
        Product cellphone = new Product();
        cellphone.setName("Xiaomi Poco x5 pro");
        cellphone.setDescription("Ram 8GB, Memory 256GB");
        cellphone.setPrice(new BigDecimal("2199"));

        //Factory passando o persistenciUnitName no arquivo persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("store");
        EntityManager entityManager = factory.createEntityManager();
        //iniciar as transações
        entityManager.getTransaction().begin();
        entityManager.persist(cellphone);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
