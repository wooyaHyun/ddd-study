package com.example.dddstudy.order.exception;

import com.example.dddstudy.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderException extends RuntimeException{
    private final ErrorCode errorCode;
}
