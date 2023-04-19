package com.pfcti.spring.developer.training.pfcti.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private String tipo;
    @ManyToOne()
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Cliente cliente;
}
