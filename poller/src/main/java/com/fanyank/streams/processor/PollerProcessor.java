package com.fanyank.streams.processor;

import com.fanyank.model.AuthorizationSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PollerProcessor implements ItemProcessor<AuthorizationSummary, AuthorizationSummary> {
    @Override
    public AuthorizationSummary process(AuthorizationSummary authorizationSummary) throws Exception {
        log.info("authorization summary is going through processor: {}", authorizationSummary);
        return authorizationSummary;
    }
}
