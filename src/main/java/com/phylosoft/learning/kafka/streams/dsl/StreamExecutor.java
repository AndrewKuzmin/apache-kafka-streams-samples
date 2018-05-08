package com.phylosoft.learning.kafka.streams.dsl;

import com.phylosoft.learning.kafka.drivers.EventDriver;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class StreamExecutor {

    final private String[] args;
    final private String appId;
    final private EventDriver eventDriver;

    public StreamExecutor(String[] args, String appId, EventDriver eventDriver) {
        this.args = args;
        this.appId = appId;

        this.eventDriver = eventDriver;
    }

    public void run(TopologyBuilder topologyBuilder) {
        eventDriver.sendEvents();

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StreamsBuilder builder = new StreamsBuilder();
        topologyBuilder.buildTopology(eventDriver, builder);
        StreamsConfig config = new StreamsConfig(getProperties(args));
        KafkaStreams streams = new KafkaStreams(builder.build(), config);

        //
        streams.cleanUp();

        //
        streams.start();

        //
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));


    }

    private Properties getProperties(String[] args) {
        final String bootstrapServers = args.length > 0 ? args[0] : "localhost:9092";

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "/tmp/streams-examples");

        // Records should be flushed every 10 seconds. This is less than the default
        // in order to keep this example interactive.
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);

        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return properties;
    }

}
