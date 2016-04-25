package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Advertisment;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class AdvertismentController {
    @Autowired
    private AdvertismentService advertismentService;

    @RequestMapping("/admin/Advertisment/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Advertisment advertisment) {

        try {
            advertismentService.save(advertisment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Advertisment/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Advertisment advertisment) {

        try {
            advertismentService.update(advertisment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/Advertisment/deleteAjax")
    @ResponseBody
    public MessageBean deleteAjax(Advertisment advertisment){

        try {
            advertisment=advertismentService.getOne(advertisment.getClass(), advertisment.getId());
            advertismentService.delete(advertisment);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/admin/Advertisment/page")
    @ResponseBody
    public Page<Advertisment> page(Page<Advertisment> advertisment) {

        try {

            return advertismentService.page(advertisment);
        } catch (Exception e) {
            Page<Advertisment> page = new Page<Advertisment>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/Advertisment/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Advertisment getOne(Advertisment advertisment) {
        try {
            return advertismentService.getOne(advertisment.getClass(), advertisment.getAddId());

        } catch (Exception e) {
            Advertisment page = new Advertisment();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
