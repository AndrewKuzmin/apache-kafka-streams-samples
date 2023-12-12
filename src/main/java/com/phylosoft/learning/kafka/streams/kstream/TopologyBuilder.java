package com.phylosoft.learning.kafka.streams.kstream;

import org.apache.kafka.streams.StreamsBuilder;

@FunctionalInterface
public interface TopologyBuilder {

    void buildTopology(StreamsBuilder builder);

}
