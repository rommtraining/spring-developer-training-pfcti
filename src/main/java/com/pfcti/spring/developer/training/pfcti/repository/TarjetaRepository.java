package com.pfcti.spring.developer.training.pfcti.repository;

import com.pfcti.spring.developer.training.pfcti.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
}