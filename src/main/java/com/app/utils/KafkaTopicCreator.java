package com.app.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KafkaTopicCreator {

    private final AdminClient adminClient;

    public KafkaTopicCreator(AdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public void createTopicsFromFile(String filePath) {
        List<String> topics = readTopicsFromFile(filePath);

        for (String topic : topics) {
            createTopic(topic, 1, (short) 1);
        }
    }

    private List<String> readTopicsFromFile(String filePath) {
        List<String> topics = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                topics.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading topics from file", e);
        }

        return topics;
    }

    private void createTopic(String topicName, int partitions, short replicationFactor) {
        NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
        adminClient.createTopics(Collections.singletonList(newTopic));
    }
}
