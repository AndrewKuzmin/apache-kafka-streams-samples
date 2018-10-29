# kafka-streams-samples
Apache Kafka Stream samples

## Plan

- Streams DSL

    - Creating source streams from Kafka

    - Transform a stream

        - Stateless transformations

        - Stateful transformations
            - Aggregating
            - Joining
                - Join co-partitioning requirements
                - KStream-KStream Join
                - KTable-KTable Join
                - KStream-KTable Join
                - KStream-GlobalKTable Join
            - Windowing
                - Tumbling time windows
                - Hopping time windows
                - Sliding time windows
                - Session Windows

        - Applying processors and transformers (Processor API integration)

    - Writing streams back to Kafka

    - Kafka Streams DSL for Scala

- Processor API

- Interactive Queries