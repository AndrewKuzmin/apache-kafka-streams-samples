package com.phylosoft.learning.kafka.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdClickEvent {

    private long adDeliveryId;
    private long timestamp;

    @Override
    public String toString() {
        return "AdClickEvent{" +
                "adDeliveryId=" + adDeliveryId +
                ", timestamp=" + timestamp +
                '}';
    }
}
