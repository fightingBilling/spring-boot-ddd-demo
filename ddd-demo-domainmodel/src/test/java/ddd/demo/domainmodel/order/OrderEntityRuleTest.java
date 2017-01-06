package ddd.demo.domainmodel.order;

import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.model.OrderBrokenRuleMessage;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class OrderEntityRuleTest {
    @Test
    public void orderIdTest() {
        Order o = new Order(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, null, null, null);

        Boolean vResult = o.validate();

        Assert.assertFalse(vResult);

        o.getBrokenRules().get(0).getName().equals(OrderBrokenRuleMessage.ORDER_ID_ERROR);
    }
    @Test
    public  void orderVendorInfoTest(){
//        Order o = new Order(1L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, null, null, null);
    }

}
