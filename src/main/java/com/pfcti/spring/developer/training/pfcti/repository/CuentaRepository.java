package com.pfcti.spring.developer.training.pfcti.repository;

import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {
    void deleteAllByCliente_Id(int clienteId);

    @Modifying
    @Query("update Cuenta c set c.estado = false where c.cliente.id = :clienteId and c.estado")
    void inactivarCuentasPorCliente(Integer clienteId);

    List<Cuenta> findAllByCliente_IdAndEstadoIsTrue(int clienteId);

    List<Cuenta> findCuentasByNumeroContainingIgnoreCaseOrTipoContainingIgnoreCase(String numero, String tipo);

    List<Cuenta> findAllByEstado(Boolean estado);

    List<Cuenta> findAllByCliente_Id(int clienteId);

}
