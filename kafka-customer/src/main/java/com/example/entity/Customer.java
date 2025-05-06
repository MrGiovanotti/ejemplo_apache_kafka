package com.example.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer implements Serializable {

  private Long id;
  private String name;
  private String email;

}
