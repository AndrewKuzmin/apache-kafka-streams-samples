package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdClickEvent;

public class AdClickEventDeserializer
        extends BaseJsonDeserializer<AdClickEvent> {

    public Class<AdClickEvent> getValueType() {
        return AdClickEvent.class;
    }

}
