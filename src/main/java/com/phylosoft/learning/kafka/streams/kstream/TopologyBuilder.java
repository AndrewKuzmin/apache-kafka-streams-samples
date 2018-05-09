package com.phylosoft.learning.kafka.streams.kstream;

import com.phylosoft.learning.kafka.drivers.EventDriver;
import org.apache.kafka.streams.StreamsBuilder;

@FunctionalInterface
public interface TopologyBuilder {

    void buildTopology(EventDriver eventDriver, StreamsBuilder builder);

}
