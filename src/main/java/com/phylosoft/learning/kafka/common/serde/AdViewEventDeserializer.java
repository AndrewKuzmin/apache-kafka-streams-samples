package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdViewEvent;

public class AdViewEventDeserializer
        extends BaseJsonDeserializer<AdViewEvent> {

    public AdViewEventDeserializer() {
    }

    public Class<AdViewEvent> getValueType() {
        return AdViewEvent.class;
    }

}
