package ddd.demo.infrastructure.elasticsearch.order;

import ddd.demo.application.order.EsWarningOrderQuery;
import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.application.order.WarningOrderDto;

import java.util.List;
import java.util.Map;


public class MockOrderElasticSearchQuery implements IOrderElasticSearchQuery {
    @Override
    public List<Map> findAll() {
        return null;
    }

    public void createMapping() {
    }

    @Override
    public void createWarningOrderType() {

    }

    @Override
    public void createWarningOrderDoc() {

    }

    @Override
    public List<WarningOrderDto> select(EsWarningOrderQuery query, int pageIndex, int pageSize, long vendorId) {
        return null;
    }

    @Override
    public void updatesStatus(long orderId, long vendorId, int status) {

    }


    @Override
    public void clear() {

    }
}
