package com.phylosoft.learning.kafka.drivers;

@FunctionalInterface
public interface EventDriver {

    void sendEvents();

}
