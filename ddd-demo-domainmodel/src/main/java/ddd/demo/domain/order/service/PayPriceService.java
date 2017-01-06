package ddd.demo.domain.order.service;


import java.math.BigDecimal;

/**
 * 计算订单实际支付金额
 */
public class PayPriceService {

    public BigDecimal getPayPrice(BigDecimal totalPrice, BigDecimal discountPrice) {
        return totalPrice.subtract(discountPrice);
    }
}
