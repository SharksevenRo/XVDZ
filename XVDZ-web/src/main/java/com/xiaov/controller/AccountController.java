package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Account;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yymao on 2016/4/25.
 */
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/admin/Account/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Account account) {

        try {
            accountService.save(account);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Account/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Account account) {

        try {
            accountService.update(account);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Account/deleteAjax")
    @ResponseBody
    public MessageBean deleteAjax(Account account) {

        try {
            account = accountService.getOne(account.getClass(), account.getId());
            accountService.delete(account);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/admin/Account/page")
    @ResponseBody
    public Page<Account> page(Page<Account> accountPage) {

        try {

            return accountService.page(accountPage);
        } catch (Exception e) {
            Page<Account> page = new Page<Account>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/Account/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Account getOne(Account account) {
        try {
            return accountService.getOne(account.getClass(), account.getId());

        } catch (Exception e) {
            Account page = new Account();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
