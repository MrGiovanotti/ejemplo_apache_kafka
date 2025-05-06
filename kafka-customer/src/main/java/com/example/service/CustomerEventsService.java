package com.example.service;

import com.example.entity.Customer;
import com.example.events.CustomerCreatedEvent;
import com.example.events.Event;
import com.example.events.EventType;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEventsService {

  private final KafkaTemplate<String, Event<?>> producer;

  @Value("${topic.customer.name:customers}")
  private String topicCustomer;

  public void publish(Customer customer) {
    CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
    customerCreatedEvent.setData(customer);
    customerCreatedEvent.setId(UUID.randomUUID().toString());
    customerCreatedEvent.setType(EventType.CREATED);
    customerCreatedEvent.setDate(new Date());
    producer.send(topicCustomer, customerCreatedEvent);
  }

}
