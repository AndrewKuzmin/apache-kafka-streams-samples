package com.phylosoft.learning.kafka.streams.kstream.transformers;

import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.kstream.ValueTransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.processor.StateStore;

public class BaseValueTransformerSupplier implements ValueTransformerSupplier<String, String> {

    @Override
    public ValueTransformer get() {

        return new ValueTransformer() {
            private StateStore state;

            @Override
            public void init(ProcessorContext context) {
                this.state = context.getStateStore("myValueTransformState");
                Punctuator callback = new Punctuator() {
                    @Override
                    public void punctuate(long timestamp) {

                    }
                };
                context.schedule(1000, PunctuationType.STREAM_TIME, callback); // call #punctuate() each 1000ms
            }

            @Override
            public Object transform(Object value) {
                // can access this.state
//                return new NewValueType(); // or null
                return null;
            }

            @Override
            public void close() {
                // can access this.state
            }
        };

    }


}
