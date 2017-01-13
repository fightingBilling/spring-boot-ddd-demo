package ddd.demo.application.order.ordercreated;

import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.repository.IOrderQueryRepository;
import ddd.demo.domain.order.repository.IOrderRepository;
import easy.domain.event.IDomainEvent;
import easy.domain.event.IDomainEventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;


public class UpdateEsSubscriber implements IDomainEventSubscriber<OrderCreatedDomainEvent> {

    @Autowired
    private IOrderQueryRepository query;

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public void handleEvent(OrderCreatedDomainEvent orderCreatedDomainEvent) {

        Order order = this.orderRepository.findBy(orderCreatedDomainEvent.getOrderId());
        this.query.add(order);

    }


    @Override
    public Class<?> suscribedToEventType() {
        return OrderCreatedDomainEvent.class;
    }
}
