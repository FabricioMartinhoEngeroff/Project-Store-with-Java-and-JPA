package com.DvFabricio.Loja.test;

import com.DvFabricio.Loja.Util.JPAUtil;
import com.DvFabricio.Loja.dao.CategoryDAO;
import com.DvFabricio.Loja.dao.ClientDAO;
import com.DvFabricio.Loja.dao.OrderDAO;
import com.DvFabricio.Loja.dao.ProductDAO;
import com.DvFabricio.Loja.model.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PerformanceConsultations {

    public static void main(String[] args) {
        populateDatabase();
        EntityManager em = JPAUtil.getEntityManager();
        OrderDAO orderDAO = new  OrderDAO(em);
        Order order = orderDAO.findOrderWithClient(1l);
        em.close();
        System.out.println(order.getClient().getName());
    }

    private static void populateDatabase() {
        Category cellphones = new Category("CELLPHONES");
        Category videogames = new Category("VIDEOGAMES");
        Category computers = new Category("COMPUTERS");

        Product cellphone = new Product("Xiaomi Redmi", "Very cool", new BigDecimal("800"), cellphones, LocalDate.of(2024, 5, 2));
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("8000"), videogames, LocalDate.of(2024, 5, 2));
        Product macbook = new Product("Macbook", "Macbook pro retina", new BigDecimal("14000"), computers, LocalDate.of(2024, 5, 2));

        Client client = new Client("Rodrigo", "123456");

        Order order = new Order(client);
        order.addItem(new OrderItem(10, order, cellphone));
        order.addItem(new OrderItem(40, order, videogame));

        Order order2 = new Order(client);
        order2.addItem(new OrderItem(2, order2, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        CategoryDAO categoryDao = new CategoryDAO(em);
        ClientDAO clientDao = new ClientDAO(em);
        OrderDAO orderDao = new OrderDAO(em);

        em.getTransaction().begin();

        categoryDao.register(cellphones);
        categoryDao.register(videogames);
        categoryDao.register(computers);

        productDao.register(cellphone);
        productDao.register(videogame);
        productDao.register(macbook);

        clientDao.register(client);

        orderDao.register(order);
        orderDao.register(order2);

        em.getTransaction().commit();
        em.close();
    }

}
