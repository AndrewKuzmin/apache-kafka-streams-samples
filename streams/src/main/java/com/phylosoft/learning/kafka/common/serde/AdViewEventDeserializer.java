package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phylosoft.learning.kafka.common.model.AdViewEvent;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class AdViewEventDeserializer implements Deserializer<AdViewEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public AdViewEvent deserialize(String topic, byte[] bytes) {

        try {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return mapper.readValue(bytes, AdViewEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
