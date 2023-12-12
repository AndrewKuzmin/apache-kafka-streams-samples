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
                - KTable-KTable Foreign-Key Join
                - KStream-KTable Join
                - KStream-GlobalKTable Join
            - Windowing
                - Tumbling time windows
                - Hopping time windows
                - Sliding time windows
                - Session Windows
                - Window Final Results
                - Window duration and joins

        - Applying processors and transformers (Processor API integration)

    - Controlling KTable emit rate
 
    - Writing streams back to Kafka

- Processor API

- Interactive Queries