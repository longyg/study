package com.yglong.kafka;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class KafkaAdminClientTest {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BROKERS);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        AdminClient client = AdminClient.create(props);

        NewTopic newTopic = new NewTopic(Constant.TOPIC, 2, (short) 1);
        CreateTopicsResult result = client.createTopics(Collections.singleton(newTopic));
        try {
            result.all().get();
            System.out.println(Constant.TOPIC + " is created");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> names = listTopics(client);

//        DeleteTopicsResult deleteTopicsResult = client.deleteTopics(names);
//        try {
//            deleteTopicsResult.all().get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("all topics deleted");
//
//        listTopics(client);

        client.close();
    }

    private static Set<String> listTopics(AdminClient client) {
        Set<String> names = new HashSet<>();
        ListTopicsResult topics = client.listTopics();
        try {
            names = topics.names().get();
            System.out.println("Existing topics: " + names);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
