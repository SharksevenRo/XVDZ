package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCoupan;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.DiscountCoupanServiceImpl;
import com.xiaov.service.interfaces.DiscountCoupanService;

import com.xiaov.utils.LazyObjecUtil;
import com.xiaov.web.support.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class DiscountCoupanController {
	@Autowired
    private DiscountCoupanService discountCoupanService;

    @Autowired
    private DiscountCoupanServiceImpl discountCoupanServiceImpl;

    @RequestMapping("/admin/DiscountCoupan/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(DiscountCoupan discountCoupan) {

        try {
            discountCoupanService.save(discountCoupan);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/DiscountCoupan/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(DiscountCoupan discountCoupan) {

        try {
            discountCoupanService.update(discountCoupan);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/DiscountCoupan/page")
    @ResponseBody
    public Page<DiscountCoupan> page(DiscountCoupan discountCoupan) {
        try {

            return discountCoupanService.page(discountCoupan);
        } catch (Exception e) {
            Page<DiscountCoupan> page = new Page<DiscountCoupan>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/DiscountCoupan/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public DiscountCoupan getOne(DiscountCoupan discountCoupan) {
        try {
            return discountCoupanService.getOne(discountCoupan.getClass(), discountCoupan.getId());

        } catch (Exception e) {
            DiscountCoupan page = new DiscountCoupan();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
    @RequestMapping("/admin/DiscountCoupan/getUserCoupan")
    @ResponseBody
    public List<DiscountCoupan> getUserCoupan(HttpServletRequest request) {
        try {
            CookieUtil util=new CookieUtil(request);
            String userid= util.getValue("user","userId",true);
            List<DiscountCoupan> result = discountCoupanServiceImpl.getByProperty("userInfo.id",userid);
            result = LazyObjecUtil.LazySetNull(result,"userInfo");
            return result;
        } catch (Exception e) {
            List<DiscountCoupan> list = null;
            return list;
        }
    }
}
