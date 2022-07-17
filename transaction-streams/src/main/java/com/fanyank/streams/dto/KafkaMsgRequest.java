package com.fanyank.streams.dto;

import lombok.Data;

@Data
public class KafkaMsgRequest {
    private Object key;
    private Object value;
    private String topic;
}
