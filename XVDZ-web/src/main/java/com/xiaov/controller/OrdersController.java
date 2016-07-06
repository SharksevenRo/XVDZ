package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.OrdersService;
import com.xiaov.utils.LazyObjecUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/admin/orders/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Orders orders) {

        try {
            ordersService.save(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/orders/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Orders orders) {

        try {
            ordersService.update(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
    @RequestMapping("/admin/orders/deleteAjax")
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

    @RequestMapping("/admin/orders/page")
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

    @RequestMapping(value = "/admin/orders/getOneAjax", method = RequestMethod.POST)
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
}
