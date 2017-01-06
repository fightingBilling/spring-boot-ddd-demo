package ddd.demo.jsf.api.order;


import java.util.List;

public interface IOrderService {
    void create(OrderDto orderDto);

    void out(long orderId);

    void confirm(long orderId);

    void cancel(long orderId);

    void complete(long orderId);

    void delivery(long orderId);
}
