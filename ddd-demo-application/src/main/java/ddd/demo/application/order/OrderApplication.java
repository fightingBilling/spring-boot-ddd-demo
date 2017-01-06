package ddd.demo.application.order;

import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.event.OrderOutDomainEvent;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.domain.order.service.PayPriceService;
import ddd.demo.domain.order.service.TotalPriceService;
import easy.domain.application.BaseApplication;
import easy.domain.application.BaseReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

import static javafx.scene.input.KeyCode.O;

/**
 * 订单应用服务层
 */
public class OrderApplication extends BaseApplication {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    /**
     * 根据订单ID查询详情
     *
     * @param orderId
     * @return
     */
    public BaseReturn<Order> findById(long orderId) {
        Order o = this.orderRepository.findBy(orderId);

        return this.write("findById", o);
    }

    /**
     * 生成订单
     *
     * @param vendorInfo          商家信息
     * @param userId              下单用户ID
     * @param discountPrice       折扣金额
     * @param orderItemList       订单明细
     * @param deliveryAddressInfo 收货地址
     */
    public void create(VendorInfo vendorInfo, Integer userId, BigDecimal discountPrice, List<OrderItem> orderItemList, DeliveryAddressInfo deliveryAddressInfo) {
        Long orderId = this.orderRepository.getNexOrderId();
        BigDecimal totalPrice = new TotalPriceService().getTotalPrice(orderItemList);
        BigDecimal payPrice = new PayPriceService().getPayPrice(totalPrice, discountPrice);
        Order order = new Order(orderId, totalPrice, discountPrice, payPrice, userId, vendorInfo, orderItemList, deliveryAddressInfo);


        if (!order.validate()) {
            return;
        }


        TransactionTemplate transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
        transactionTemplate.setReadOnly(false);
        transactionTemplate.execute(s -> {

            this.orderRepository.add(order);
            return true;
        });

        this.publishEvent(new OrderCreatedDomainEvent(order.getId()));
    }

    /**
     * 订单确认
     *
     * @param orderId
     */
    public void confirm(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.confirm();
        if (order.validate()) {
            this.orderRepository.update(order);
        }
    }

    /**
     * 订单出库
     *
     * @param orderId 订单ID
     */
    public void out(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.out();
        if (order.validate()) {
            orderRepository.update(order);
        }

        this.publishEvent(new OrderOutDomainEvent(order.getId()));
    }

    /**
     * 订单发货
     *
     * @param orderId 订单ID
     */
    public void delivery(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.delivery();
        if (order.validate()) {
            orderRepository.update(order);
        }
    }

    /**
     * 订单完成 订单ID
     *
     * @param orderId
     */
    public void complete(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.complete();
        if (order.validate()) {
            orderRepository.update(order);
        }
    }

    /**
     * 订单取消
     *
     * @param orderId
     */
    public void cancel(long orderId) {
        Order order = this.orderRepository.findBy(orderId);
        order.cancel();
        if (order.validate()) {
            orderRepository.update(order);
        }
    }

    /**
     * 修改订单收货地址
     *
     * @param orderId
     * @param deliveryAddressInfo
     */
    public void changeDeliveryAddress(long orderId, DeliveryAddressInfo deliveryAddressInfo) {
        Order order = this.orderRepository.findBy(orderId);

        order.changeDeliveryAddress(deliveryAddressInfo);
        if (order.validate()) {
            this.orderRepository.update(order);
        }
    }
}
