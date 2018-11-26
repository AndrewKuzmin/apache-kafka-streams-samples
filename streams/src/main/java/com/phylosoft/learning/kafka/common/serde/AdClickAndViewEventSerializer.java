package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phylosoft.learning.kafka.common.model.AdClickAndViewEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdClickAndViewEventSerializer implements Serializer<AdClickAndViewEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, AdClickAndViewEvent event) {
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
