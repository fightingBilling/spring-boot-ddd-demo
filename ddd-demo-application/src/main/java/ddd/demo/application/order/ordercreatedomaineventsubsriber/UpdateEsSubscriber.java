package ddd.demo.application.order.ordercreatedomaineventsubsriber;

import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;


public class UpdateEsSubscriber implements IDomainEventSubscriber<OrderCreatedDomainEvent> {

    @Autowired
    private IOrderElasticSearchQuery query;

    @Override
    public void handleEvent(OrderCreatedDomainEvent orderCreatedDomainEvent) {
        System.out.println(orderCreatedDomainEvent.getOrderId());

    }

    @Override
    public <T extends IDomainEvent> Class<?> suscribedToEventType() {
        return OrderCreatedDomainEvent.class;
    }
}
