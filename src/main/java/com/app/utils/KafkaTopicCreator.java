package com.app.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaTopicCreator {

    @Autowired
    public KafkaAdmin admin;

    @Autowired
    ScriptLoader scriptLoader;

//    public KafkaTopicCreator(AdminClient adminClient) {
//        this.adminClient = adminClient;
//    }

    public void createTopicsFromFile(String filePath) throws IOException {
        List<String> topics = readTopicsFromFile(filePath);

        for (String topic : topics) {
            createTopic(topic, 1, (short) 1);
        }
    }

    private List<String> readTopicsFromFile(String filePath) throws IOException {
        List<String> topics = new ArrayList<>();


        File file = scriptLoader.loadScriptFile(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                topics.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading topics from file", e);
        }

        return topics;
    }

//    private void createTopic(String topicName, int partitions, short replicationFactor) {
//        NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
//        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());
//        adminClient.createTopics(Collections.singletonList(newTopic));
//        log.info("{} topic configured", topicName);
//    }

    private void createTopic(String topicName, int partitions, short replicationFactor)  {
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());
        try {
            // Check if the topic exists before creating it
            boolean topicExists =  adminClient.listTopics().names().get().stream().anyMatch(topic -> topic.equalsIgnoreCase(topicName));
            if (topicExists) {
                // Create the topic if it doesn't exist
                NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
                CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singletonList(newTopic));
                createTopicsResult.all().get();  // Wait for create completion
                log.info("{} topic created successfully", topicName);
            } else {
                log.info("{} topic already exists", topicName);
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error creating topic {}: ", topicName, e);
        } finally {
            adminClient.close();  // Close the AdminClient
        }
    }
}
