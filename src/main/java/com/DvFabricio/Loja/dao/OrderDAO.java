package com.DvFabricio.Loja.dao;

import com.DvFabricio.Loja.Vo.SalesReportVo;
import com.DvFabricio.Loja.model.Order;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDAO {

    private EntityManager em;

    public void register(Order order) {
        this.em.persist(order);

    }

    public BigDecimal totalValueSold() {
        String jpql = "SELECT SUM(p.totalValue) FROM Order p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<SalesReportVo> salesReport() {
        String jpql = "SELECT new com.DvFabricio.Loja.Vo.SalesReportVo("
                + "product.name, "
                + "SUM(item.quantity), "
                + "MAX(order.date)) "
                + "FROM Order order "
                + "JOIN order.items item "
                + "JOIN item.product product "
                + "GROUP BY product.name "
                + "ORDER BY SUM(item.quantity) DESC";

        return em.createQuery(jpql, SalesReportVo.class)
                .getResultList();
    }

    public Order findOrderWithClient(Long id) {
        return em.createQuery("SELECT p FROM Order p JOIN FETCH p.client WHERE p.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }


}
