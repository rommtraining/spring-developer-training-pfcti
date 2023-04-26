package com.pfcti.spring.developer.training.pfcti.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CuentaDto {

    private int id;
    @NotNull
    @Length(max = 10)
    private String numero;
    private String tipo;
    private Boolean estado;
    private int clienteId;
}
