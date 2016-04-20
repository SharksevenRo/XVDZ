package com.xiaov.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Product;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.utils.ImageCutModel;
import com.xiaov.utils.UploadFileUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	public ProductController(){
		System.out.println("11111");
	}
	
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
	
	@RequestMapping(value="/admin/product/getOneAjax",method=RequestMethod.POST)
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
	public MessageBean saveProductImage(ImageCutModel imgCut,String sort,HttpServletRequest request){
		
		System.out.println("11");
		try {
			if(imgCut!=null){
				//新文件名
				String newFileName = new Date().getTime()+".jpg";
				String contextPath = request.getRealPath("/");
				//获取公司ID
				//设置正常保存路径域
				String[] normalScope = {"normal",sort,new Date().getYear()+"",new Date().getMonth()+"","product"}; 
				//创建图片上传对象，这是正常路径
				UploadFileUtil fileUtil = new UploadFileUtil(contextPath,normalScope);
				
				//设置压缩保存路径域
				String[] compressScope = {"normal",sort,new Date().getYear()+"",new Date().getMonth()+"","product"};
				//压缩
				String compressTargetPath = fileUtil.savePicWithCompress(imgCut.getImageFile(), newFileName,compressScope, false);
				String dbCompressUrl = fileUtil.getSmallRelativeFolderPath()+newFileName;
				//剪切保存域
				String[] cutScope = {"normal",sort,new Date().getYear()+"",new Date().getMonth()+"","product"};
				//剪切
				String cutTargetPath = fileUtil.cutImg(imgCut, newFileName ,cutScope);
				String dbCutUrl = fileUtil.getCutRelativeFolderPath()+newFileName;
				
				if(compressTargetPath!=null && dbCutUrl != null){
					
					return new MessageBean(APPConstant.SUCCESS, "上传成功");
				}else{
					return new MessageBean(APPConstant.SUCCESS, "上传失败");
				}
			}else{
				return new MessageBean(APPConstant.SUCCESS, "上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.SUCCESS, "上传失败");
		}
	}

}
