package com.DvFabricio.Loja.test;

import com.DvFabricio.Loja.Util.JPAUtil;
import com.DvFabricio.Loja.dao.CategoryDAO;
import com.DvFabricio.Loja.dao.OrderDAO;
import com.DvFabricio.Loja.dao.ProductDAO;
import com.DvFabricio.Loja.model.Category;
import com.DvFabricio.Loja.model.Order;
import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CriteriaTest {

    public static void main(String[] args) {
        populateDatabase();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);
        List<Product> products = productDAO.findByParametersWithCriteria(null, null, LocalDate.now());
        products.forEach(p -> System.out.println(p.getName()));
    }

    private static void populateDatabase() {
        Category cellphones = new Category("CELLPHONES");
        Category videogames = new Category("VIDEOGAMES");
        Category computers = new Category("COMPUTERS");

        Product cellphone = new Product("Xiaomi Redmi", "Very cool", new BigDecimal("800"), cellphones, LocalDate.of(2024, 5, 2));
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("8000"), videogames, LocalDate.of(2024, 5, 2));
        Product macbook = new Product("Macbook", "Macbook pro retina", new BigDecimal("14000"), computers, LocalDate.of(2024, 5, 2));


        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        CategoryDAO categoryDao = new CategoryDAO(em);


        em.getTransaction().begin();

        categoryDao.register(cellphones);
        categoryDao.register(videogames);
        categoryDao.register(computers);

        productDao.register(cellphone);
        productDao.register(videogame);
        productDao.register(macbook);

        em.getTransaction().commit();
        em.close();
    }
}
