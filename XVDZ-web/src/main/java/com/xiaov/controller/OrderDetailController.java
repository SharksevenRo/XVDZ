package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.OrderDetail;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/admin/OrderDetail/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(OrderDetail orderDetail) {

        try {
            orderDetailService.save(orderDetail);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/OrderDetail/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(OrderDetail orderDetail) {

        try {
            orderDetailService.update(orderDetail);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/OrderDetail/page")
    @ResponseBody
    public Page<OrderDetail> page(Page<OrderDetail> orderDetail) {
        try {

            return orderDetailService.page(orderDetail);
        } catch (Exception e) {
            Page<OrderDetail> page = new Page<OrderDetail>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/OrderDetail/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public OrderDetail getOne(OrderDetail orderDetail) {
        try {
            return orderDetailService.getOne(orderDetail.getClass(), orderDetail.getId());

        } catch (Exception e) {
            OrderDetail page = new OrderDetail();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
