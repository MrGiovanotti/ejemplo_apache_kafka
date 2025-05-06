package com.example.events;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Event <T> {

  private String id;
  private Date date;
  EventType type;
  private T data;

}
