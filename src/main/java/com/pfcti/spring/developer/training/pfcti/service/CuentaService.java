package com.pfcti.spring.developer.training.pfcti.service;

import com.pfcti.spring.developer.training.pfcti.criteria.CuentaSpecification;
import com.pfcti.spring.developer.training.pfcti.dto.ClienteDto;
import com.pfcti.spring.developer.training.pfcti.dto.CuentaDto;
import com.pfcti.spring.developer.training.pfcti.dto.NotificationDto;
import com.pfcti.spring.developer.training.pfcti.jms.publishers.NotificationPubSubSender;
import com.pfcti.spring.developer.training.pfcti.jms.sender.NotificationSender;
import com.pfcti.spring.developer.training.pfcti.model.Cliente;
import com.pfcti.spring.developer.training.pfcti.model.Cuenta;
import com.pfcti.spring.developer.training.pfcti.repository.ClienteRepository;
import com.pfcti.spring.developer.training.pfcti.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;
    private ClienteRepository clienteRepository;

    private NotificationSender notificationSender;
    private ClienteService clienteService;

    private NotificationPubSubSender notificationPubSubSender;

    public void insertarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setCliente(clienteRepository.getById(cuentaDto.getClienteId()));
        cuentaRepository.save(cuenta);
    }

    public void inactivarCuentasPorCliente(int clienteId) {
        cuentaRepository.inactivarCuentasPorCliente(clienteId);
    }

    private Cuenta deCuentaDtoACuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();

        BeanUtils.copyProperties(cuentaDto, cuenta);

        return cuenta;
    }

    private CuentaDto deCuentaACuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();

        BeanUtils.copyProperties(cuenta, cuentaDto);
        cuentaDto.setClienteId(cuenta.getCliente().getId());
        return cuentaDto;
    }

    public List<CuentaDto> buscarDinamicamentePorCriterios(CuentaDto cuentaDto) {
        return cuentaRepository
                .findAll(cuentaSpecification.buildFilter(cuentaDto))
                .stream()
                .map(this::deCuentaACuentaDto)
                .collect(Collectors.toList());
    }

    public List<CuentaDto> obtenerCuentasActivasPorCliente(int clienteId) {
        return cuentaRepository
                .findAllByCliente_IdAndEstadoIsTrue(clienteId)
                .stream()
                .map(this::deCuentaACuentaDto)
                .collect(Collectors.toList());
    }

    public void sendNotification(CuentaDto cuentaDto) {
        ClienteDto clienteDto = clienteService.obtenerCliente(cuentaDto.getClienteId());
        NotificationDto notificacionDto = new NotificationDto();
        notificacionDto.setPhoneNumber(clienteDto.getTelefono());
        notificacionDto.setMailBody(getMailBody(cuentaDto, clienteDto));
        this.notificationSender.sendMail(notificacionDto);
    }

    private static String getMailBody(CuentaDto cuentaDto, ClienteDto clienteDto) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Estimado ");
        bodyBuilder.append(clienteDto.getNombre());
        bodyBuilder.append(" ");
        bodyBuilder.append(clienteDto.getApellidos());
        bodyBuilder.append(" tu cuenta número ");
        bodyBuilder.append(cuentaDto.getNumero());
        bodyBuilder.append(" se ha creado con éxito.");
        return bodyBuilder.toString();

    }

    public void creacionDeCuentaYNotificacion(CuentaDto cuentaDto) {
        insertarCuenta(cuentaDto);
        sendNotification(cuentaDto);
    }

    public void sendCreateAccountNotification(CuentaDto cuentaDto) {
        log.info("Empezando envío de notificaciones");
        Message<CuentaDto> message = MessageBuilder.withPayload(cuentaDto).build();
        Message<String> result = notificationPubSubSender.sendNotification(message);
        log.info("Resultado envío notificación: {}", result.getPayload());
    }

    public void creacionDeCuentaPublishSub(CuentaDto cuentaDto) {
        insertarCuenta(cuentaDto);
        sendCreateAccountNotification(cuentaDto);
    }

}
