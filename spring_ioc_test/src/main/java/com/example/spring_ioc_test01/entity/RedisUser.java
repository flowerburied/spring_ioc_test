package com.example.spring_ioc_test01.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class RedisUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int age;

    private String address;

}

