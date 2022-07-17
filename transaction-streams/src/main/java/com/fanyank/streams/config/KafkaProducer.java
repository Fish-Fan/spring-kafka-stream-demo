package com.fanyank.streams.config;

import com.fanyank.streams.dto.KafkaMsgRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<Object, Object> genericProducerTemplate;

    public void sendMessageToDesignatedTopic(KafkaMsgRequest request) {
        log.info("topic: {}, key: {}, value: {}", request.getTopic(), request.getKey(), request.getValue());
        this.genericProducerTemplate.send(request.getTopic(), request.getKey(), request.getValue());
    }
}
