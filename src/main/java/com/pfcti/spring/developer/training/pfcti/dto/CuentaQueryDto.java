package com.pfcti.spring.developer.training.pfcti.dto;

import com.pfcti.spring.developer.training.pfcti.dto.enums.CuentaQueryType;
import lombok.Data;

@Data
public class CuentaQueryDto {
    private String textoBusqueda;
    private CuentaQueryType cuentaQueryType;
}
