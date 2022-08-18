package id.ten.realtimedashboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random;
    @Value("${kafka.topic}")
    public String topic;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
        random = new Random();

    }

    public void sendMessage() throws InterruptedException {

        while (true) {

            Thread.sleep(1000);

            String data = generateData();
            log.info("Producing message --> {}", data);
            this.kafkaTemplate.send(topic, data);

        }

    }

    private String generateData() {

        int data = random.nextInt(1000);
        return String.valueOf(data);

    }

}
