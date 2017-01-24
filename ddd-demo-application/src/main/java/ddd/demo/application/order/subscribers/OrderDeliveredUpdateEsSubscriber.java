package ddd.demo.application.order.subscribers;


import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.event.OrderDeliveredDomainEvent;
import easy.domain.activemqdomainevent.AbstractJsonActiveMqDomainEventSubscriber;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;

import java.util.concurrent.ThreadPoolExecutor;

public class OrderDeliveredUpdateEsSubscriber extends AbstractJsonActiveMqDomainEventSubscriber<OrderDeliveredDomainEvent> {
    @Override
    public Class<?> subscribedToEventType() {
        return OrderCreatedDomainEvent.class;
    }

    @Override
    public void handleEvent(OrderDeliveredDomainEvent orderDeliveredDomainEvent) {


        System.out.println(orderDeliveredDomainEvent.getOrderId());
    }


}
