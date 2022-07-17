package com.fanyank.streams.config;

import com.fanyank.model.AuthorizationSummary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@EnableConfigurationProperties(PollerProperties.class)
public class KafkaProducer {

    private final KafkaTemplate<String, AuthorizationSummary> kafkaTemplate;

    private final PollerProperties properties;

    public void bulkSend(List<AuthorizationSummary> summaryList) {
        if (!summaryList.isEmpty()) {
            summaryList.stream().forEach(summary -> {
                log.info("topic: {}, key: {}, value: {}", properties.getOutTopic(), summary.getMerchantId(), summary);
                this.kafkaTemplate.send(properties.getOutTopic(), summary.getMerchantId(), summary);
            });
        }

    }
}
