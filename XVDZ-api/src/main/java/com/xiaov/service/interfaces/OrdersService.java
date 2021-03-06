package com.xiaov.service.interfaces;

import com.xiaov.model.Orders;
import com.xiaov.service.BaseService;

import java.util.List;

/**
 * Created by zouziyang on 4/12/16.
 */
public interface OrdersService extends BaseService<Orders> {


    public Orders getOrderDetail(Orders orders);
    public List<Orders> pageOrder(Orders orders);

}
