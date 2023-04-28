package com.pfcti.spring.developer.training.pfcti.jms.receivers;

import com.pfcti.spring.developer.training.pfcti.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationProcessor {
    @JmsListener(destination = "smsReceiverJms")
    public void processMessage(NotificationDto notificationDto) {
        //Lógica para envío de sms.
        log.info("sms info:{}", notificationDto);
    }
}
