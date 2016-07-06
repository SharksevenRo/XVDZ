package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCode;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;

import com.xiaov.service.interfaces.DiscountCodeService;
import com.xiaov.utils.LazyObjecUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class DiscountCodeController {
    @Autowired
    private DiscountCodeService discountCodeService;

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

    @RequestMapping("/admin/discountCode/updateAjax")
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
        	page=LazyObjecUtil.LazyPageSetNull(page, new String []{"userInfoByGnrtUId","userInfoByProUId"});
            return page;
        } catch (Exception e) {
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/discountCode/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public DiscountCode getOne(DiscountCode discountCode) {
        try {
            return discountCodeService.getOne(discountCode.getClass(), discountCode.getId());

        } catch (Exception e) {
            DiscountCode page = new DiscountCode();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
