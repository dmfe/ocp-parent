package com.nc.ocp.io.serialization.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private char type;
}
