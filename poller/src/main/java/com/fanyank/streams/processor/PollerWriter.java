package com.fanyank.streams.processor;

import com.fanyank.streams.config.KafkaProducer;
import com.fanyank.model.AuthorizationSummary;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@AllArgsConstructor
public class PollerWriter implements ItemWriter<AuthorizationSummary> {

    private final KafkaProducer kafkaProducer;

    @Override
    public void write(List<? extends AuthorizationSummary> list) throws Exception {
        kafkaProducer.bulkSend((List<AuthorizationSummary>) list);
    }
}
