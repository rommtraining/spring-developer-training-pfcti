package com.pfcti.spring.developer.training.pfcti.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String direccion;
    @Column
    private String nomenclatura;

    @ManyToOne
    @JoinColumn(name="client_Id", referencedColumnName = "id")
    private Cliente cliente;
}
