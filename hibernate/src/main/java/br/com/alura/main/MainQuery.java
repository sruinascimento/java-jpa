package br.com.alura.main;

import br.com.alura.dao.CategoryDao;
import br.com.alura.dao.ProductDao;
import br.com.alura.model.Category;
import br.com.alura.model.Product;
import br.com.alura.util.JPAUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

public class MainQuery {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);
        CategoryDao categoryDao = new CategoryDao(entityManager);
//        Product product = productDao.getProductById(4l);
//        System.out.println(product.getName());
//        System.out.println(product.getPrice());
//        List<Product> products = productDao.getProducts();
//        products.forEach(System.out::println);
//        List<Category> categories = categoryDao.getCategories();
//        categories.forEach(System.out::println);
        /*List<Product> products = productDao.getProductByName("Xiaomi Poco x5 pro");
        products.forEach(System.out::println);*/

//        List<Product> products = productDao.getProductByCategoryName("cellphone");
//        products.forEach(System.out::println);
        List<BigDecimal> prices = productDao.getPriceOfProduct();
        prices.forEach(System.out::println);
        System.out.println("Preço " + productDao.getPriceOfProductByName("Legion5"));
    }
}
