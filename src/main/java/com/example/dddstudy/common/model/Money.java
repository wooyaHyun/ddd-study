package com.example.dddstudy.common.model;

import lombok.Getter;

import java.util.Objects;


public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    //밸류 객체는 값을 불변식을 위해 변경하지 말고 새로 생성해야한다.
    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
