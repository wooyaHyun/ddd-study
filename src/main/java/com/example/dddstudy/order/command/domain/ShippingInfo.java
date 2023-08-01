package com.example.dddstudy.order.command.domain;

import com.example.dddstudy.common.model.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ShippingInfo {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code")),
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_address1")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_address2"))
    })
    private Address address;


    @Embedded
    private Receiver receiver;

    @Column(name = "shipping_message")
    private String message;

    @Builder
    public ShippingInfo(Address address, Receiver receiver, String message) {
        this.address = address;
        this.receiver = receiver;
        this.message = message;
    }
}
