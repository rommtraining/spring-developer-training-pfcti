package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteQueryDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaQueryDto;

import java.util.List;

public interface BuscadorCuentas {
    List<CuentaDto> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto);
}
