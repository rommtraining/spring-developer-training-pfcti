package com.pfcti.spring.developer.training.pfcti.beans;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaQueryDto;
import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import com.pfcti.spring.developer.training.pfcti.dto.enums.CuentaQueryType;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import com.pfcti.spring.developer.training.pfcti.repository.CuentaRepository;
import com.pfcti.spring.developer.training.pfcti.service.CuentaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("baseDeDatosCuentas")
public class BuscadorCuentasBdd implements BuscadorCuentas {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaDto> obtenerListaCuentas(CuentaQueryDto cuentaQueryDto) {
        List<Cuenta> cuentas = null;
        if(CuentaQueryType.NUMERO.equals(cuentaQueryDto.getCuentaQueryType()) ||
                CuentaQueryType.TIPO.equals(cuentaQueryDto.getCuentaQueryType())) {
            cuentas = cuentaRepository.findCuentasByNumeroContainingIgnoreCaseOrTipoContainingIgnoreCase(cuentaQueryDto.getTextoBusqueda(), cuentaQueryDto.getTextoBusqueda());
        }else if (CuentaQueryType.ESTADO.equals(cuentaQueryDto.getCuentaQueryType())) {
            cuentas = cuentaRepository.findAllByEstado(Boolean.parseBoolean(cuentaQueryDto.getTextoBusqueda()));
        } else if (CuentaQueryType.CLIENTE.equals(cuentaQueryDto.getCuentaQueryType())) {
            cuentas = cuentaRepository.findAllByCliente_Id(Integer.parseInt((cuentaQueryDto.getTextoBusqueda())));
        }

        List<CuentaDto> clientesDto = new ArrayList<>();

        cuentas.forEach(cuenta -> {
            clientesDto.add(deCuentaACuentaDto(cuenta));
        });

        return clientesDto;
    }

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
}
