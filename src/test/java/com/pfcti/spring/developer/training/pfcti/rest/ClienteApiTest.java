package com.pfcti.spring.developer.training.pfcti.rest;

import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteApiTest {
    @LocalServerPort
    private Integer port = 0;
    private String BASEURL = "http://localhost";
    private static final String BASERESTURL = "/v1/api/cliente";
    private WebTestClient webTestClient = null;

    @BeforeEach
    void setUp() {
        BASEURL = BASEURL.concat(":").concat(port.toString()).concat(BASERESTURL);
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl(BASEURL) .responseTimeout(Duration.ofSeconds(3600)) .build();
    }

    @Test
    void testCallbuscarTodosClientes(){
        List<ClienteDto> clienteDtos = this.webTestClient .get()
                .uri("/all")
                .exchange()
                .expectStatus()
                .isOk() .expectBodyList(ClienteDto.class) .returnResult() .getResponseBody();
        System.out.println("clientDtos : " +clienteDtos);
        assertFalse(clienteDtos.isEmpty());
        assertTrue(clienteDtos.get(0).getNombre() != null);
    }

    @Test
    void testGuardarCliente() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCedula("8888888");
        clienteDto.setTelefono("888888888");
        clienteDto.setNombre("JUANA");
        clienteDto.setApellidos("VEGA");
        clienteDto.setPais("CR");

        this.webTestClient.post().uri("")
                .body(Mono.just(clienteDto), ClienteDto.class)
                .exchange().expectStatus().isCreated();
    }
}
