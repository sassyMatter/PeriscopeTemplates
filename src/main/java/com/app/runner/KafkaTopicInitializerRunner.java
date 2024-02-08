package com.app.runner;

import com.app.utils.KafkaTopicCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicInitializerRunner implements CommandLineRunner {

    private final KafkaTopicCreator kafkaTopicCreator;

    public KafkaTopicInitializerRunner(KafkaTopicCreator kafkaTopicCreator) {
        this.kafkaTopicCreator = kafkaTopicCreator;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pass the file path containing topics to create
        kafkaTopicCreator.createTopicsFromFile("src/main/resources/scripts/kafka_config.txt");
    }
}
