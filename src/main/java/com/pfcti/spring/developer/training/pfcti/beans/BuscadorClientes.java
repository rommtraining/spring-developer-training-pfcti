package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto);
}
