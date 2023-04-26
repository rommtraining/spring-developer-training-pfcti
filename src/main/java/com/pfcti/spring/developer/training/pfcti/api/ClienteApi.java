package com.pfcti.spring.developer.training.pfcti.api;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/cliente")
public class ClienteApi {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto buscarCliente(@PathVariable int id) {
        log.info("Busqueda de cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ClienteDto buscarClienteXml(@PathVariable int id) {
        log.info("Busqueda de cliente XML: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/json/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClienteDto buscarClienteXmlJson(@PathVariable int id) {
        log.info("Busqueda de cliente XMLJSON: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/parameter")
    public ClienteDto buscarClienteParameter(@RequestParam int id){
        log.info("Busqueda de cliente Parameter: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertarClienteParameter(@Valid @RequestBody ClienteDto clienteDto){
        log.info("Busqueda de cliente Parameter: {}", clienteDto);
        clienteService.insertarCliente(clienteDto);
    }
/*
    @PostMapping
    public ResponseEntity<HttpStatus> insertarClienteParameterResponseEntity(@RequestBody ClienteDto clienteDto){
        log.info("Busqueda de cliente Parameter: {}", clienteDto);
        clienteService.insertarCliente(clienteDto);

        return ResponseEntity.status(201).build();
    }
*/
    @GetMapping(value = "/all")
    public List<ClienteDto> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void actualizarCliente(@Valid @RequestBody ClienteDto clienteDto){
        log.info("cliente de cliente : {}", clienteDto);
        clienteService.actualizarCliente(clienteDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void eliminarCliente(@PathVariable int id){
        log.info("cliente de cliente : {}", id);
        clienteService.eliminarCliente(id);
    }
}
