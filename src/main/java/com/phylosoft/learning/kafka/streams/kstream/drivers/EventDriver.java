package com.phylosoft.learning.kafka.streams.kstream.drivers;

@FunctionalInterface
public interface EventDriver {

    void sendEvents();

}
