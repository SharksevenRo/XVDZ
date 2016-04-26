package com.xiaov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.ProductDetail;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ProductDetailService;

@Controller
public class ProductDetailController {

	@Autowired
	private ProductDetailService productDetailService;
	
	 @RequestMapping("/admin/productDetail/saveAjax")
	    @ResponseBody
	    public MessageBean saveAjax(ProductDetail ProductDetail) {

	        try {
	            productDetailService.save(ProductDetail);
	            return new MessageBean(APPConstant.SUCCESS, "上传成功");
	        } catch (Exception e) {
	            return new MessageBean(APPConstant.SUCCESS, "上传失败");
	        }
	    }

	    @RequestMapping("/admin/productDetail/updateAjax")
	    @ResponseBody
	    public MessageBean updateAjax(ProductDetail productDetail) {

	        try {
	            productDetailService.update(productDetail);
	            return new MessageBean(APPConstant.SUCCESS, "上传成功");
	        } catch (Exception e) {
	            return new MessageBean(APPConstant.SUCCESS, "上传失败");
	        }
	    }

	    @RequestMapping("/admin/productDetail/page")
	    @ResponseBody
	    public Page<ProductDetail> page(ProductDetail productDetail) {
	        try {

	            return productDetailService.page(productDetail);
	        } catch (Exception e) {
	            Page<ProductDetail> page = new Page<ProductDetail>();
	            page.setCode(APPConstant.ERROR);
	            page.setMessage("服务器忙");
	            return page;
	        }
	    }

	    @RequestMapping(value = "/admin/productDetail/getOneAjax", method = RequestMethod.POST)
	    @ResponseBody
	    public ProductDetail getOne(ProductDetail productDetail) {
	        try {
	            return productDetailService.getOne(productDetail.getClass(), productDetail.getId());

	        } catch (Exception e) {
	            ProductDetail page = new ProductDetail();
	            page.setCode(APPConstant.ERROR);
	            page.setMessage("服务器忙");
	            return page;
	        }
	    }
}
