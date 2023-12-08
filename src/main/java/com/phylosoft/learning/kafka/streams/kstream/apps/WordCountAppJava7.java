package com.phylosoft.learning.kafka.streams.kstream.apps;

import com.phylosoft.learning.kafka.streams.kstream.drivers.NothingEventDriver;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;

public class WordCountAppJava7 {

    public static void main(String[] args) {

        new StreamExecutor(args, "wordcount-application-java7", new NothingEventDriver())
                .run((eventDriver, builder) -> {

                    KStream<String, String> textLines = builder.stream("TextLinesTopic");
                    KTable<String, Long> wordCounts = textLines
                            .flatMapValues(new ValueMapper<String, Iterable<String>>() {
                                @Override
                                public Iterable<String> apply(String textLine) {
                                    return Arrays.asList(textLine.toLowerCase().split("\\W+"));
                                }
                            })
                            .groupBy(new KeyValueMapper<String, String, String>() {
                                @Override
                                public String apply(String key, String word) {
                                    return word;
                                }
                            })
                            .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));

                    wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));

                });

    }

}