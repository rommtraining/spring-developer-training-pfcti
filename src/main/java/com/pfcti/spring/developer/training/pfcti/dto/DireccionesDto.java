package com.pfcti.spring.developer.training.pfcti.dto;

import lombok.Data;

@Data
public class DireccionesDto {
    private int id;
    private String direccion;
    private String nomenclatura;
    private int clienteId;

}
