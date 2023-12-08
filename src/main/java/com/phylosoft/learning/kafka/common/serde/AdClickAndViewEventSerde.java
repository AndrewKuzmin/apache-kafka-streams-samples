package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdClickAndViewEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AdClickAndViewEventSerde implements Serde<AdClickAndViewEvent> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public void close() {

    }

    public Serializer<AdClickAndViewEvent> serializer() {
        return new AdClickAndViewEventSerializer();
    }

    public Deserializer<AdClickAndViewEvent> deserializer() {
        return new AdClickAndViewEventDeserializer();
    }
}
