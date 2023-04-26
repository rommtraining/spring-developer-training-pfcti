package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaQueryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sistemaExterno")
public class BuscadorCuentasSistemaExterno implements BuscadorCuentas {
    @Override
    public List<CuentaDto> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto) {
        return new ArrayList<>();
    }
}
