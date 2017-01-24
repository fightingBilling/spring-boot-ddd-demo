package ddd.demo.jsf;

import ddd.demo.application.order.OrderApplication;
import ddd.demo.domain.order.model.VendorInfo;

import ddd.demo.jsf.api.order.IOrderService;
import ddd.demo.jsf.api.order.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class OrderJsfService implements IOrderService {
    @Autowired
    private OrderApplication orderApplication;

    @Override
    public void create(OrderDto orderDto) throws Exception {
        this.orderApplication.create(new VendorInfo(1L, "sdf"), 1, BigDecimal.ONE, null, null);
    }

    @Override
    public void out(long orderId) throws Exception {
        this.orderApplication.out(orderId);
    }

    @Override
    public void confirm(long orderId) {
        this.orderApplication.confirm(orderId);
    }

    @Override
    public void cancel(long orderId) {
        this.orderApplication.cancel(orderId);
    }

    @Override
    public void complete(long orderId) {
        this.orderApplication.complete(orderId);
    }

    @Override
    public void delivery(long orderId) {
        this.delivery(orderId);
    }
}
