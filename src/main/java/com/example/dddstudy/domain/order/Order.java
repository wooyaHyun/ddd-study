package com.example.dddstudy.domain.order;

import com.example.dddstudy.common.jpa.MoneyConverter;
import com.example.dddstudy.common.model.Money;
import com.example.dddstudy.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchase_order")
@Entity
public class Order extends BaseEntity {
    /*
     * DB 자동증가 컬럼 사용한 예
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 식별자
    */
    @EmbeddedId
    private OrderNo orderNo; // 주문 식별자(타입만으로도 식별자임을 알 수 있도록 한 것)

    @Embedded
    private Orderer orderer; // 주문자

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_amounts")
    private Money totalAmounts; // 주문 총 금액

    @Embedded
    private ShippingInfo shippingInfo; // 배송지

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Builder
    public Order(OrderNo orderNo, Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        this.orderNo = orderNo;
        this.orderer = orderer;
        this.orderLines = orderLines;
        this.shippingInfo = shippingInfo;
        this.state = state;
    }
}
