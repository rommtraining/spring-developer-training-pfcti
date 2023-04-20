package com.pfcti.spring.developer.training.pfcti.repository;

import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
