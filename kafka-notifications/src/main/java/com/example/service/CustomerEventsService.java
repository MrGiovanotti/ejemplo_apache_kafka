package com.example.service;

import com.example.events.CustomerCreatedEvent;
import com.example.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerEventsService {

  @KafkaListener(topics = "${topic.customer.name:customers}",
      containerFactory = "kafkaListenerContainerFactory",
      groupId = "grupo1")
  public void consumer(Event<?> event) {

    if (event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
      CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;
      log.info("***** Customer received with id = {}, data = {} *****",
          customerCreatedEvent.getId(), customerCreatedEvent.getData());
    }

  }

}
