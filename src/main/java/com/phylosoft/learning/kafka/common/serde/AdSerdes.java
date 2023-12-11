package com.phylosoft.learning.kafka.common.serde;

public class AdSerdes {

    public static AdViewEventSerde AD_VIEW_SERDE = new AdViewEventSerde();
    public static AdClickEventSerde AD_CLICK_SERDE = new AdClickEventSerde();
    public static AdViewAndAdClickEventSerde AD_CLICK_VIEW_SERDE = new AdViewAndAdClickEventSerde();
}
