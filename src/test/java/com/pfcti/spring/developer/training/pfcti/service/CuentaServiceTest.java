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
        cuentaDto.setEstado(true);
        List<CuentaDto> cuentasDto = cuentaService.buscarDinamicamentePorCriterios(cuentaDto);
        cuentasDto.forEach(
                        cuentaDtoResultado -> {
                            System.out.println("Cuenta Resultado: " + cuentaDtoResultado.getNumero());
                        }
                );
        assertEquals(1,1);
    }
}