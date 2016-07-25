package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Payment;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yymao on 2016/5/5.
 */
@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @RequestMapping("/auth/payment/save")
    @ResponseBody
    public MessageBean saveAjax(Payment payment) {

        try {
            paymentService.save(payment);
            return new MessageBean(APPConstant.SUCCESS, "添加成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "添加失败"+e.getMessage());
        }
    }

    @RequestMapping("/auth/payment/update")
    @ResponseBody
    public MessageBean updateAjax(Payment payment) {

        try {
            paymentService.update(payment);
            return new MessageBean(APPConstant.SUCCESS, "更新成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "更新失败"+e.getMessage());
        }
    }
}
