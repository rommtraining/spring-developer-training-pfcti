package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.criteria.CuentaSpecification;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import com.pfcti.spring.developer.training.pfcti.repository.ClienteRepository;
import com.pfcti.spring.developer.training.pfcti.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;
    private ClienteRepository clienteRepository;

    public void insertarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setCliente(clienteRepository.getById(cuentaDto.getClienteId()));
        cuentaRepository.save(cuenta);
    }

    public void inactivarCuentasPorCliente(int clienteId) {
        cuentaRepository.inactivarCuentasPorCliente(clienteId);
    }

    private Cuenta deCuentaDtoACuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();

        BeanUtils.copyProperties(cuentaDto, cuenta);

        return cuenta;
    }

    private CuentaDto deCuentaACuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();

        BeanUtils.copyProperties(cuenta, cuentaDto);
        cuentaDto.setClienteId(cuenta.getCliente().getId());
        return cuentaDto;
    }

    public List<CuentaDto> buscarDinamicamentePorCriterios(CuentaDto cuentaDto) {
        return cuentaRepository
                .findAll(cuentaSpecification.buildFilter(cuentaDto))
                .stream()
                .map(this::deCuentaACuentaDto)
                .collect(Collectors.toList());
    }

    public List<CuentaDto> obtenerCuentasActivasPorCliente(int clienteId) {
        return cuentaRepository
                .findAllByCliente_IdAndEstadoIsTrue(clienteId)
                .stream()
                .map(this::deCuentaACuentaDto)
                .collect(Collectors.toList());
    }

}
