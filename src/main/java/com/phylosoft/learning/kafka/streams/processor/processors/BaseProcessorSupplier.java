package com.phylosoft.learning.kafka.streams.processor.processors;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.state.KeyValueStore;

public class BaseProcessorSupplier implements ProcessorSupplier<String, String> {

    private final String storeName;

    public BaseProcessorSupplier(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public Processor<String, String> get() {

        return new Processor<String, String>() {

            //            private StateStore state;
            private KeyValueStore<String, String> store;

            @Override
            public void init(ProcessorContext context) {
//                this.state = context.getStateStore("myProcessorState");
//                context.schedule(1000); // call #punctuate() each 1000ms
                store = (KeyValueStore<String, String>) context.getStateStore(storeName);
            }

            @Override
            public void process(String key, String value) {
                // can access this.state
                String prevValue = store.get(key);
                if (prevValue != null)
                    store.delete(key);
                store.put(key, value);
            }

            @Override
            public void close() {
                // can access this.state
                store.close();
            }
        };

    }

}
