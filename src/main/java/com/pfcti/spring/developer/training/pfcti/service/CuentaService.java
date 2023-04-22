package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.criteria.CuentaSpecification;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import com.pfcti.spring.developer.training.pfcti.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;

    private Cuenta deCuentaDtoACuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();

        BeanUtils.copyProperties(cuentaDto, cuenta);

        return cuenta;
    }

    private CuentaDto deCuentaACuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();

        BeanUtils.copyProperties(cuenta, cuentaDto);

        return cuentaDto;
    }

    public List<CuentaDto> buscarDinamicamentePorCriterios(CuentaDto cuentaDto) {
        return cuentaRepository
                .findAll(cuentaSpecification.buildFilter(cuentaDto))
                .stream()
                .map(this::deCuentaACuentaDto)
                .collect(Collectors.toList());
    }

}
