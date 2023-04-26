package com.pfcti.spring.developer.training.pfcti.api;

import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/cuenta")
public class CuentaApi {
    @Autowired
    private CuentaService cuentaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertarCuenta(@Valid @RequestBody CuentaDto cuentaDto){
        log.info("Busqueda de cliente Parameter: {}", cuentaDto);
        cuentaService.insertarCuenta(cuentaDto);
    }

    @DeleteMapping(value = "/cuentas")
    public void inactivarCuentasCliente(@RequestParam int clienteId) {
        cuentaService.inactivarCuentaCliente(clienteId);
    }
}
