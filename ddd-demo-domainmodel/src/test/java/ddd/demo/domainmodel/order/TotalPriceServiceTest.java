package ddd.demo.domainmodel.order;

import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.service.TotalPriceService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算订单总额
 */
public class TotalPriceServiceTest {
    @Test
    public void getTotalPriceTest() {

        TotalPriceService service = new TotalPriceService();

        List<OrderItem> orderItemList = new ArrayList<>();

        orderItemList.add(new OrderItem( 100, 2, BigDecimal.valueOf(20.89D)));
        orderItemList.add(new OrderItem( 102, 4, BigDecimal.valueOf(20.13D)));

        BigDecimal actualMoney = service.getTotalPrice(orderItemList);

        boolean expectedTrue = actualMoney.setScale(2, BigDecimal.ROUND_HALF_DOWN).equals(BigDecimal.valueOf(122.3D).setScale(2, BigDecimal.ROUND_HALF_DOWN));

        Assert.assertTrue(expectedTrue);
    }

}
