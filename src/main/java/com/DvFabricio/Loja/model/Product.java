package com.DvFabricio.Loja.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate registerDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    private List<OrderItem> item = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Category category, LocalDate registerDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.registerDate = registerDate;
    }
}
