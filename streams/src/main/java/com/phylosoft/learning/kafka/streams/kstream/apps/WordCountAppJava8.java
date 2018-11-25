package com.phylosoft.learning.kafka.streams.kstream.apps;

import com.phylosoft.learning.kafka.streams.kstream.drivers.NothingEventDriver;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;

public class WordCountAppJava8 {

    public static void main(String[] args) {

        new StreamExecutor(args, "wordcount-application-java8", new NothingEventDriver())
                .run((eventDriver, builder) -> {

                    KStream<String, String> textLines = builder.stream("TextLinesTopic");
                    KTable<String, Long> wordCounts = textLines
                            .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                            .groupBy((key, word) -> word)
                            .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
                    wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));

                });

    }

}
