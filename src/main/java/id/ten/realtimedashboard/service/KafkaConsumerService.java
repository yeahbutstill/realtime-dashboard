package id.ten.realtimedashboard.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    final SimpMessagingTemplate template;

    public KafkaConsumerService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void consume(@Payload String message) {
        if (isNumeric(message)) {
            template.convertAndSend("/topic/temperature", message);
        }

    }

    public boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}