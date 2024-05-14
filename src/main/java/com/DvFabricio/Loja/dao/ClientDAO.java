package com.DvFabricio.Loja.dao;

import com.DvFabricio.Loja.model.Client;
import com.DvFabricio.Loja.model.Order;
import com.DvFabricio.Loja.model.Product;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ClientDAO {

    private EntityManager em;

    public void register(Client client) {
        this.em.persist(client);

    }

    public Client findById(Long id) {
        return em.find(Client.class, 1);
    }

}
