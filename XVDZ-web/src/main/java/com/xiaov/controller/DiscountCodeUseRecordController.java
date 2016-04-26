package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCodeUseRecord;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.DiscountCodeUseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class DiscountCodeUseRecordController {
    @Autowired
    private DiscountCodeUseRecordService discountCodeUseRecordService;

    @RequestMapping("/admin/DiscountCodeUseRecord/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(DiscountCodeUseRecord discountCodeUseRecord) {

        try {
            discountCodeUseRecordService.save(discountCodeUseRecord);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/DiscountCodeUseRecord/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(DiscountCodeUseRecord discountCodeUseRecord) {

        try {
            discountCodeUseRecordService.update(discountCodeUseRecord);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/DiscountCodeUseRecord/page")
    @ResponseBody
    public Page<DiscountCodeUseRecord> page(DiscountCodeUseRecord discountCodeUseRecord) {

        try {

            return discountCodeUseRecordService.page(discountCodeUseRecord);
        } catch (Exception e) {
            Page<DiscountCodeUseRecord> page = new Page<DiscountCodeUseRecord>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/DiscountCodeUseRecord/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public DiscountCodeUseRecord getOne(DiscountCodeUseRecord discountCodeUseRecord) {
        try {
            return discountCodeUseRecordService.getOne(discountCodeUseRecord.getClass(), discountCodeUseRecord.getDisCodeId());

        } catch (Exception e) {
            DiscountCodeUseRecord page = new DiscountCodeUseRecord();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
