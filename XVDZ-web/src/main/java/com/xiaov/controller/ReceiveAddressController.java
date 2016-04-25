package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.ReceiveAddress;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

    @RequestMapping("/admin/ReceiveAddress/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(ReceiveAddress receiveAddress) {

        try {
            receiveAddressService.save(receiveAddress);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/ReceiveAddress/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(ReceiveAddress receiveAddress) {

        try {
            receiveAddressService.update(receiveAddress);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/ReceiveAddress/deleteAjax")
    @ResponseBody
    public MessageBean deleteAjax(ReceiveAddress receiveAddress){

        try {
            receiveAddress=receiveAddressService.getOne(receiveAddress.getClass(), receiveAddress.getId());
            receiveAddressService.delete(receiveAddress);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/admin/ReceiveAddress/page")
    @ResponseBody
    public Page<ReceiveAddress> page(ReceiveAddress receiveAddress) {

        try {

            return receiveAddressService.page(receiveAddress);
        } catch (Exception e) {
            Page<ReceiveAddress> page = new Page<ReceiveAddress>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/ReceiveAddress/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public ReceiveAddress getOne(ReceiveAddress receiveAddress) {
        try {
            return receiveAddressService.getOne(receiveAddress.getClass(), receiveAddress.getId());

        } catch (Exception e) {
            ReceiveAddress page = new ReceiveAddress();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
