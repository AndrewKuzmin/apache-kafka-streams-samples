package com.phylosoft.learning.kafka.streams.kstream.apps.join;

import com.phylosoft.learning.kafka.common.model.AdViewAndAdClickEvent;
import com.phylosoft.learning.kafka.common.model.AdClickEvent;
import com.phylosoft.learning.kafka.common.model.AdViewEvent;
import com.phylosoft.learning.kafka.common.serde.AdSerdes;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;
import com.phylosoft.learning.kafka.streams.kstream.drivers.AdClickAndViewEventDriver;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;

public class OuterJoinTableApp {

    public static void main(String[] args) {

        var eventDriver = new AdClickAndViewEventDriver(0, 0);

        new StreamExecutor(args, "OuterJoinTableApp", eventDriver)
                .run((builder) -> {
                    KTable<Long, AdViewEvent> viewTable =
                            builder.table(eventDriver.getViewTopic(), Consumed.with(Serdes.Long(), AdSerdes.AD_VIEW_SERDE));
                    KTable<Long, AdClickEvent> clickTable =
                            builder.table(eventDriver.getClickTopic(), Consumed.with(Serdes.Long(), AdSerdes.AD_CLICK_SERDE));
                    KTable<Long, AdViewAndAdClickEvent> outerJoin =
                            viewTable.outerJoin(clickTable, AdViewAndAdClickEvent::new);
                    outerJoin.toStream().print(Printed.toSysOut());
                });

    }

}
