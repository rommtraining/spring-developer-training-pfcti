package com.pfcti.spring.developer.training.pfcti.jms.publishers;

import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface NotificationPubSubSender {
    @Gateway(requestChannel = "pubSubNotification")
    Message<String> sendNotification(Message<CuentaDto> message);
}
