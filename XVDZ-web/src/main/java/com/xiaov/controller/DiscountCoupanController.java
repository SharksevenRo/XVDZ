package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCoupan;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.DiscountCoupanServiceImpl;
import com.xiaov.service.interfaces.DiscountCoupanService;

import com.xiaov.utils.LazyObjecUtil;
import com.xiaov.web.support.CookieUtil;
import oracle.jrockit.jfr.events.DynamicValueDescriptor;
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

    @RequestMapping("/admin/discountCoupan/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(DiscountCoupan discountCoupan) {

        try {
            discountCoupanService.save(discountCoupan);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/discountCoupan/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(DiscountCoupan discountCoupan) {

        try {
            discountCoupanService.update(discountCoupan);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/discountCoupan/page")
    @ResponseBody
    public List<DiscountCoupan> page(DiscountCoupan discountCoupan) {
        List<DiscountCoupan> page;
        try {
            page= discountCoupanService.loadAll(discountCoupan);
            page=LazyObjecUtil.LazySetNull(page,"userInfo");
            return page;
        } catch (Exception e) {
          return null;
        }
    }

    @RequestMapping(value = "/admin/discountCoupan/getOneAjax", method = RequestMethod.POST)
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
    @RequestMapping("/auth/discountCoupan/getUserCoupan")
    @ResponseBody
    public Page<DiscountCoupan> getUserCoupan(UserInfo user) {
        Page<DiscountCoupan> page;
        try {

            DiscountCoupan discountCoupan=new DiscountCoupan();
            discountCoupan.setUserInfo(user);
            page = discountCoupanServiceImpl.page(discountCoupan);
            page = LazyObjecUtil.LazyPageSetNull(page,"userInfo");
            return page;
        } catch (Exception e) {
            DiscountCoupan discountCoupan=new DiscountCoupan();
            discountCoupan.setCode(APPConstant.ERROR);
            discountCoupan.setMessage("请求错误"+e.getMessage());
            return discountCoupan;

        }
    }
}
