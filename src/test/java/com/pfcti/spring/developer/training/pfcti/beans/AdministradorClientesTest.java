package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteQueryDto;
import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import com.pfcti.spring.developer.training.pfcti.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministradorClientesTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    void obtenerListaClientesPorCriterio() {
        AdministradorClientes administradorClientes = new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRE);
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