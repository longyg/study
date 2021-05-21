package com.yglong.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class ProducerTest {
    private static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BROKERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Constant.STRING_SERIALIZER);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Constant.STRING_SERIALIZER);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        try {
            // 异步发送，不关心发送结果
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>(Constant.TOPIC, "hello, Kafka!")); // 这5条可能会在一个批次中发送到broker
            }
            System.out.println("5 message are sent");

            // 同步方式发送，发送后通过get方法等待发送结果
            for (int i = 0; i < 5; i++) {
                RecordMetadata metadata = producer.send(new ProducerRecord<>(Constant.TOPIC, "hello, Kafka, wait for result")).get();
                System.out.println("message is sent, partition: " + metadata.partition() + ", offset: " + metadata.offset());
            }

            // 异步发送，通过回调函数异步获取发送结果
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>(Constant.TOPIC, "hello, Kafka, give me result"), (metadata, exception) -> {
                    if (null != metadata) {
                        System.out.println("message is sent, partition: " + metadata.partition() + ", offset: " + metadata.offset());
                    }
                    if (null != exception) {
                        System.out.println("message is sent failed");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
