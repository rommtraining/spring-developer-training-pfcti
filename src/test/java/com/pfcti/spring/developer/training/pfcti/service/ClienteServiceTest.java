package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void insertarCliente() {
        List<Cliente> listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println("Lista antes de insertar " + listaClientes.size());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("Morera");
        clienteDto.setCedula("123456789");
        clienteDto.setNombre("Roger");
        clienteDto.setTelefono("22222222");

        clienteService.insertarCliente(clienteDto);

        listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println("Lista despues de insertar " + listaClientes.size());

        assertEquals(listaClientes.size(),3);
    }

    @Test
    void obtenerCliente() {
        ClienteDto clienteDto = clienteService.obtenerCliente(1);
        System.out.println("Apellido: " + clienteDto.getApellidos());
        assertEquals(clienteDto.getId(),1);
    }

    @Test
    void actualizarCliente() {
        ClienteDto clienteDtoInicial = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial " + clienteDtoInicial.getApellidos());
        clienteDtoInicial.setApellidos("Morera");
        clienteService.actualizarCliente(clienteDtoInicial);

        ClienteDto clienteDtoDespues = clienteService.obtenerCliente(1);
        System.out.println("El cliente final " + clienteDtoInicial.getApellidos());

        assertEquals(clienteDtoInicial.getApellidos(),"Morera");
    }

    @Test
    void obtenerClientes() {
        List<ClienteDto> clientesDto = clienteService.obtenerClientes();
        clientesDto.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));

        assertEquals(2,clientesDto.size());
    }

    @Test
    void obtenerClientesPorCodigoIsoPaisYCuentasActivas() {
        List<ClienteDto> clientesDto = clienteService.obtenerClientesPorCodigoIsoPaisYCuentasActivas("CR");
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertEquals(1, clientesDto.size());
    }

    @Test
    void eliminarCliente() {
        clienteService.eliminarCliente(1);
        assertEquals(1,1);
    }

    @Test
    void buscarPorApellidos() {
        List<ClienteDto> clientesDto = clienteService.buscarPorApellidos("SANCHEZ");
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertEquals(1,clientesDto.size());
    }

    @Test
    void buscarPorApellidosQueryNativo() {
        List<ClienteDto> clientesDto = clienteService.buscarPorApellidosQueryNativo("SANCHEZ");
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertEquals(1,clientesDto.size());
    }

    @Test
    void actualizarClientePorQuery() {
        ClienteDto clienteDtoOriginal = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("Nombre original " + clienteDtoOriginal.getNombre());

        clienteService.actualizarClientePorQuery("Maria", "SANCHEZ");

        ClienteDto clienteDtoDespues = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("Nombre despues " + clienteDtoDespues.getNombre());

        assertNotEquals(clienteDtoOriginal.getNombre(), clienteDtoDespues.getNombre());
    }

    @Test
    void buscarPorApellidosYNombre() {
        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidosYNombre("SANCHEZ", "RAUL");
        System.out.println("Cliente encontrado " + clienteDtos.get(0).getApellidos());
        assertFalse(clienteDtos.isEmpty());
        assertEquals("SANCHEZ", clienteDtos.get(0).getApellidos());
    }

    @Test
    void buscarClientesExtranjerosConTarjetasInactivas() {
        List<ClienteDto> clienteDtos = clienteService.buscarClientesExtranjerosConTarjetasInactivas("CR");
        assertFalse(clienteDtos.isEmpty());
        System.out.println("Clientes extranjeros con tarjetas inactivas: " + clienteDtos.get(0).getApellidos());
        assertEquals("PEREZ", clienteDtos.get(0).getApellidos());
    }

    @Test
    void buscarDinamicamentePorCriterios() {

        List<ClienteDto> clientesDto = clienteService.buscarDinamicamentePorCriterios(new ClienteDto());
        assertFalse(clientesDto.isEmpty());
        clientesDto.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clientesDto.size() >= 2);

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clientesDto = clienteService.buscarDinamicamentePorCriterios(clienteDto);
        clientesDto.forEach(clienteDto1 -> {
            System.out.println("Cliente: " + clienteDto1.getApellidos());
        });
        assertTrue(clientesDto.size() == 3);

        ClienteDto clienteDto2 = new ClienteDto();
        clienteDto2.setApellidos("SANCHEZ");
        clienteDto2.setNombre("MARIA");
        clientesDto = clienteService.buscarDinamicamentePorCriterios(clienteDto2);
        clientesDto.forEach(clienteDto1 -> {
            System.out.println("Cliente: " + clienteDto1.getApellidos());
        });
        assertTrue(clientesDto.size() == 1);

        ClienteDto clienteDto3 = new ClienteDto();
        clienteDto3.setApellidos("SANCHEZ");
        clienteDto3.setCedula("3");
        clientesDto = clienteService.buscarDinamicamentePorCriterios(clienteDto3);
        clientesDto.forEach(clienteDto1 -> {
            System.out.println("Cliente: " + clienteDto1.getApellidos());
        });
        assertTrue(clientesDto.size() == 1);

        ClienteDto clienteDto4 = new ClienteDto();
        clienteDto4.setApellidos("SANCHEZ");
        clienteDto4.setNombre("MARIA");
        clienteDto4.setCedula("3");
        clientesDto = clienteService.buscarDinamicamentePorCriterios(clienteDto4);
        clientesDto.forEach(clienteDto1 -> {
            System.out.println("Cliente: " + clienteDto1.getApellidos());
        });
        assertTrue(clientesDto.size() == 1);
    }


}