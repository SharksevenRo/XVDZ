package com.xiaov.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaov.service.impl.ProductServiceImpl;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Product;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.utils.ImageCutModel;
import com.xiaov.utils.LazyObjecUtil;
import com.xiaov.utils.UploadFileUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductServiceImpl productServiceimpl;

	@RequestMapping("/admin/product/saveAjax")
	@ResponseBody
	public MessageBean saveAjax(Product product){
		
		try {
			product.setAddTime(new Date());
			productService.save(product);
			return new MessageBean(APPConstant.SUCCESS, "上传成功");
		} catch (Exception e) {
			e.printStackTrace();
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
	public Page<Product> page(Product product){
		
		try {
			
		Page<Product> page = productService.page(product);
		
		String [] fileName={"material","productType"};
		page = LazyObjecUtil.LazyPageSetNull(page, fileName);
		
		return page;
		} catch (Exception e) {
			e.printStackTrace();
			Page<Product> page=new Page<Product>();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}
	
	@RequestMapping("/admin/product/deleteAjax")
	@ResponseBody
	public MessageBean deleteAjax(Product product){

		try {
			product=productService.getOne(product.getClass(), product.getId());
			productService.delete(product);
			return new MessageBean(APPConstant.ERROR, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "删除失败");
		}
	}

	@RequestMapping("/admin/product/getOneAjax")
	@ResponseBody
	public Product getOne(Product product){
		
		try {
			
			return productService.getOne(product.getClass(),product.getId());
		} catch (Exception e) {
			Product page=new Product();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}
	@RequestMapping("/admin/product/picUploadAjax.do")
	@ResponseBody
	public MessageBean saveProductImage(ImageCutModel imgCut,HttpServletRequest request){
		
		System.out.println("11");
		try {
			if(imgCut!=null){
				//新文件名
				String newFileName = new Date().getTime()+".jpg";
				String contextPath = request.getRealPath("/");
				//获取公司ID
				//设置正常保存路径域
				String[] normalScope = {"normal",imgCut.getType(),new Date().getYear()+"",new Date().getMonth()+"","product"}; 
				//创建图片上传对象，这是正常路径
				UploadFileUtil fileUtil = new UploadFileUtil(contextPath,normalScope);
				
				//设置压缩保存路径域
				String[] compressScope = {"normal",imgCut.getType(),new Date().getYear()+"",new Date().getMonth()+"","product"};
				//压缩
				String compressTargetPath = fileUtil.savePicWithCompress(imgCut.getImageFile(), newFileName,compressScope, false);
				String dbCompressUrl = fileUtil.getSmallRelativeFolderPath()+newFileName;
				//剪切保存域
				String[] cutScope = {"normal",imgCut.getType(),new Date().getYear()+"",new Date().getMonth()+"","product"};
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
	@RequestMapping("/admin/product/newProduct.do")
	@ResponseBody
	public Page<Product> newProduct(Product product){
		/*
		try {
			product.setSidx("addTime");
			product.setSord("DESC");
			Page<Product> page = productService.page(product);

			String [] fileName={"material","productType"};
			page = LazyObjecUtil.LazyPageSetNull(page, fileName);

			return page;
		} catch (Exception e) {
			e.printStackTrace();
			Page<Product> page=new Page<Product>();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}*/
		Page<Product> page = new Page<Product>();
		try {
			product.setSidx("addTime");
			product.setSord("DESC");
			page= productService.page(product);
			LazyObjecUtil.LazyPageSetNull(page, new String[]{"material","productType"});
			return page;
		} catch (Exception e) {

			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}

	}

	@RequestMapping("/admin/product/hotProduct.do")
	@ResponseBody
	public Page<Product> hotProduct(Product product){

		try {
			product.setSidx("pdtShareCount");
			product.setSord("DESC");
			Page<Product> page = productService.page(product);

			String [] fileName={"material","productType"};
			page = LazyObjecUtil.LazyPageSetNull(page, fileName);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			Page<Product> page=new Page<Product>();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}

	}

	@RequestMapping(value = "/admin/product/searchProduct")
	@ResponseBody
	public List<Product> searchProduct(String searchText){

		Criterion eq1 = Restrictions.or(Restrictions.like("pdtName", "%"+searchText+"%"),Restrictions.like("remark", "%"+searchText+"%"));
		Criterion [] criterions ={eq1};
		try{
			List<Product> productList = productServiceimpl.searchProduct(criterions);

			return productList;

		}catch (Exception e){
			System.out.println("系统错误!");
			return null;
		}

	}
}
