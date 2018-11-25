package com.phylosoft.learning.kafka.streams.kstream.drivers;

import de.codecentric.kafka.streams.model.AdClickEvent;
import de.codecentric.kafka.streams.model.AdViewEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

final public class AdClickAndViewEventDriver implements EventDriver {

    final private int viewPartition;
    final private int clickPartition;
    final private String viewTopic;
    final private String clickTopic;

    final private Producer<Long, AdViewEvent> viewProducer;
    final private Producer<Long, AdClickEvent> clickProducer;

    public AdClickAndViewEventDriver(int viewPartition, int clickPartition) {

        this.viewPartition = viewPartition;
        this.clickPartition = clickPartition;
        this.viewTopic = "view" + UUID.randomUUID().toString();
        this.clickTopic = "click" + UUID.randomUUID().toString();

        Properties viewProps = new Properties();
        viewProps.put("bootstrap.servers", "localhost:9092");
        viewProps.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        viewProps.put("value.serializer", "de.codecentric.kafka.streams.serde.AdViewEventSerializer");
        viewProps.put("linger.ms", 0);

        Properties clickProps = new Properties();
        clickProps.put("bootstrap.servers", "localhost:9092");
        clickProps.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        clickProps.put("value.serializer", "de.codecentric.kafka.streams.serde.AdClickEventSerializer");
        clickProps.put("linger.ms", 10000);

        viewProducer = new KafkaProducer<>(viewProps);
        clickProducer = new KafkaProducer<>(clickProps);

    }

    public void sendEvents() {
        sendView(0, "click 1000 ms after view", 0);
        sendClick(0, 1000);

        sendView(1, "click 10,000 ms after view", 0);
        sendClick(1, 10000);

        sendView(2, "click 1000 ms before view", 1000);
        sendClick(2, 0);

        sendView(3, "no click", 0);

        //no view
        sendClick(4, 0);

        sendView(5, "duplicate view event1", 0);
        sendView(5, "duplicate view event2", 1);
        sendClick(5, 1000);


        sendView(6, "duplicate click 500 ms and 800 ms after view", 0);
        sendClick(6, 500);

        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        sendClick(6, 800);
    }

    private void sendView(long adDeliveryId, String adId, long timestamp) {
        AdViewEvent adViewEvent = new AdViewEvent();
        adViewEvent.setAdDeliveryId(adDeliveryId);
        adViewEvent.setAdId(adId);
        adViewEvent.setTimestamp(timestamp);
        viewProducer.send(new ProducerRecord<>(viewTopic, viewPartition, timestamp, adDeliveryId, adViewEvent));
    }

    private void sendClick(long adDeliveryId, long timestamp) {
        AdClickEvent adClickEvent = new AdClickEvent();
        adClickEvent.setAdDeliveryId(adDeliveryId);
        adClickEvent.setTimestamp(timestamp);
        clickProducer.send(new ProducerRecord<>(clickTopic, clickPartition, timestamp, adDeliveryId, adClickEvent));
    }

}
