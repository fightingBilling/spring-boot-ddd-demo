package ddd.demo.application.order;

import ddd.demo.application.ApplicationConfig;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.infrastructure.elasticsearch.ElasticsSearchConfig;
import ddd.demo.infrastructure.repository.RepositoryConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderApplicationTest extends BaseTest {

    @Autowired
    private OrderApplication orderApplication;

    @Test
    public void addOrderTest() {

        VendorInfo vendorInfo = new VendorInfo(1, "中国");

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem0 = new OrderItem(1, 1, new BigDecimal("23"));
        OrderItem orderItem1 = new OrderItem(2, 2, new BigDecimal("23"));

        orderItemList.add(orderItem0);
        orderItemList.add(orderItem1);

        DeliveryAddressInfo deliveryAddressInfo = new DeliveryAddressInfo("a", "1111", "北京市朝阳区");

        this.orderApplication.create(vendorInfo, 1, new BigDecimal("12"), orderItemList, deliveryAddressInfo);

        

    }

    @Test
    public void updateOrderTest() {

    }
}
