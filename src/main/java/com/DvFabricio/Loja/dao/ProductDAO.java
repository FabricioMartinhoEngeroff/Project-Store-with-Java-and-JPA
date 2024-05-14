package com.DvFabricio.Loja.dao;

import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        String jpql = "SELECT p FROM Product p WHERE p.category.categoryID.name = :name";
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

    public List<Product> findByParameter(String name, BigDecimal price, LocalDate registerDate) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql = " AND p.nome = :nome ";
        }
        if (price != null) {
            jpql = " AND p.preco = :preco ";
        }
        if (registerDate != null) {
            jpql = " AND p.dataCadastro = :dataCadastro ";
        }
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("nome", name);
        }
        if (price != null) {
            query.setParameter("preco", price);
        }
        if (registerDate != null) {
            query.setParameter("dataCadastro", registerDate);
        }

        return query.getResultList();
    }

    public List<Product> findByParametersWithCriteria(String name, BigDecimal price, LocalDate registerDate) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filtros = builder.and();
        if (name != null && !name.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), name));
        }
        if (price != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), price));
        }
        if (registerDate != null) {
            filtros = builder.and(filtros, builder.equal(from.get("registerDate"), registerDate));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }


}
