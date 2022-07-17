package com.fanyank.streams.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "com.fanyank.poller")
public class PollerProperties {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_CHUNK_SIZE = 10;
    private static final String POLLER_OUT_TOPIC = "poller-out-topic";

    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Integer chunkSize = DEFAULT_CHUNK_SIZE;

    private String outTopic = POLLER_OUT_TOPIC;
}
