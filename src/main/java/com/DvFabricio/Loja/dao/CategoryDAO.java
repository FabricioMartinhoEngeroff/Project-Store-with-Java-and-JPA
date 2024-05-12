package com.DvFabricio.Loja.dao;

import com.DvFabricio.Loja.model.Category;
import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryDAO {

    private EntityManager em;

    public void register(Category category){
        this.em.persist(category);
    }

    public void update(Category category) {
        this.em.merge(category);
    }

    public void remove(Category category) {
        category = em.merge(category);
        this.em.remove(category);
    }


}
