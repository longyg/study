package com.yglong.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class Constant {
    public static final String BROKERS = "172.18.69.91:9092";
    public static final String TOPIC = "new-topic";
    public static final String STRING_SERIALIZER = StringSerializer.class.getName();
    public static final String STRING_DESERIALIZER = StringDeserializer.class.getName();
}
