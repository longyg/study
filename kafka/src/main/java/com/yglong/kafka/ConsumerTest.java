package com.yglong.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerTest {
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    private static Properties initConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BROKERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Constant.STRING_DESERIALIZER);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Constant.STRING_DESERIALIZER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer.group.demo");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(Constant.TOPIC));

        // 从broker拉取消息
        try {
            // 第一次poll是为了拉取主题分区信息
            consumer.poll(Duration.ofMillis(1000));
            Set<TopicPartition> partitions = consumer.assignment();
            consumer.seekToBeginning(partitions); // 指定从每个分区的开头消费
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset());
                    System.out.println("key = " + record.key() + ", value = " + record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
