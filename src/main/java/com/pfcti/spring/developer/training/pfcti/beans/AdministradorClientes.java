package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteQueryDto;
import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import com.pfcti.spring.developer.training.pfcti.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AdministradorClientes {

    private ClienteRepository clienteRepository;
    private ClienteQueryType defaultClienteQueryType;

    public AdministradorClientes(ClienteRepository clienteRepository, ClienteQueryType defaultClienteQueryType) {
        System.out.println("AdministradorClientes Instancia: " + this);
        this.clienteRepository = clienteRepository;
        this.defaultClienteQueryType = defaultClienteQueryType;
    }

    public List<ClienteDto> obtenerListaClientesPorCriterio(ClienteQueryDto clienteQueryDto){
        List<Cliente> clientes = null;
        if(ClienteQueryType.CEDULA.equals(clienteQueryDto.getClienteQueryType())) {
            clientes = clienteRepository.findClientesByCedula(clienteQueryDto.getTextoBusqueda());
        }else if (ClienteQueryType.NOMBRE.equals(clienteQueryDto.getClienteQueryType())) {
            clientes = clienteRepository.findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(), clienteQueryDto.getTextoBusqueda());
        }
        List<ClienteDto> clientesDto = new ArrayList<>();

        clientes.forEach(cliente -> {
            clientesDto.add(deClienteAClienteDto(cliente));
        });

        return clientesDto;
    }

    private ClienteDto deClienteAClienteDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();

        BeanUtils.copyProperties(cliente, clienteDto);

        return clienteDto;
    }
}
