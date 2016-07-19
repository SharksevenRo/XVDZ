package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.ReceiveAddress;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.ReceiveAddressServiceImpl;
import com.xiaov.service.interfaces.ReceiveAddressService;
import com.xiaov.utils.LazyObjecUtil;
import com.xiaov.web.support.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.asm.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;

    @Autowired
    private ReceiveAddressServiceImpl receiveAddressServiceimpl;

    @RequestMapping("/auth/receiveAddress/save")
    @ResponseBody
    public MessageBean saveAjax(ReceiveAddress receiveAddress) {

        try {
            receiveAddress.setAddTime(new Date());
            receiveAddress.setAddDefault("0");
            receiveAddressService.save(receiveAddress);
            return new MessageBean(APPConstant.SUCCESS, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.SUCCESS, "添加失败");
        }
    }

    @RequestMapping("/auth/receiveAddress/update")
    @ResponseBody
    public MessageBean updateAjax(ReceiveAddress receiveAddress) {

        try {
            receiveAddressService.update(receiveAddress);
            return new MessageBean(APPConstant.SUCCESS, "修改成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "修改失败"+e.getMessage());
        }
    }

    @RequestMapping("/auth/receiveAddress/delete")
    @ResponseBody
    public MessageBean deleteAjax(ReceiveAddress receiveAddress) {

        try {
            receiveAddress = receiveAddressService.getOne(receiveAddress.getClass(), receiveAddress.getId());
            receiveAddressService.delete(receiveAddress);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/admin/receiveAddress/page")
    @ResponseBody
    public Page<ReceiveAddress> page(ReceiveAddress receiveAddress) {

        try {

            Page<ReceiveAddress> page = receiveAddressService.page(receiveAddress);
            page=LazyObjecUtil.LazyPageSetNull(page,"userInfo");
            return  page;
        } catch (Exception e) {
            Page<ReceiveAddress> page = new Page<ReceiveAddress>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/admin/receiveAddress/getOneAjax")
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

    @RequestMapping("auth/user/addresses")
    @ResponseBody
    public List<ReceiveAddress> getReceiveAddress(ReceiveAddress receiveAddress) {

        List<ReceiveAddress> result = receiveAddressServiceimpl.getByProperty("userInfo.id", receiveAddress.getUserInfo().getId());
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/auth/addresses/change/default")
    @ResponseBody
    public MessageBean chanageDefalut(ReceiveAddress receiveAddress) {

        try {
            if (receiveAddress.getAddDefault().equals("1")) {

                List<ReceiveAddress> addDefault = receiveAddressServiceimpl.getByProperty("addDefault", "1");
                if (addDefault.size() == 1) {
                    ReceiveAddress dreceiver = addDefault.get(0);
                    dreceiver.setAddDefault("0");
                    receiveAddressService.update(dreceiver);


                    receiveAddressService.update(receiveAddress);

                    return new MessageBean(APPConstant.SUCCESS, "修改成功");
                } else {
                    if(addDefault.size()==0){
                        receiveAddressService.update(receiveAddress);
                        return new MessageBean(APPConstant.SUCCESS, "修改成功");
                    }else{
                        return new MessageBean(APPConstant.ERROR, "修改错误");
                    }

                }
            }else{
                receiveAddressService.update(receiveAddress);
                return new MessageBean(APPConstant.SUCCESS, "修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MessageBean(APPConstant.SUCCESS, "修改成功");
    }

}
