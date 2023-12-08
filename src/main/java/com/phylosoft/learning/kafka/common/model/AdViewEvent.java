package com.phylosoft.learning.kafka.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdViewEvent {

    private long adDeliveryId;
    private String adId;
    private long timestamp;

    @Override
    public String toString() {
        return "AdViewEvent{" +
                "adDeliveryId=" + adDeliveryId +
                ", adId='" + adId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
