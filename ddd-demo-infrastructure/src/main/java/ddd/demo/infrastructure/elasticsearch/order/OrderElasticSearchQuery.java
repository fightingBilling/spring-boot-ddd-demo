package ddd.demo.infrastructure.elasticsearch.order;

import ddd.demo.application.order.EsWarningOrderQuery;
import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.application.order.WarningOrderDto;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.index.mapper.core.IntegerFieldMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class OrderElasticSearchQuery implements IOrderElasticSearchQuery {

    private final ElasticsearchTemplate template;

    public OrderElasticSearchQuery(ElasticsearchTemplate elasticsearchTemplate) {
        this.template = elasticsearchTemplate;
    }

    @Override
    public List<Map> findAll() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("test")
                .withTypes("demo")
                .withQuery(matchAllQuery())
                .withFilter(new BoolQueryBuilder().must(matchQuery("user", "中国")))
                .withSort(new FieldSortBuilder("_id").order(SortOrder.DESC))
                .build();
        return this.template.queryForList(searchQuery, Map.class);
    }

    @Override
    public void createMapping() {
        this.template.createIndex(EsData.class);

        EsData esData = new EsData();
        esData.setAge(1L);
        esData.setName("中国");
        esData.setId("100");


//        TypeQueryBuilder typeQueryBuilder = new TypeQueryBuilder().

        IndexQuery query = new IndexQueryBuilder().withId("400").withObject(esData).build();

        this.template.index(query);
    }

    @Override
    public void createWarningOrderType() {
        this.template.createIndex(EsWarningOrder.class);
    }

    @Override
    public void createWarningOrderDoc() {

        this.template.createIndex(EsWarningOrder.class);

        EsWarningOrder esWarningOrder1 = this.createEsWarningOrder(100L, new int[]{100, 200}, DateTime.now().plusDays(-2).toDate(), 10L, 1, 1);
        esWarningOrder1.setId("10010");
        EsWarningOrder esWarningOrder2 = this.createEsWarningOrder(101L, new int[]{100}, DateTime.now().plusDays(-2).toDate(), 11L, 2, 1);
        esWarningOrder2.setId("10111");
        EsWarningOrder esWarningOrder3 = this.createEsWarningOrder(102L, new int[]{100, 200, 300}, DateTime.now().toDate(), 10L, 1, 1);
        esWarningOrder3.setId("10210");
        EsWarningOrder esWarningOrder4 = this.createEsWarningOrder(103L, new int[]{100, 200}, DateTime.now().plusDays(-1).toDate(), 10L, 2, 1);
        esWarningOrder4.setId("10310");

        List<EsWarningOrder> esWarningOrderList = new ArrayList<>();
        esWarningOrderList.add(esWarningOrder1);
        esWarningOrderList.add(esWarningOrder2);
        esWarningOrderList.add(esWarningOrder3);
        esWarningOrderList.add(esWarningOrder4);

        esWarningOrderList.stream().forEach((item) -> {
            IndexQuery query = new IndexQueryBuilder().withId(item.getId()).withObject(item).build();
            this.template.index(query);
        });
    }

    @Override
    public List<WarningOrderDto> select(EsWarningOrderQuery query, int pageIndex, int pageSize, long vendorId) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                .withIndices("tjz").withTypes("EsWarningOrder")
                .withQuery(matchAllQuery())
                .withPageable(new PageRequest(pageSize * (pageIndex - 1), pageSize))
                .withSort(new FieldSortBuilder("waringTime").order(SortOrder.DESC));

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .must(termQuery("vendorId", vendorId))
                .must(termQuery("status", 1));

        if (query.getOrderId() > 0) {
            boolQueryBuilder.must(termQuery("orderId", query.getOrderId()));
        }
        if (query.getWarginType() > 0) {
            boolQueryBuilder.must(termQuery("waringType", query.getWarginType()));
        }
        builder.withFilter(boolQueryBuilder);

        SearchQuery searchQuery = builder.build();

        Page<EsWarningOrder> page = this.template.queryForPage(searchQuery, EsWarningOrder.class);

        return page.getContent().stream().map(s -> {
            WarningOrderDto warningOrderDto = new WarningOrderDto();

            warningOrderDto.setOrderMark(s.getOrderMark());
            warningOrderDto.setWaringTime(s.getWaringTime());
            warningOrderDto.setStatus(s.getStatus());
            warningOrderDto.setVendorId(s.getVendorId());
            warningOrderDto.setWaringType(s.getWaringType());
            warningOrderDto.setOrderId(s.getOrderId());

            return warningOrderDto;

        }).collect(Collectors.toList());

    }

    @Override
    public void updatesStatus(long orderId, long vendorId, int status) {
        String id = Long.toString(orderId) + Long.toString(vendorId);

        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setIndexName("tjz");
        updateQuery.setType("EsWarningOrder");
        updateQuery.setId(id);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.doc("status", status);
//        updateRequest.id(id);

        updateQuery.setUpdateRequest(updateRequest);

        this.template.update(updateQuery);
    }

    @Override
    public void clear() {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setIndex("tjz");
        deleteQuery.setType("EsWarningOrder");
        deleteQuery.setQuery(matchAllQuery());

        this.template.delete(deleteQuery);
        this.template.deleteIndex(EsWarningOrder.class);
    }

    private EsWarningOrder createEsWarningOrder(long orderId, int[] warningTypes, Date d, long vendorId, int status, int orderMark) {
        EsWarningOrder order1 = new EsWarningOrder();
        order1.setOrderId(orderId);
        order1.setWaringType(warningTypes);
        order1.setWaringTime(d);
        order1.setVendorId(vendorId);
        order1.setStatus(status);
        order1.setOrderMark(orderMark);
        return order1;
    }
}
