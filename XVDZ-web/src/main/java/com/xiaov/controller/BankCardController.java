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

    @RequestMapping("/admin/BankCard/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(BankCard bankCard) {

        try {
            bankCardService.save(bankCard);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/BankCard/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(BankCard bankCard) {

        try {
            bankCardService.update(bankCard);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/BankCard/page")
    @ResponseBody
    public Page<BankCard> page(Page<BankCard> bankCard) {

        try {

            return bankCardService.page(bankCard);
        } catch (Exception e) {
            Page<BankCard> page = new Page<BankCard>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/BankCard/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public BankCard getOne(BankCard bankCard) {
        try {
            return bankCardService.getOne(bankCard.getClass(), bankCard.getBkCdId());

        } catch (Exception e) {
            BankCard page = new BankCard();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
