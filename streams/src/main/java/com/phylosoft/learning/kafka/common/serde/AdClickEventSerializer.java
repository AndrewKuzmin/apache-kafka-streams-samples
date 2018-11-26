package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phylosoft.learning.kafka.common.model.AdClickEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdClickEventSerializer implements Serializer<AdClickEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, AdClickEvent adClickEvent) {
        try {
            return mapper.writeValueAsBytes(adClickEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
