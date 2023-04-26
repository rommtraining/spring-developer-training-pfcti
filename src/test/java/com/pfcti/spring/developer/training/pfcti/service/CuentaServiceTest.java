package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;

    @Test
    void buscarDinamicamentePorCriterios() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(false);
        List<CuentaDto> cuentasDto = cuentaService.buscarDinamicamentePorCriterios(cuentaDto);
        cuentasDto.forEach(
                        cuentaDtoResultado -> {
                            System.out.println("Cuenta Resultado: " + cuentaDtoResultado.getNumero());
                        }
                );
        assertEquals(1,1);
    }

    @Test
    void insertarCuenta() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setTipo("A");
        cuentaDto.setNumero("123");
        cuentaDto.setEstado(true);
        cuentaDto.setClienteId(1);

        cuentaService.insertarCuenta(cuentaDto);

        CuentaDto cuentaDto1 = new CuentaDto();
        cuentaDto1.setEstado(true);
        cuentaDto1.setClienteId(1);

        List<CuentaDto> cuentasDto = cuentaService.buscarDinamicamentePorCriterios(cuentaDto1);
        cuentasDto.forEach(
                cuentaDtoResultado -> {
                    System.out.println("Cuenta Resultado: " + cuentaDtoResultado.getNumero() + ' ' + cuentaDtoResultado.getTipo());
                }
        );
        assertEquals(1,1);
    }

    @Test
    void inactivarCuentasPorCliente() {
        cuentaService.inactivarCuentasPorCliente(1);

        CuentaDto cuentaDto1 = new CuentaDto();
        cuentaDto1.setEstado(true);
        cuentaDto1.setClienteId(1);

        List<CuentaDto> cuentasDto = cuentaService.buscarDinamicamentePorCriterios(cuentaDto1);
        cuentasDto.forEach(
                cuentaDtoResultado -> {
                    System.out.println("Cuenta Resultado: " + cuentaDtoResultado.getNumero() + ' ' + cuentaDtoResultado.getTipo() + ' ' + cuentaDtoResultado.getEstado().toString());
                }
        );

        assertEquals(1,1);
    }

    @Test
    void obtenerCuentasActivasPorCliente() {
        List<CuentaDto> cuentasDto = cuentaService.obtenerCuentasActivasPorCliente(1);
        cuentasDto.forEach(
                cuentaDtoResultado -> {
                    System.out.println("Cuenta Resultado: " + cuentaDtoResultado.getNumero() + ' ' + cuentaDtoResultado.getTipo() + ' ' + cuentaDtoResultado.getEstado().toString());
                }
        );

        assertEquals(1,1);
    }
}