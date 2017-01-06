package ddd.demo.application.order;

import java.util.List;
import java.util.Map;


public interface IOrderElasticSearchQuery {

    List<Map> findAll();
    void createMapping();

    void createWarningOrderType();

    void createWarningOrderDoc();

    List<WarningOrderDto> select(EsWarningOrderQuery query, int pageIndex, int pageSize, long vendorId);

    void updatesStatus(long orderId,long vendorId,int status);

    void clear();
}
