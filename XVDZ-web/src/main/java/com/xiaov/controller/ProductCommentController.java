package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.ProductComment;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class ProductCommentController {
    @Autowired
    private ProductCommentService ProductCommentService;

    @RequestMapping("/admin/ProductComment/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(ProductComment productComment) {

        try {
            ProductCommentService.save(productComment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/ProductComment/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(ProductComment productComment) {

        try {
            ProductCommentService.update(productComment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/admin/ProductComment/page")
    @ResponseBody
    public Page<ProductComment> page(Page<ProductComment> productComment) {
        try {

            return ProductCommentService.page(productComment);
        } catch (Exception e) {
            Page<ProductComment> page = new Page<ProductComment>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/ProductComment/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public ProductComment getOne(ProductComment productComment) {
        try {
            return ProductCommentService.getOne(productComment.getClass(), productComment.getCmtId());

        } catch (Exception e) {
            ProductComment page = new ProductComment();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
