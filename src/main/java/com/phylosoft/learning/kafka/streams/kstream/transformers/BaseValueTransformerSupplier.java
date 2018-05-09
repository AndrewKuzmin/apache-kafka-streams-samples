package com.phylosoft.learning.kafka.streams.kstream.transformers;

import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.kstream.ValueTransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;

public class BaseValueTransformerSupplier implements ValueTransformerSupplier<String, String> {

    @Override
    public ValueTransformer get() {

        return new ValueTransformer() {
            private StateStore state;

            @Override
            public void init(ProcessorContext context) {
                this.state = context.getStateStore("myValueTransformState");
                context.schedule(1000); // call #punctuate() each 1000ms
            }

            @Override
            public Object transform(Object value) {
                // can access this.state
//                return new NewValueType(); // or null
                return null;
            }

            @Override
            public Object punctuate(long timestamp) {
                // can access this.state
                return null; // don't return result -- can also be "new NewValueType()" (current key will be used to build KeyValue pair)
            }

            @Override
            public void close() {
                // can access this.state
            }
        };

    }


}
