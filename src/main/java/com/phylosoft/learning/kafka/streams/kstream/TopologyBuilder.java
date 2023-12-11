package com.phylosoft.learning.kafka.streams.kstream;

import org.apache.kafka.streams.StreamsBuilder;

@FunctionalInterface
public interface TopologyBuilder {

    void buildTopology(String viewTopic, String clickTopic, StreamsBuilder builder);

}
