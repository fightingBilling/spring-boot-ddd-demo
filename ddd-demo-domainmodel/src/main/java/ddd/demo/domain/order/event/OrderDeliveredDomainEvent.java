package ddd.demo.domain.order.event;


import easy.domain.event.IDomainEvent;

public class OrderDeliveredDomainEvent implements IDomainEvent {
    private long orderId;

    public OrderDeliveredDomainEvent(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return this.orderId;
    }


}
