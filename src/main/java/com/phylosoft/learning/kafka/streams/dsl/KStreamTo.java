package com.phylosoft.learning.kafka.streams.dsl;

import com.phylosoft.learning.kafka.drivers.NothingEventDriver;

import java.util.UUID;

public class KStreamTo {

    public static void main(String[] args) {

        new StreamExecutor(args, "KStreamTo" + UUID.randomUUID(), new NothingEventDriver())
                .run((eventDriver, builder) -> {

                    builder.stream("streams-file-input").to("streams-pipe-output");

                });

    }

}
