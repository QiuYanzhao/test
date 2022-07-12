package com.example.demo.repository;

import lombok.ToString;

@ToString
public class Example {
    Integer i;
    public Example() {
    }

    public Example(Integer i) {
        this.i = i;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

}
