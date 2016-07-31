package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCode;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;

import com.xiaov.service.interfaces.DiscountCodeService;
import com.xiaov.service.interfaces.UserService;
import com.xiaov.utils.LazyObjecUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class DiscountCodeController {
    @Autowired
    private DiscountCodeService discountCodeService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/discountCode/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(DiscountCode discountCode) {

        try {
            discountCodeService.save(discountCode);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/discountCode/update")
    @ResponseBody
    public MessageBean updateAjax(DiscountCode discountCode) {

        try {
            discountCodeService.update(discountCode);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
        	e.printStackTrace();
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
    @RequestMapping("/auth/discountCode/transfer")
    @ResponseBody
    public MessageBean transfer(DiscountCode discountCode) {

        try {
            if(discountCode.getId()==null){
                return new MessageBean(APPConstant.ERROR, "转让错误，优惠码id为空");
            }
            List<UserInfo> phone = userService.getByProperty("usTel", discountCode.getTowho());
            if(phone.size()!=1){
                return new MessageBean(APPConstant.ERROR, "转让错误，请确认手机号码是否正确");
            }
            if(!phone.get(0).getTypeId().equals("user.salesman")){
                return new MessageBean(APPConstant.ERROR, "转让错误，你要转让的用户不是业务员");
            }
            discountCode.setSalesman(phone.get(0).getId());

            discountCodeService.update(discountCode);
            return new MessageBean(APPConstant.SUCCESS, "转让成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.SUCCESS, "服务器异常"+e.getMessage());
        }
    }

    @RequestMapping("/admin/discountCode/deleteAjax")
    @ResponseBody
    public MessageBean deleteAjax(DiscountCode discountCode){

        try {
            discountCode=discountCodeService.getOne(discountCode.getClass(), discountCode.getId());
            discountCodeService.delete(discountCode);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/admin/discountCode/page")
    @ResponseBody
    public Page<DiscountCode> page(DiscountCode discountCode) {
    	  Page<DiscountCode> page = new Page<DiscountCode>();
        try {

        	page=discountCodeService.page(discountCode);
            page.setCode(APPConstant.SUCCESS);
            return page;
        } catch (Exception e) {
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/auth/discountCode/getOne")
    @ResponseBody
    public Page<DiscountCode> getOne(UserInfo user) {
        try {
            DiscountCode code=new DiscountCode();
            code.setSalesman(user.getId());
            Page<DiscountCode> page = discountCodeService.page(code);
            if(page.getResult().size()==1){
                return page;
            }else{
                code.setCode(APPConstant.SUCCESS);
                code.setMessage("数据异常，你存在多个优惠码或者没有优惠码，请联系管理员");
               return code;
            }
        } catch (Exception e) {
            DiscountCode page = new DiscountCode();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
