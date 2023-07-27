package com.example.dddstudy.domain.order;

import com.example.dddstudy.common.jpa.MoneyConverter;
import com.example.dddstudy.common.model.Money;
import com.example.dddstudy.domain.catalog.product.ProductId;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderLine {
    @Embedded
    private ProductId product;

    @Convert(converter = MoneyConverter.class)
    @Column
    private Money price;

    @Column
    private int quantity;

    @Convert(converter = MoneyConverter.class)
    @Column
    private Money amounts;

    @Builder
    public OrderLine(ProductId product, Money price, int quantity, Money amounts) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = amounts;
    }
}
