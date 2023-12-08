package com.phylosoft.learning.kafka.streams.kstream.apps.join;

import com.phylosoft.learning.kafka.common.model.AdClickAndViewEvent;
import com.phylosoft.learning.kafka.common.model.AdClickEvent;
import com.phylosoft.learning.kafka.common.model.AdViewEvent;
import com.phylosoft.learning.kafka.common.serde.AdSerdes;
import com.phylosoft.learning.kafka.streams.kstream.StreamExecutor;
import com.phylosoft.learning.kafka.streams.kstream.drivers.AdClickAndViewEventDriver;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;

/**
 * Created by Andrew on 12/8/2023.
 */
public class InnerJoinTableApp {

    public static void main(String[] args) {

        new StreamExecutor(args, "InnerJoinTableApp", new AdClickAndViewEventDriver(0, 0))
                .run((viewTopic, clickTopic, builder) -> {
                    KTable<Long, AdViewEvent> viewStream =
                            builder.table(viewTopic, Consumed.with(Serdes.Long(), AdSerdes.AD_VIEW_SERDE));
                    KTable<Long, AdClickEvent> clickStream =
                            builder.table(clickTopic, Consumed.with(Serdes.Long(), AdSerdes.AD_CLICK_SERDE));
                    KTable<Long, AdClickAndViewEvent> innerJoin =
                            viewStream.join(clickStream, (view, click) -> new AdClickAndViewEvent(view, click));
//                    innerJoin.print();
                });

    }

}
