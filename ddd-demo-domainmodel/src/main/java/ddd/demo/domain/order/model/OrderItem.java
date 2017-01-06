package ddd.demo.domain.order.model;

import java.math.BigDecimal;

/**
 * 订单明细
 */
public class OrderItem {
    /**
     * SkuId
     */
    private int skuId;
    /**
     * 商品数量
     */
    private int number;
    /**
     * 商品单价
     */
    private BigDecimal price;

    public OrderItem(int skuId, int number, BigDecimal price) {
        this.setSkuId(skuId);
        this.setNumber(number);
        this.setPrice(price);
    }

    public int getSkuId() {
        return skuId;
    }

    private void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price == null ? BigDecimal.ZERO.setScale(2) : price.setScale(2);
    }

    public int getNumber() {
        return number;
    }

    private void setNumber(int number) {
        this.number = number;
    }
}
