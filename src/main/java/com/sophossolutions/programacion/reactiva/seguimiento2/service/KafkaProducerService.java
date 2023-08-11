package com.sophossolutions.programacion.reactiva.seguimiento2.service;

import com.sophossolutions.programacion.reactiva.seguimiento2.model.OrdenLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKey(String topico, Integer key, OrdenLocal ordenLocal ){
        var future = kafkaTemplate.send(topico, key.toString(), ordenLocal.toString());

        future.whenComplete((resultadoEnvio, excepcion) -> {
            if(excepcion != null){
                LOGGER.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                future.complete(resultadoEnvio);
            }
            LOGGER.info("Pedido enviado al topico de Kafka con id " + ordenLocal);
        });
    }

    public void send(String topico, OrdenLocal ordenLocal){
        var future = kafkaTemplate.send(topico, ordenLocal.toString());

        future.whenComplete((resultadoEnvio, excepcion) -> {
            if(excepcion != null){
                LOGGER.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                future.complete(resultadoEnvio);
            }
            LOGGER.info("Pedido enviado al topico de Kafka "+ordenLocal);
        });

    }
}
