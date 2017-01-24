package ddd.demo.api.controllers;

import com.alibaba.fastjson.JSON;
import ddd.demo.application.order.OrderApplication;
import ddd.demo.domain.order.model.DeliveryAddressInfo;
import ddd.demo.domain.order.model.OrderItem;
import ddd.demo.domain.order.model.VendorInfo;
import ddd.demo.domain.order.viewmodel.QueryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @GetMapping("/create")
    @ResponseBody
    public String create() throws Exception {

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(1, 1, new BigDecimal("100")));


        this.orderApplication.create(new VendorInfo(1, "aaa"), 1, new BigDecimal("10"), orderItemList, new DeliveryAddressInfo("a", "b", "c"));

        return "OK";
    }

    /**
     * 订单详情
     *
     * @param orderId 订单ID
     * @return
     * @throws Exception
     */
    @GetMapping("/detail")
    @ResponseBody
    public String orderDetailById(@RequestParam(name = "orderId", defaultValue = "0") long orderId) throws Exception {

        return orderApplication.findById(orderId).result(s -> {

            Map<String, Object> map = new HashMap<>();

            map.put("orderId", s.getId());
            map.put("createTime", s.getCreateTime().toString());
            map.put("orderStatus", s.getOrderStatus().value());

            return JSON.toJSONString(map);
        });
    }

    /**
     * 订单出确认
     *
     * @param orderId 订单ID
     * @return
     */
    @PostMapping("/confirm")
    @ResponseBody
    public String confirm(@RequestParam(name = "orderId", defaultValue = "0") long orderId) {

        this.orderApplication.confirm(orderId);

        return "ok";
    }

    /**
     * 订单出库
     *
     * @param orderId 订单ID
     * @return
     */
    @PostMapping("/out")
    @ResponseBody
    public String out(@RequestParam(name = "orderId", defaultValue = "0") long orderId) throws Exception {
        this.orderApplication.out(orderId);

        return "ok";
    }

    /**
     * 订单发货
     *
     * @param orderId 订单ID
     * @return
     */
    @PostMapping("/delivery")
    @ResponseBody
    public String delivery(@RequestParam(name = "orderId", defaultValue = "0") long orderId) throws Exception {
        this.orderApplication.delivery(orderId);

        return "ok";
    }

    /**
     * 订单发货
     *
     * @param orderId 订单ID
     * @return
     */
    @PostMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestParam(name = "orderId", defaultValue = "0") long orderId) {
        this.orderApplication.cancel(orderId);

        return "ok";
    }

    /**
     * 分页查询订单列表
     *
     * @param queryViewModel
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */

    @GetMapping("/pagelist")
    @ResponseBody
    public Map<String, Object> pageOrderList(QueryViewModel queryViewModel, @RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) throws Exception {

        return this.orderApplication.pageList(queryViewModel, pageIndex, pageSize).result(l -> {

            Map<String, Object> map = new HashMap<>();
            map.put("totalRows", l.getTotalRows());
            map.put("pageIndex", pageIndex);
            map.put("pageSize", pageSize);
            map.put("rows", l.getRows());

            return map;
        });
    }
}
