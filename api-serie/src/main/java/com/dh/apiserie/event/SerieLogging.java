package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = RabbitMQConfig.QUEUE_COURSE)
    public void receiveMessage(final SerieLoggingData message) {
        log.info("Received message as generic AMQP 'Message' wrapper: {}", message.operationId);
    }

    @Getter
    @Setter
    public static class SerieLoggingData {
        private long studentId;
        private String operationId;
    }
}