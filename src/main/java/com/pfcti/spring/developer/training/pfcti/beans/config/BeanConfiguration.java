package com.pfcti.spring.developer.training.pfcti.beans.config;

import com.pfcti.spring.developer.training.pfcti.beans.AdministradorClientes;
import com.pfcti.spring.developer.training.pfcti.dto.enums.ClienteQueryType;
import com.pfcti.spring.developer.training.pfcti.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {
    @Autowired
    private ClienteRepository clienteRepository;

    @Bean({"defaultCedula", "criterioCedula"})
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClientes administradorClientesPorCedula() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRE);
    }

    @Bean({"defaultNombresSingleton"})
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClientes administradorClientesPorNombreSingletonBean() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRE);
    }

    @Bean({"defaultNombres"})
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Lazy
    public AdministradorClientes administradorClientesPorNombreBean() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRE);
    }
}
