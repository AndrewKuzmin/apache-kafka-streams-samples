package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdViewEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdViewEventSerde implements Serde<AdViewEvent> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public void close() {

    }

    public Serializer<AdViewEvent> serializer() {
        return new AdViewEventSerializer();
    }

    public Deserializer<AdViewEvent> deserializer() {
        return new AdViewEventDeserializer();
    }
}
