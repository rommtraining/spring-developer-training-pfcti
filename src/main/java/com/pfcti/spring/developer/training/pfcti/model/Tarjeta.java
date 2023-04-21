package com.pfcti.spring.developer.training.pfcti.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private String tipo;

    private boolean estado;

    @ManyToOne()
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private Cliente cliente;
}