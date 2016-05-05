package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Dynamic;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.DynamicService;
import com.xiaov.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Controller
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private UserService userService;

    //获取所有
    @RequestMapping("/admin/dynamic/allDynamic")
    @ResponseBody
    public List<Dynamic> getAll() {
        //先声明关闭延迟加载的关联对象UserInfo
        String[] fields = new String[]{"userInfo"};
        return dynamicService.getAll();
    }

    @RequestMapping("/admin/dynamic/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Dynamic dynamic) {

        try {
            dynamicService.save(dynamic);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/dynamic/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Dynamic dynamic) {

        try {
            dynamicService.update(dynamic);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/dynamic/page")
    @ResponseBody
    public Page<Dynamic> page(Dynamic dynamic) {
        try {

            return dynamicService.page(dynamic);
        } catch (Exception e) {
            Page<Dynamic> page = new Page<Dynamic>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/dynamic/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Dynamic getOne(Dynamic dynamic) {
        try {
            return dynamicService.getOne(dynamic.getClass(), dynamic.getId());

        } catch (Exception e) {
            Dynamic page = new Dynamic();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/admin/dynamic/addAjax")
    @ResponseBody
    public MessageBean addAjax(Dynamic dynamic) {

        try {
            UserInfo userInfo = new UserInfo();
            dynamic.setUserInfo(userService.getOne(userInfo.getClass(), "1"));
            dynamicService.save(dynamic);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
}
