package com.DvFabricio.Loja.test;

import com.DvFabricio.Loja.Util.JPAUtil;
import com.DvFabricio.Loja.Vo.SalesReportVo;
import com.DvFabricio.Loja.dao.CategoryDAO;
import com.DvFabricio.Loja.dao.ClientDAO;
import com.DvFabricio.Loja.dao.OrderDAO;
import com.DvFabricio.Loja.dao.ProductDAO;
import com.DvFabricio.Loja.model.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderRegistration {

    public static void main(String[] args) {
        populateDatabase();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        ClientDAO clientDao = new ClientDAO(em);

        Product product1 = productDao.findById(1l);
        Product product2 = productDao.findById(2l);
        Product product3 = productDao.findById(3l);
        Client client = clientDao.findById(1l);

        em.getTransaction().begin();

        Order order1 = new Order(client);
        order1.addItem(new OrderItem(10, order1, product1));
        order1.addItem(new OrderItem(40, order1, product2));

        Order order2 = new Order(client);
        order2.addItem(new OrderItem(2, order2, product3));

        OrderDAO orderDAO = new OrderDAO(em);
        orderDAO.register(order1);
        orderDAO.register(order2);

        em.getTransaction().commit();

        BigDecimal soldTotal = orderDAO.totalValueSold();
        System.out.println("TOTAL VALUE: " + soldTotal);

        List<SalesReportVo> report = orderDAO.salesReport();
        report.forEach(System.out::println);
    }

    private static void populateDatabase() {
        Category cellphones = new Category("CELL_PHONE");
        Category games = new Category("PLAYGAMES");
        Category computers = new Category("COMPUTERS");

        Product cellPhone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones, LocalDate.of(2024, 5, 2));
        Product game = new Product("PS5", "Playstation 5", new BigDecimal("1500"), games, LocalDate.of(2024, 5, 2));
        Product computer = new Product("PC Gamer", "Potente para jogos", new BigDecimal("3000"), computers, LocalDate.of(2024, 5, 2));

        Client client = new Client("Jonas", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        CategoryDAO categoryDao = new CategoryDAO(em);
        ClientDAO clientDao = new ClientDAO(em);

        em.getTransaction().begin();

        categoryDao.register(cellphones);
        categoryDao.register(games);
        categoryDao.register(computers);

        productDao.register(cellPhone);
        productDao.register(game);
        productDao.register(computer);

        clientDao.register(client);

        em.getTransaction().commit();
        em.close();

    }

}

