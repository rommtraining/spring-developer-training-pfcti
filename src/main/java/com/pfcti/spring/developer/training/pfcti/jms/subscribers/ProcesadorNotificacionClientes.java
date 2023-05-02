package com.pfcti.spring.developer.training.pfcti.jms.subscribers;

import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionClientes {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaClientes(Message<CuentaDto> message) {
        CuentaDto cuentaDto = message.getPayload();
        log.info("ProcesadorNotificacionClientes -> Enviando notificación al cliente con la siguiente información : {}", cuentaDto);
        return MessageBuilder.withPayload("Mensaje recibido por ProcesadorNotificacionClientes").build();
    }
}
