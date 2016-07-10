package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.OrderDetail;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.OrdersService;
import com.xiaov.utils.LazyObjecUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    public final static ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/auth/orders/save")
    @ResponseBody
    public MessageBean saveAjax(Orders orders) {

        try {
            JavaType javaType = getCollectionType(ArrayList.class, OrderDetail.class);
            List<OrderDetail> orderDetails =  (List<OrderDetail>)mapper.readValue(orders.getMutiType(), javaType);
            orders.setOrderDetails(orderDetails);
            ordersService.save(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/auth/orders/update")
    @ResponseBody
    public MessageBean updateAjax(Orders orders) {

        try {
            ordersService.update(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
    @RequestMapping("/auth/orders/delete")
    @ResponseBody
    public MessageBean deleteAjax(Orders orders){

        try {
            orders=ordersService.getOne(orders.getClass(), orders.getId());
            ordersService.delete(orders);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/auth/orders/page")
    @ResponseBody
    public Page<Orders> page(Orders orders) {
    	Page<Orders> page = new Page<Orders>();
        try {

        	page= ordersService.page(orders);
        	LazyObjecUtil.LazyPageSetNull(page, new String[]{"orderDetail","discountCoupan","dbTypes"});
        	return page;
        } catch (Exception e) {
            
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/auth/orders/getOne")
    @ResponseBody
    public Orders getOne(Orders orders) {
        try {
            return ordersService.getOne(orders.getClass(), orders.getId());

        } catch (Exception e) {
            Orders page = new Orders();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/auth/orders/page/detail")
    @ResponseBody
    public Page<Orders> pageDetail(Orders orders) {
        Page<Orders> page = new Page<Orders>();
        try {
           page.setResult(ordersService.getOrderDetai(orders));
            return page;
        } catch (Exception e) {
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
