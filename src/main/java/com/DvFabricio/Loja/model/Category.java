package com.DvFabricio.Loja.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @EmbeddedId
    private CategoryID categoryID;

    public Category(String name){
        this.categoryID = new CategoryID(name, "XPTO");
    }

    public String getName(){
        return this.categoryID.getName();
    }
}
