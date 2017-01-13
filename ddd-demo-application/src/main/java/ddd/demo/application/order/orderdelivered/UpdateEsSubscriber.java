package ddd.demo.application.order.orderdelivered;


import ddd.demo.domain.order.event.OrderDeliveredDomainEvent;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;

import java.util.concurrent.ThreadPoolExecutor;

public class UpdateEsSubscriber implements IDomainEventSubscriber<OrderDeliveredDomainEvent> {
    @Override
    public void handleEvent(OrderDeliveredDomainEvent orderDeliveredDomainEvent) {


        System.out.println(orderDeliveredDomainEvent.getOrderId());
    }


    @Override
    public Class<?> suscribedToEventType() {
        return OrderDeliveredDomainEvent.class;
    }
}
