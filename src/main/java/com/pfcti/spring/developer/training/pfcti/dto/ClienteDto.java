package com.pfcti.spring.developer.training.pfcti.dto;

import com.pfcti.spring.developer.training.pfcti.model.Tarjeta;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String pais;
    private String telefono;
    private List<DireccionesDto> direcciones;
    private List<CuentaDto> cuentas;
    private List<TarjetaDto> tarjetas;
}
