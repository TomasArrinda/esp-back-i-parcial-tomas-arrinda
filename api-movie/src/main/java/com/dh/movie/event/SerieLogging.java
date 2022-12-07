package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SerieLogging {
    private static final Logger log = LoggerFactory.getLogger(SerieLogging.class);
    private final RabbitTemplate rabbitTemplate;

    public SerieLogging(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(SerieLoggingData data) {
        log.info("Sending message from added series");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_LOG_COURSE, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SerieLoggingData {
        private String operationId;
    }
}