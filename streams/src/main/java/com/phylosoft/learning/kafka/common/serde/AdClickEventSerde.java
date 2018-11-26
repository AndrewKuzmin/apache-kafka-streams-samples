package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdClickEventSerde implements Serde<AdClickEvent> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public void close() {

    }

    public Serializer<AdClickEvent> serializer() {
        return new AdClickEventSerializer();
    }

    public Deserializer<AdClickEvent> deserializer() {
        return new AdClickEventDeserializer();
    }
}
