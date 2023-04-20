package com.pfcti.spring.developer.training.pfcti.dto;

import lombok.Data;

@Data
public class TarjetaDto {

    private int id;
    private String direccion;
    private String nomenclatura;
    private int clienteId;
}
