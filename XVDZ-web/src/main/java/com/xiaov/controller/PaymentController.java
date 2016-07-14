package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Payment;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.PaymentService;
import com.xiaov.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired


    @RequestMapping("/auth/payment/save")
    @ResponseBody
    public MessageBean saveAjax(Payment payment) {

        try {
            paymentService.save(payment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/auth/payment/update")
    @ResponseBody
    public MessageBean updateAjax(Payment payment) {

        try {
            paymentService.update(payment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
}
