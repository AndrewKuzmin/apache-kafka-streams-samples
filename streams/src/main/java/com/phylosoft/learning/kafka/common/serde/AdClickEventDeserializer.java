package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phylosoft.learning.kafka.common.model.AdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class AdClickEventDeserializer implements Deserializer<AdClickEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public AdClickEvent deserialize(String topic, byte[] bytes) {

        try {

            return mapper.readValue(bytes, AdClickEvent.class);
        } catch (Exception e) {

            return null;
        }
    }

    public void close() {

    }
}
