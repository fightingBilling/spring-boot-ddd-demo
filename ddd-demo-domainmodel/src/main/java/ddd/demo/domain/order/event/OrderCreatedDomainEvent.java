package ddd.demo.domain.order.event;

import easy.domain.event.IDomainEvent;


public class OrderCreatedDomainEvent implements IDomainEvent {
    private long orderId;

    public OrderCreatedDomainEvent(long orderId){
        this.setOrderId(orderId);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
