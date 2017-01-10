package ddd.demo.infrastructure.elasticsearch.order;

import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.repository.IOrderQueryRepository;
import ddd.demo.domain.order.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;


public class OrderQueryRepository implements IOrderQueryRepository {

    @Autowired
    private ElasticsearchTemplate template;

    @Override
    public List<OrderViewModel> readyOut(long venderId) {
        return null;
    }

    @Override
    public List<OrderViewModel> readyOutTimeOut(long venderId) {
        return null;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public void update(Order order) {

    }
}
