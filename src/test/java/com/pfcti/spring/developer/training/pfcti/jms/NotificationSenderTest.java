package com.pfcti.spring.developer.training.pfcti.jms;

import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.service.CuentaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NotificationSenderTest {
    @Autowired
    private CuentaService cuentaService;

    @Test
    void creacionDeCuentaYNotificacion() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        cuentaDto.setNumero("4788987255");
        cuentaDto.setTipo("AHO");
        cuentaDto.setClienteId(1);
        this.cuentaService.creacionDeCuentaYNotificacion(cuentaDto);
    }

    @Test
    void crearCuentaPubSub() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        cuentaDto.setNumero("4788987255");
        cuentaDto.setTipo("AHO");
        cuentaDto.setClienteId(1);
        cuentaService.creacionDeCuentaPublishSub(cuentaDto);
    }
}
