package com.example.config;

import com.example.entity.Customer;
import com.example.events.Event;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

  private static final String BOOTSTRAP_ADDRESS = "localhost:29092";

  @Bean
  public ProducerFactory<String, Event<Customer>> producerFactory() {
    Map<String, Object> configurationProperties = new HashMap<>();
    configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS);
    configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(configurationProperties);
  }

  @Bean
  public KafkaTemplate<String, Event<Customer>> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

}
