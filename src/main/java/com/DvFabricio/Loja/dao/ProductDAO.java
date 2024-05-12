package com.DvFabricio.Loja.dao;

import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class ProductDAO {

    private EntityManager em;

    public void register(Product product) {
        this.em.persist(product);

    }

    public void update(Product product) {
        this.em.merge(product);
    }

    public void remove(Product product) {
        product = em.merge(product);
        this.em.remove(product);
    }

    public Product findById(Long id) {
        return em.find(Product.class, 1);
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findByCategoryName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Optional<BigDecimal> findByProductPriceWithName(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        List<BigDecimal> prices = em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getResultList();
        if (!prices.isEmpty()) {
            return Optional.of(prices.get(0));
        } else {
            return Optional.empty();
        }
    }

}
