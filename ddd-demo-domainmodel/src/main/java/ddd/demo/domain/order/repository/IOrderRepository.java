package ddd.demo.domain.order.repository;


import ddd.demo.domain.order.model.Order;

public interface IOrderRepository {
    long getNexOrderId();
    void add(Order order);
    void update(Order order);
    Order findBy(Long orderId);
    void throwTest();
}
