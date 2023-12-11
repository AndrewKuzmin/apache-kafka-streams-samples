package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdViewAndAdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdViewAndAdClickEventSerde implements Serde<AdViewAndAdClickEvent> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public void close() {

    }

    public Serializer<AdViewAndAdClickEvent> serializer() {
        return new AdViewAndAdClickEventSerializer();
    }

    public Deserializer<AdViewAndAdClickEvent> deserializer() {
        return new AdViewAndAdClickEventDeserializer();
    }
}
