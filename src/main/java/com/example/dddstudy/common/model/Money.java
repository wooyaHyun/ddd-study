package com.example.dddstudy.common.model;

import lombok.Getter;


public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }


}
