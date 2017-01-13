package ddd.demo.application.order;

import ddd.demo.domain.order.event.*;
import ddd.demo.domain.order.model.*;
import ddd.demo.domain.order.repository.IOrderQueryRepository;
import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.domain.order.service.*;
import ddd.demo.domain.order.viewmodel.OrderViewModel;
import easy.domain.application.BaseApplication;
import easy.domain.application.result.IBaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.List;


/**
 * 订单应用服务层
 */
public class OrderApplication extends BaseApplication {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderQueryRepository orderQueryRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void eventTest() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 5000; i++) {

            this.publishEvent(new OrderDeliveredDomainEvent(i));
        }
        stopWatch.stop();
        System.out.println(String.format("total millis = %s", stopWatch.getTotalTimeMillis()));
    }


    /**
     * 根据订单ID查询详情
     *
     * @param orderId 订单ID
     * @return
     */
    public IBaseResult<Order> findById(long orderId) {
        Order o = this.orderRepository.findBy(orderId);

        return this.write(o);
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
    public void create(VendorInfo vendorInfo, Integer userId, BigDecimal discountPrice, List<OrderItem> orderItemList, DeliveryAddressInfo deliveryAddressInfo) throws Exception {
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
     * @param orderId 订单ID
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
    public void out(long orderId) throws Exception {
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
    public void delivery(long orderId) throws Exception {
        Order order = this.orderRepository.findBy(orderId);
        order.delivery();
        if (order.validate()) {
            orderRepository.update(order);
        }

        this.publishEvent(new OrderDeliveredDomainEvent(orderId));
    }

    /**
     * 订单完成 订单ID
     *
     * @param orderId 订单ID
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
     * @param orderId 订单ID
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
     * @param orderId             订单ID
     * @param deliveryAddressInfo 新收货地址
     */
    public void changeDeliveryAddress(long orderId, DeliveryAddressInfo deliveryAddressInfo) {
        Order order = this.orderRepository.findBy(orderId);

        order.changeDeliveryAddress(deliveryAddressInfo);
        if (order.validate()) {
            this.orderRepository.update(order);
        }
    }

    /**
     * 待出库列表
     *
     * @param venderId 商家ID
     * @return
     */
    public IBaseResult<List<OrderViewModel>> readyOut(long venderId) {
        List<OrderViewModel> orderViewModels = this.orderQueryRepository.readyOut(venderId);

        return this.write(orderViewModels);
    }

    /**
     * 待出库超时订单列表
     *
     * @param venderId 商家ID
     * @return
     */
    public IBaseResult<List<OrderViewModel>> readyOutTimeOut(long venderId) {
        List<OrderViewModel> orderViewModels = this.orderQueryRepository.readyOutTimeOut(venderId);

        return this.write(orderViewModels);
    }
}
