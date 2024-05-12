package com.DvFabricio.Loja.test;

import com.DvFabricio.Loja.Util.JPAUtil;
import com.DvFabricio.Loja.dao.CategoryDAO;
import com.DvFabricio.Loja.dao.ProductDAO;
import com.DvFabricio.Loja.model.Category;
import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CadastroDeProdutos {

    public static void main(String[] args) {
        registerProduct();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(em);

        Product p = productDAO.findById(1l);
        System.out.println(p.getPrice());

        List<Product> all = productDAO.findByCategoryName("CELL_PHONE");
        all.forEach(p2 -> System.out.println(p2.getName()));

        Optional<BigDecimal> productPriceOpt = productDAO.findByProductPriceWithName("Xiaomi Redmi");
        if (productPriceOpt.isPresent()) {
            System.out.println("Preco do Produto: " + productPriceOpt.get());
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    private static void registerProduct() {
        Category cellphones = new Category("CELL_PHONE");
        Product cellPhone = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), cellphones);

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAO productDao = new ProductDAO(em);
        CategoryDAO categoryDao = new CategoryDAO(em);

        em.getTransaction().begin();

        categoryDao.register(cellphones);
        productDao.register(cellPhone);

        em.getTransaction().commit();
        em.close();
    }
}