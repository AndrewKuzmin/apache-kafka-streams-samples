package com.phylosoft.learning.kafka.streams.kstream.apps;

import com.phylosoft.learning.kafka.streams.kstream.drivers.NothingEventDriver;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;

public class PipeApp {

    public static void main(String[] args) {

        new StreamExecutor(args, "streams-pipe", new NothingEventDriver())
                .run((eventDriver, builder) -> {

                    builder.stream("streams-file-input").to("streams-pipe-output");

                });

    }

}
