package com.example.dddstudy.domain.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderState {
    PAYMENT_WAITING("결제대기"),
    PREPARING("상품준비"),
    SHIPPED("상품발송"),
    DELIVERING("배송중"),
    DELIVERY_COMPLETED("배송완료"),
    CANCELED("취소"),
    ;

    private final String title;
}
