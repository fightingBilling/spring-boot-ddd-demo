package ddd.demo.domain.order.viewmodel;

import ddd.demo.domain.order.model.OrderStatus;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * 订单视图
 */
public class OrderViewModel {
    public long orderId;
    public BigDecimal totalPrice;
    public BigDecimal payPrice;
    public DateTime createTime;
    public DateTime deliveryTime;
    public DateTime outTime;
    public DateTime confirmTime;
    public DateTime completeTime;
    public DateTime cancelTime;
    public int orderStatus;
    public BigDecimal discountPrice;
    public int userId;
    public VendorInfoViewModel vendorInfo;
    public DeliveryAddressInfoViewModel deliveryAddressInfo;
    public OrderItemViewModel[] orderItems;

}
