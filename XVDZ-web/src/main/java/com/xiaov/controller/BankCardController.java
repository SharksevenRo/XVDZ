package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.BankCard;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    @RequestMapping("/admin/bankCard/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(BankCard bankCard) {

        try {
            bankCardService.save(bankCard);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/bankCard/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(BankCard bankCard) {

        try {
            bankCardService.update(bankCard);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/bankCard/page")
    @ResponseBody
    public Page<BankCard> page(BankCard bankCard) {

        try {

            return bankCardService.page(bankCard);
        } catch (Exception e) {
            Page<BankCard> page = new Page<BankCard>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/bankCard/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public BankCard getOne(BankCard bankCard) {
        try {
            return bankCardService.getOne(bankCard.getClass(), bankCard.getId());

        } catch (Exception e) {
            BankCard page = new BankCard();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
