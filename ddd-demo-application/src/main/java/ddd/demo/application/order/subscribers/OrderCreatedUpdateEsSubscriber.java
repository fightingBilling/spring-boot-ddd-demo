package ddd.demo.application.order.subscribers;

import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.repository.IOrderQueryRepository;
import ddd.demo.domain.order.repository.IOrderRepository;
import easy.domain.activemqdomainevent.AbstractJsonActiveMqDomainEventSubscriber;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderCreatedUpdateEsSubscriber extends AbstractJsonActiveMqDomainEventSubscriber<OrderCreatedDomainEvent> {

    @Autowired
    private IOrderQueryRepository query;

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Class<?> subscribedToEventType() {
        return OrderCreatedDomainEvent.class;
    }

    @Override
    public void handleEvent(OrderCreatedDomainEvent orderCreatedDomainEvent) {
        System.out.println("更新 es");
        Order order = this.orderRepository.findBy(orderCreatedDomainEvent.getOrderId());
        this.query.add(order);

    }


}
