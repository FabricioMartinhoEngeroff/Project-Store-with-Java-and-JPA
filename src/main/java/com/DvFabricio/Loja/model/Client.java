package com.DvFabricio.Loja.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalData personalData;

    public Client(String name, String cpf) {
        this.personalData = new PersonalData(name, cpf);
    }

    public String getName(){
        return this.personalData.getName();
    }

    public String getCpf(){
        return this.personalData.getCpf();
    }
}
