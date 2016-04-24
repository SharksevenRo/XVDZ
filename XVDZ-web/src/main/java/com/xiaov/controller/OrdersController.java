package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.OrdersService;
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

    @RequestMapping("/admin/Orders/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Orders orders) {

        try {
            ordersService.save(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Orders/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Orders orders) {

        try {
            ordersService.update(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Orders/page")
    @ResponseBody
    public Page<Orders> page(Page<Orders> orders) {

        try {

            return ordersService.page(orders);
        } catch (Exception e) {
            Page<Orders> page = new Page<Orders>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/Orders/getOneAjax", method = RequestMethod.POST)
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
