package com.phylosoft.learning.kafka.streams.kstream.apps;

import com.phylosoft.learning.kafka.drivers.NothingEventDriver;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;

import java.util.UUID;

public class KStreamToApp {

    public static void main(String[] args) {

        new StreamExecutor(args, "KStreamTo" + UUID.randomUUID(), new NothingEventDriver())
                .run((eventDriver, builder) -> {

                    builder.stream("streams-file-input").to("streams-pipe-output");

                });

    }

}
