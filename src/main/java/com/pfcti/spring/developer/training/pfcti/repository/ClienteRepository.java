package com.pfcti.spring.developer.training.pfcti.repository;

import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {
    List<Cliente> findClientesByPaisAndCuentas_EstadoIsTrue(String pais);

    void deleteClienteById(int clienteId);

    @Query(value = "select c from Cliente c where c.apellidos = :apellidos")
    List<Cliente> buscarPorApellidos(String apellidos);

    @Query(value = "select id, nombre, apellidos, cedula, telefono from TCliente where apellidos = :apellidos", nativeQuery = true)
    List<Tuple> buscarPorApellidosNativo(String apellidos);

    @Modifying
    @Query(value = "update Cliente c set c.nombre = :nombre where c.apellidos = :apellidos")
    void actualizarClientePorQuery(String nombre, String apellidos);

    List<Cliente> findByApellidosAndAndNombre(String apellidos, String nombre);

    List<Cliente> findByPaisIsNotAndTarjetas_EstadoIsFalse(String pais);

    List<Cliente> findClientesByCedula(String cedula);

    List<Cliente> findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre, String apellidos);
}
