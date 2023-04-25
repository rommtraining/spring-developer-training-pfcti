package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteQueryDto;
import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdministradorClientesAsBeanTest {
    @Autowired
    private AdministradorClientes defaultNombres;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes administradorClientes;

    @Test
    void obtenerListaClientesPorCriterio() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRE);
        clienteQueryDto.setTextoBusqueda("ROBERTO");

        List<ClienteDto> clientesDto = defaultNombres.obtenerListaClientesPorCriterio(clienteQueryDto);
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clientesDto.size() == 1);
    }

    @Test
    void obtenerListaClientesPorCriterioConAnotacion() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRE);
        clienteQueryDto.setTextoBusqueda("ROBERTO");

        List<ClienteDto> clientesDto = administradorClientes.obtenerListaClientesPorCriterio(clienteQueryDto);
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clientesDto.size() == 1);
    }
}
