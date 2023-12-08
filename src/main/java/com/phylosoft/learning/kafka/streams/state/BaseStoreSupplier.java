package com.phylosoft.learning.kafka.streams.state;

import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.StoreSupplier;

public class BaseStoreSupplier implements StoreSupplier {

    @Override
    public String name() {
        return null;
    }

    @Override
    public StateStore get() {
        return null;
    }

    @Override
    public String metricsScope() {
        return null;
    }

}
