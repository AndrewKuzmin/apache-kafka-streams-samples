package com.phylosoft.learning.kafka.streams.processor.processors;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

/**
 * Created by Andrew Kuzmin on 11/14/2018.
 */
public class CustomMaxAggregatorSupplier implements ProcessorSupplier<String, Long> {

    @Override
    public Processor<String, Long> get() {
        return new CustomMaxAggregator();
    }

}

