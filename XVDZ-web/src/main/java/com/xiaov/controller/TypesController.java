package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Account;
import com.xiaov.model.Types;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AccountService;
import com.xiaov.service.interfaces.TypesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yymao on 2016/4/25.
 */
public class TypesController {
    @Autowired
    private TypesService typesService;

    /**
     * 
     * @Description:添加类型
     * @param types
     * @return MessageBean
     * @throws
     */
    @RequestMapping("/admin/types/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Types types) {

        try {
        	typesService.save(types);
            return new MessageBean(APPConstant.SUCCESS, "添加成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "添加失败");
        }
    }

    /**
     * 
     * @Description:修改类型
     * @param types
     * @return MessageBean
     * @throws
     */
    @RequestMapping("/admin/types/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Types types) {

        try {
        	typesService.update(types);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }
    /**
     * 
     * @Description:删除类型
     * @param types
     * @return MessageBean
     * @throws
     */
    @RequestMapping("/admin/types/deleteAjax")
    @ResponseBody
    public MessageBean deleteAjax(Types types) {

        try {
        	types = typesService.getOne(types.getClass(), types.getId());
            typesService.delete(types);
            return new MessageBean(APPConstant.SUCCESS, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }
    /**
     * 
     * @Description:根据ID查询类型
     * @param types
     * @return Types
     * @throws
     */
    @RequestMapping(value = "/admin/types/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Types getOne(Types types) {
        try {
            return typesService.getOne(types.getClass(), types.getId());
        } catch (Exception e) {
        	Types page = new Types();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
}
