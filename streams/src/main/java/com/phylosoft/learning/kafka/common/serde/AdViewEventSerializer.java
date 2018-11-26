package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phylosoft.learning.kafka.common.model.AdViewEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdViewEventSerializer implements Serializer<AdViewEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, AdViewEvent adViewEvent) {
        try {
            return mapper.writeValueAsBytes(adViewEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
