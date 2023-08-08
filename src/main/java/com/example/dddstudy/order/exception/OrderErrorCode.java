package com.example.dddstudy.order.exception;

import com.example.dddstudy.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {
/*
    NoOrderException;
    AlreadyShippedException;
    OrderAlreadyCanceledException;
*/
    NO_ORDER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "NoOrderException"),
    ALREADY_SHIPPED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AlreadyShippedException"),
    ORDER_ALREADY_CANCELED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "OrderAlreadyCanceledException"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
