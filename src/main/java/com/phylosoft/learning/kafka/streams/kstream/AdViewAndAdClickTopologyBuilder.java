package com.phylosoft.learning.kafka.streams.kstream;

import org.apache.kafka.streams.StreamsBuilder;

@FunctionalInterface
public interface AdViewAndAdClickTopologyBuilder {

    void buildTopology(String viewTopic, String clickTopic, StreamsBuilder builder);

}
