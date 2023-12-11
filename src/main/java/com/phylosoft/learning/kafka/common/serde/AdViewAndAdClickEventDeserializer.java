package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdViewAndAdClickEvent;

public class AdViewAndAdClickEventDeserializer
        extends BaseJsonDeserializer<AdViewAndAdClickEvent> {

    public Class<AdViewAndAdClickEvent> getValueType() {
        return AdViewAndAdClickEvent.class;
    }

}
