package com.yglong.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private String name;
    private String email;
    private Integer age;
}
