package com.fanyank.streams.controller;

import com.fanyank.streams.config.KafkaProducer;
import com.fanyank.streams.dto.KafkaMsgRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/msg")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMsgToKafka(@RequestBody KafkaMsgRequest request) {
        producer.sendMessageToDesignatedTopic(request);
    }
}
