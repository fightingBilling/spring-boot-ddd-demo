package ddd.demo.domain.order.event;

import easy.domain.event.IDomainEvent;

/**
 * 订单发货事件
 */
public class OrderOutDomainEvent implements IDomainEvent {

    private Long orderId;

    public OrderOutDomainEvent(Long orderId) {
        this.setOrderId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
