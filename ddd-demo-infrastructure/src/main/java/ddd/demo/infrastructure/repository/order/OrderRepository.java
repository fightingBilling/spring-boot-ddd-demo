package ddd.demo.infrastructure.repository.order;

import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.domain.order.model.Order;
import org.apache.commons.lang.NotImplementedException;
import org.joda.time.DateTime;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单资源库
 */
public class OrderRepository implements IOrderRepository {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public long getNexOrderId() {
        return DateTime.now().getMillis();
    }

    @Override
    public void add(Order order) {
        this.sqlSessionTemplate.insert("Order.add", order);

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getId());

        for (OrderItem orderItem : order.getItems()) {
            map.remove("orderItem");
            map.put("orderItem", orderItem);
            this.sqlSessionTemplate.insert("Order.addItems", map);
        }
    }

    @Override
    public void update(Order order) {
        //TODO:更新订单
    }

    @Override
    public Order findBy(Long orderId) {
        return this.sqlSessionTemplate.selectOne("Order.findOrderOne", orderId);
    }

    @Override
    public void throwTest() {
        throw new NotImplementedException();
    }
}
