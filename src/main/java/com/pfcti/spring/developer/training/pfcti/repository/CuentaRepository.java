package com.pfcti.spring.developer.training.pfcti.repository;

import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {
    void deleteAllByCliente_Id(int clienteId);

    void updateCuentaByCliente_Id(int clienteId);

    void updateCuenta_EstadoByCliente_Id(boolean estado, int clienteId);

}
