package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Logging {
    private static final Logger log = LoggerFactory.getLogger(Logging.class);
    private final RabbitTemplate rabbitTemplate;

    public Logging(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(LoggingData data) {
        log.info("Sending message from added series");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_LOG_COURSE, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LoggingData {
        private String operationId;
    }
}