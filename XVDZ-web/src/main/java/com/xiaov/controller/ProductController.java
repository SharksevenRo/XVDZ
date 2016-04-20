package com.xiaov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Product;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/admin/product/saveAjax")
	@ResponseBody
	public MessageBean saveAjax(Product product){
		
		try {
			productService.save(product);
			return new MessageBean(APPConstant.SUCCESS, "上传成功");
		} catch (Exception e) {
			return new MessageBean(APPConstant.SUCCESS, "上传失败");
		}
	}
	@RequestMapping("/admin/product/updateAjax")
	@ResponseBody
	public MessageBean updateAjax(Product product){
		
		try {
			productService.update(product);
			return new MessageBean(APPConstant.SUCCESS, "上传成功");
		} catch (Exception e) {
			return new MessageBean(APPConstant.SUCCESS, "上传失败");
		}
	}
	@RequestMapping("/admin/product/page")
	@ResponseBody
	public Page<Product> page(Page<Product> product){
		
		try {
			
			return productService.page(product);
		} catch (Exception e) {
			Page<Product> page=new Page<Product>();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}
	
	@RequestMapping("/admin/product/getOneAjax")
	@ResponseBody
	public Product getOne(Product product){
		
		try {
			
			return productService.getOne(product.getClass(),product.getPdtId());
		} catch (Exception e) {
			Product page=new Product();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}
	@RequestMapping("/admin/product/picUploadAjax")
	@ResponseBody
	public MessageBean picUpload(Product product){
		
		return null;
	}
}
