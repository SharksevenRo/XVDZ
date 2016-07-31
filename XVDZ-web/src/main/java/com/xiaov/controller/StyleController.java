package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Product;
import com.xiaov.model.Style;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.StyleService;
import com.xiaov.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Controller
public class StyleController {
    @Autowired
    private StyleService styleService;
    @Autowired
    private UserService userService;

    

    @RequestMapping("/admin/style/save")
    @ResponseBody
    public MessageBean saveAjax(Style style) {

        try {
            style.setAddTime(new Date());
            styleService.save(style);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }

    @RequestMapping("/admin/style/update")
    @ResponseBody
    public MessageBean updateAjax(Style style) {

        try {
            styleService.update(style);
            return new MessageBean(APPConstant.ERROR, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/style/page")
    @ResponseBody
    public Page<Style> page(Style style) {
        try {
            List<Style> styles = styleService.loadAll(style);
            style.setResult(styles);
            style.setCode(APPConstant.SUCCESS);
            return style;
        } catch (Exception e) {
            style.setCode(APPConstant.ERROR);
            style.setMessage("服务器忙");
            return style;
        }
    }

    @RequestMapping("/admin/style/getOne")
    @ResponseBody
    public Style getOne(Style style) {
        try {
            return styleService.getOne(style.getClass(), style.getId());
        } catch (Exception e) {
            Style page = new Style();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
    @RequestMapping("/admin/style/delete")
    @ResponseBody
    public MessageBean deleteAjax(Style style) {

        try {
            style = styleService.getOne(style.getClass(), style.getId());
            styleService.delete(style);
            return new MessageBean(APPConstant.SUCCESS, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }
}
