package ddd.demo.application.order.ordercreatedomaineventsubsriber;

import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;


public class UpdateEsSubscriber implements IDomainEventSubscriber<OrderCreatedDomainEvent> {

    @Override
    public void handleEvent(OrderCreatedDomainEvent orderCreatedDomainEvent) {
        System.out.println(orderCreatedDomainEvent.getOrderId());
    }

    @Override
    public <T extends IDomainEvent> Class<?> suscribedToEventType() {
        return OrderCreatedDomainEvent.class;
    }
}
