package com.phylosoft.learning.kafka.common.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Andrew Kuzmin on 11/28/2018.
 */
public abstract class BaseJsonDeserializer<T> implements Deserializer<T> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            if (data == null || data.length == 0) {
                return null;
            }
            return mapper.readValue(data, getValueType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }

    public abstract Class<T> getValueType();

}
