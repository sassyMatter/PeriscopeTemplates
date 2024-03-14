package com.app.runner;

import com.app.utils.KafkaTopicCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaTopicInitializerRunner implements CommandLineRunner {

    private final KafkaTopicCreator kafkaTopicCreator;

    public KafkaTopicInitializerRunner(KafkaTopicCreator kafkaTopicCreator) {
        this.kafkaTopicCreator = kafkaTopicCreator;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pass the file path containing topics to create
        try {
            kafkaTopicCreator.createTopicsFromFile("kafka_config.txt");
        }catch(Exception ex){
            log.error("Could not initialize kafka topics");
            ex.printStackTrace();
        }
    }
}
