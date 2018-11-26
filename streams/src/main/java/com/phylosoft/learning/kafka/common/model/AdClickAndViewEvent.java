package com.phylosoft.learning.kafka.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdClickAndViewEvent {

    private AdViewEvent viewEvent;
    private AdClickEvent clickEvent;

    public long duration() {
        return clickEvent.getTimestamp() - viewEvent.getTimestamp();
    }

    @Override
    public String toString() {
        return "AdClickAndViewEvent{" +
                "viewEvent=" + viewEvent +
                ", clickEvent=" + clickEvent +
                '}';
    }
}
