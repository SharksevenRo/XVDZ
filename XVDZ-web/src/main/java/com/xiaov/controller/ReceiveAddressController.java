package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.ReceiveAddress;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/admin/ReceiveAddress/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(String reAddTo,String reAddTel,String reAddDet,String addDefault) {

        try {
        	//receiveAddress.setId(new CookieUtil(request).getValue("user", "id", true));
            ReceiveAddress receiveAddress = new ReceiveAddress();
            receiveAddress.setId("1");
            receiveAddress.setReAddTo(reAddTo);
            receiveAddress.setReAddTel(reAddTel);
            receiveAddress.setReAddDet(reAddDet);
            System.out.println(addDefault);
            if(addDefault.equals("true"))
                receiveAddress.setAddDefault(true);
            else
                receiveAddress.setAddDefault(false);
            System.out.println(receiveAddress.getAddDefault());
            receiveAddressService.save(receiveAddress);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
        	e.printStackTrace();
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
    @RequestMapping("admin/address/getUserAddress")
    @ResponseBody
    public List<ReceiveAddress> getReceiveAddress() {
        String values ="1";
        List<ReceiveAddress> result = receiveAddressServiceimpl.getByProperty("userInfo.id",values);
        try{
            result = LazyObjecUtil.LazySetNull(result,"userInfo");
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
