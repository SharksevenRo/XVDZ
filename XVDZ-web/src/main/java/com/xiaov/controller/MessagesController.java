package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Messages;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class MessagesController {
    @Autowired
    private MessagesService messagesService;

    @RequestMapping("/admin/messages/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Messages messages) {

        try {
            messagesService.save(messages);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/messages/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Messages messages) {

        try {
            messagesService.update(messages);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/messages/page")
    @ResponseBody
    public Page<Messages> page(Messages messages) {

        try {

            return messagesService.page(messages);
        } catch (Exception e) {
            Page<Messages> page = new Page<Messages>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/messages/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Messages getOne(Messages messages) {
        try {
            return messagesService.getOne(messages.getClass(), messages.getId());

        } catch (Exception e) {
            Messages page = new Messages();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
