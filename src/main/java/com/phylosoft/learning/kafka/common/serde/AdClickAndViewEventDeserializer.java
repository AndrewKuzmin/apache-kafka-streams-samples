package com.phylosoft.learning.kafka.common.serde;

import com.phylosoft.learning.kafka.common.model.AdClickAndViewEvent;

public class AdClickAndViewEventDeserializer
        extends BaseJsonDeserializer<AdClickAndViewEvent> {

    public Class<AdClickAndViewEvent> getValueType() {
        return AdClickAndViewEvent.class;
    }

}
