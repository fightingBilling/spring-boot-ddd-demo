package ddd.demo.infrastructure.elasticsearch.order;

import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.repository.IOrderQueryRepository;
import ddd.demo.domain.order.viewmodel.OrderViewModel;
import ddd.demo.domain.order.viewmodel.QueryViewModel;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
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

    @Override
    public List<OrderViewModel> pageList(QueryViewModel query, int pageIndex, int pageSize, MutableInt totalRows) {

        totalRows.setValue(100);

        return new ArrayList<>();
    }
}
