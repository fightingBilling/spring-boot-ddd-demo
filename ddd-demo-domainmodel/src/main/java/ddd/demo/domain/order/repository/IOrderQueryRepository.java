package ddd.demo.domain.order.repository;

import ddd.demo.domain.order.model.Order;
import ddd.demo.domain.order.viewmodel.OrderViewModel;
import ddd.demo.domain.order.viewmodel.QueryViewModel;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

/**
 * 订单查询接口
 */
public interface IOrderQueryRepository {
    /**
     * 待出库列表
     *
     * @param venderId 商家ID
     * @return
     */
    List<OrderViewModel> readyOut(long venderId);

    /**
     * 待出库超时订单列表
     *
     * @param venderId 商家ID
     * @return
     */
    List<OrderViewModel> readyOutTimeOut(long venderId);

    /**
     * 添加询数据源数据
     *
     * @param order
     */
    void add(Order order);

    /**
     * 更新查询数据源数据
     *
     * @param order
     */
    void update(Order order);

    /**
     * 分页查询订单列表
     *
     * @param query
     * @param pageIndex
     * @param pageSize
     * @param totalRows
     * @return
     */
    List<OrderViewModel> pageList(QueryViewModel query, int pageIndex, int pageSize, MutableInt totalRows);
}
