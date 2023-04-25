package com.pfcti.spring.developer.training.pfcti.dto;

import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import lombok.Data;

@Data
public class ClienteQueryDto {
    private String textoBusqueda;
    private ClienteQueryType clienteQueryType;
}
