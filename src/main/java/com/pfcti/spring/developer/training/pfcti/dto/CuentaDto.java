package com.pfcti.spring.developer.training.pfcti.dto;

import lombok.Data;
@Data
public class CuentaDto {

    private int id;
    private String direccion;
    private String nomenclatura;
    private boolean estado;
    private int clienteId;
}
