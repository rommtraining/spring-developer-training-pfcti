package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.criteria.ClienteSpecification;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import com.pfcti.spring.developer.training.pfcti.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;
    private TarjetaRepository tarjetaRepository;
    private InversionRepository inversionRepository;

    private ClienteSpecification clienteSpecification;

    public void insertarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);
    }

    public ClienteDto obtenerCliente(int clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                        .orElseThrow(() -> {
                                throw new RuntimeException("Cliente no existe");
                            }
                        );

        return deClienteAClienteDto(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findById(clienteDto.getId())
                .orElseThrow(() -> {
                            throw new RuntimeException("Cliente no existe");
                        }
                );
        /*
        cliente.setCedula(clienteDto.getCedula());
        cliente.setId(clienteDto.getId());
        cliente.setTelefono(cliente.getTelefono());
        cliente.setApellidos(cliente.getApellidos());
        cliente.setNombre(cliente.getNombre());
        */

        clienteRepository.save(deClienteDtoACliente(clienteDto));
    }

    public List<ClienteDto> obtenerClientes() {

        List<ClienteDto> clientesDto = new ArrayList<>();

        List<Cliente> clientes = clienteRepository.findAll();
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

    private Cliente deClienteDtoACliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();

        BeanUtils.copyProperties(clienteDto, cliente);

        return cliente;
    }

    public List<ClienteDto> obtenerClientesPorCodigoIsoPaisYCuentasActivas(String codigoIsoPais) {
        List<ClienteDto> clienteDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisAndCuentas_EstadoIsTrue(codigoIsoPais);
        clientes.forEach(cliente -> {
            clienteDto.add(deClienteAClienteDto(cliente));
        });

        return clienteDto;
    }

    public void eliminarCliente(Integer clienteId) {

        direccionRepository.deleteAllByCliente_Id(clienteId);
        tarjetaRepository.deleteAllByCliente_Id(clienteId);
        inversionRepository.deleteAllByCliente_Id(clienteId);
        cuentaRepository.deleteAllByCliente_Id(clienteId);
        clienteRepository.deleteClienteById(clienteId);
    }

    public List<ClienteDto> buscarPorApellidos(String apellidos) {
        List<ClienteDto> clientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.buscarPorApellidos(apellidos);
        clientes.forEach(cliente -> {
            clientesDto.add(deClienteAClienteDto(cliente));
        });
        return clientesDto;
    }

    public List<ClienteDto> buscarPorApellidosQueryNativo(String apellidos) {
        List<ClienteDto> clientesDto = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosNativo(apellidos);
        tuples.forEach(tuple -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String)tuple.get("apellidos"));
            clienteDto.setId((int)tuple.get("id"));
            clienteDto.setCedula((String)tuple.get("cedula"));
            clienteDto.setNombre((String)tuple.get("nombre"));
            clienteDto.setTelefono((String)tuple.get("telefono"));
            clientesDto.add(clienteDto);
        });
        return clientesDto;
    }

    public void actualizarClientePorQuery(String nombre, String apellidos) {
        clienteRepository.actualizarClientePorQuery(nombre, apellidos);
    }

    public List<ClienteDto> buscarPorApellidosYNombre(String apellidos, String nombre) {
        return clienteRepository
                .findByApellidosAndAndNombre(apellidos, nombre)
                .stream()
                .map(this::deClienteAClienteDto)
                .collect(Collectors.toList());
    }

    public List<ClienteDto> buscarClientesExtranjerosConTarjetasInactivas(String pais) {
        return clienteRepository
                .findByPaisIsNotAndTarjetas_EstadoIsFalse(pais)
                .stream()
                .map(this::deClienteAClienteDto)
                .collect(Collectors.toList());
    }

    public List<ClienteDto> buscarDinamicamentePorCriterios(ClienteDto clienteDto) {
        return clienteRepository
                .findAll(clienteSpecification.buildFilter(clienteDto))
                .stream()
                .map(this::deClienteAClienteDto)
                .collect(Collectors.toList());
    }


}
