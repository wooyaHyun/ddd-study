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
    private OrderNo number; // 주문 식별자(타입만으로도 식별자임을 알 수 있도록 한 것)

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
    public Order(OrderNo number, Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        setNumber(number);
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.state = state;
        this.orderDate = LocalDateTime.now();
    }

    // null 검사해야하는 프로퍼티는 부분 set 메서드에서 검사하고 생성자로 생성하기 -> 여기서 set은 private로 외부에서 사용 불가능하며 생성자를 통해서만 검사용도로 사용 가능하다.
    private void setNumber(OrderNo number) {
        if (number == null) {
            throw new IllegalArgumentException("no number");
        }
        this.number = number;
    }

    private void setOrderer(Orderer orderer) {
        if(orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        //if (orderLines == null || orderLines.isEmpty()) throw new IllegalArgumentException("no OrderLines");
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        //주문 목록 총 금액 계산
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) throw new IllegalArgumentException("no OrderLines");
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream().mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if(shippingInfo == null) throw new IllegalArgumentException("no shipping info");
        this.shippingInfo = shippingInfo;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) { // 출고를 하면 배송지 정보를 변경할 수 없다.
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        //Events.raise(new ShippingInfoChangedEvent(number, newShippingInfo)); // ????
    }

    public void cancel() { // 출고 전에 주문을 취소할 수 있다.
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
        //Events.raise(new OrderCanceledEvent(number.getNumber())); // ????
    }

    private void verifyNotYetShipped() {
        if(!isNotYeyShipped()) {
            // todo Make custom exception
            throw new IllegalStateException("Already Shipped");
        }
    }

    public boolean isNotYeyShipped() {
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public void startShipping() {
        verifyShippableState();
        this.state = OrderState.SHIPPED;
        //Events.raise(new ShippingStartedEvent(number.getNumber())); // ????
    }

    private void verifyShippableState() {
        verifyNotYetShipped();
    }

    private void verifyNotCanceled() {
        if (state == OrderState.CANCELED) {
            // todo Make custom exception
            throw new IllegalStateException("Already canceled");
        }
    }
}
