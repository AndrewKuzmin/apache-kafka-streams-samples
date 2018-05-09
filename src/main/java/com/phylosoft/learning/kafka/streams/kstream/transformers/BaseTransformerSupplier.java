package com.phylosoft.learning.kafka.streams.kstream.transformers;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;

public class BaseTransformerSupplier implements TransformerSupplier {

    @Override
    public Transformer get() {

        return new Transformer() {

            private ProcessorContext context;
            private StateStore state;

            @Override
            public void init(ProcessorContext context) {
                this.context = context;
                this.state = context.getStateStore("myTransformState");
                context.schedule(1000); // call #punctuate() each 1000ms
            }

            @Override
            public Object transform(Object key, Object value) {
                // can access this.state
                // can emit as many new KeyValue pairs as required via this.context#forward()
                return new KeyValue(key, value); // can emit a single value via return -- can also be null
            }

            @Override
            public Object punctuate(long timestamp) {
                return null;
            }

            @Override
            public void close() {
                // can access this.state
                // can emit as many new KeyValue pairs as required via this.context#forward()
            }
        };

    }

}
