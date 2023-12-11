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

/**
 * Created by Andrew on 12/8/2023.
 */
public class InnerJoinTableApp {

    public static void main(String[] args) {

        new StreamExecutor(args, "InnerJoinTableApp", new AdClickAndViewEventDriver(0, 0))
                .run((viewTopic, clickTopic, builder) -> {
                    KTable<Long, AdViewEvent> viewTable =
                            builder.table(viewTopic, Consumed.with(Serdes.Long(), AdSerdes.AD_VIEW_SERDE));
                    KTable<Long, AdClickEvent> clickTable =
                            builder.table(clickTopic, Consumed.with(Serdes.Long(), AdSerdes.AD_CLICK_SERDE));
                    KTable<Long, AdViewAndAdClickEvent> innerJoin =
                            viewTable.join(clickTable, AdViewAndAdClickEvent::new);
                    innerJoin.toStream().print(Printed.toSysOut());
                });

    }

}
