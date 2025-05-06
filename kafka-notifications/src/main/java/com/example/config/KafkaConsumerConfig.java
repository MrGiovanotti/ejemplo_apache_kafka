package com.example.config;

import com.example.entity.Customer;
import com.example.events.Event;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  private static final String BOOTSTRAP_ADDRESS = "localhost:29092";
  private static final String EVENTS_PACKAGE =
      "com.example.events";

  @Bean
  public ConsumerFactory<String, Event<Customer>> consumerFactory() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS);
    properties.put(JsonDeserializer.TRUSTED_PACKAGES, EVENTS_PACKAGE);

    final JsonDeserializer<Event<Customer>> jsonDeserializer = new JsonDeserializer<>();

    return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(),
        jsonDeserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Event<Customer>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Event<Customer>> kafkaListenerContainerFactory =
        new ConcurrentKafkaListenerContainerFactory<>();
    kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
    return kafkaListenerContainerFactory;
  }

}
