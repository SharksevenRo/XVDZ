package com.xiaov.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.xiaov.utils.PropertiesUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Material;
import com.xiaov.model.Product;
import com.xiaov.model.ProductDetail;
import com.xiaov.model.Types;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.TypesImpl;
import com.xiaov.service.interfaces.MaterialService;
import com.xiaov.service.interfaces.TypesService;
import com.xiaov.utils.CompressPicUtil;
import com.xiaov.utils.LazyObjecUtil;
import com.xiaov.utils.Pinyin4jUtil;

@Controller
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private TypesService typesService;
	
	private String path;
	private static int bufSize = 512; // size of bytes
	private byte[] buf;
	private int readedBytes;


	@RequestMapping("/admin/material/saveAjax")
	@ResponseBody
	public MessageBean saveAjax(Material Material) {

		try {
			materialService.save(Material);
			return new MessageBean(APPConstant.SUCCESS, "上传成功");
		} catch (Exception e) {
			return new MessageBean(APPConstant.ERROR, "上传失败");
		}
	}

	@RequestMapping("/admin/material/updateAjax")
	@ResponseBody
	public MessageBean updateAjax(Material Material) {

		try {
			materialService.update(Material);
			return new MessageBean(APPConstant.SUCCESS, "上传成功");
		} catch (Exception e) {
			return new MessageBean(APPConstant.ERROR, "上传失败");
		}
	}

	@RequestMapping("/admin/material/deleteAjax")
	@ResponseBody
	public MessageBean deleteAjax(Material Material) {

		try {
			Material = materialService.getOne(Material.getClass(), Material.getId());
			materialService.delete(Material);
			return new MessageBean(APPConstant.SUCCESS, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "删除失败");
		}
	}

	@RequestMapping("/admin/material/page")
	@ResponseBody
	public Page<Material> page(Material Material) {
		Page<Material> page = new Page<Material>();
		try {

			page = materialService.page(Material);
			LazyObjecUtil.LazyPageSetNull(page, new String[] { "orderDetail", "discountCoupan", "dbTypes" });
			return page;
		} catch (Exception e) {

			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}

	@RequestMapping(value = "/admin/material/getOneAjax", method = RequestMethod.POST)
	@ResponseBody
	public Material getOne(Material Material) {
		try {
			return materialService.getOne(Material.getClass(), Material.getId());

		} catch (Exception e) {
			Material page = new Material();
			page.setCode(APPConstant.ERROR);
			page.setMessage("服务器忙");
			return page;
		}
	}
	@ResponseBody
	@RequestMapping("/admin/material/uploadByZip")
	public MessageBean uploadProductByZip(MultipartFile zip, HttpServletRequest request) {
		ZipFile zipFile = null;
		InputStream inputStream = null;
		FileOutputStream fileOut = null;
		File file=null;

		try {
			path = PropertiesUtils.getValue("file.designer");
			
			System.out.println(path);
			inputstreamtofile(zip.getInputStream());
			zipFile = new ZipFile(path + "temp.zip", "gbk");
			for (Enumeration<? extends ZipEntry> e = zipFile.getEntries(); e.hasMoreElements();) {
				ZipEntry entry = e.nextElement();
				System.out.println("文件名:" + entry.getName() + ", 内容如下:");
				Types types=new Types();
				Types newTypes;
				types.setId("16");
				if (entry.getName().toLowerCase().endsWith(".png")) {

					// 文件路径分隔
					String[] split = entry.getName().split("[/]");
					
					for (int i = 1; i < split.length-1; i++) {
						newTypes=new Types();
						newTypes.setParentType(types);
						newTypes.setTypeName(split[i]);
						List<Types> loadAll = typesService.loadAll(newTypes);
						if(loadAll.size()<=0){
							newTypes.setAddTime(new Date());
							typesService.save(newTypes);
						}else{
							newTypes=loadAll.get(0);
						}
						
						types=newTypes;
					}
					
					
					// 分隔颜色和正反面标记
					String[] split2 = split[3].split("[_]");
					
					Material material=new Material();
					material.setDbTypes(types);
					material.setMeterialName(split2[0]);
					if(split2.length==2){
						material.setPrice(Double.parseDouble(split2[1].replace(".png", "")));
					}
					//临时文件路径
					String tempPath = "images/material/original/" + Pinyin4jUtil.spellNoneTone(split[1]).replace("ü", "")+"/"+Pinyin4jUtil.spellNoneTone(split[2]).replace("ü", "");
					//压缩文件路径
					String basePath = "images//material/compress/"+ Pinyin4jUtil.spellNoneTone(split[1]).replace("ü", "")+"/"+Pinyin4jUtil.spellNoneTone(split[2]).replace("ü", "");
					//创建文件夹和文件
					file= new File(path+tempPath );
					if (!file.exists()) {
						file.mkdirs();
					}
					file = new File(path+basePath );
					if (!file.exists()) {
						file.mkdirs();
					}
					String fileName= UUID.nameUUIDFromBytes(split2[0].getBytes()) + ".png";
					file = new File(path+basePath+"/");
					if (!file.exists()) {
						file.createNewFile();
					}
					file = new File(path+tempPath+"/"+fileName);
					if (!file.exists()) {
						file.createNewFile();
					}
					//获取压缩包中的图片
					inputStream = zipFile.getInputStream(entry);
					fileOut = new FileOutputStream(file);
					this.buf = new byte[this.bufSize];
					//写到临时文件
					while ((this.readedBytes = inputStream.read(this.buf)) > 0) {
						fileOut.write(this.buf, 0, this.readedBytes);
					}
					fileOut.flush();
					fileOut.close();
					
			
					//文件压缩
					CompressPicUtil mypic = new CompressPicUtil();
					mypic.resizePNG(path+tempPath+"/"+fileName,  path+basePath+"/"+fileName, 200, 200, true);
					
					material.setAddTime(new Date());
					material.setUrl(basePath+"/"+fileName);
					material.setOriginalUrl(basePath+"/"+fileName);
					materialService.save(material);
					
				}
			}
			return new MessageBean(APPConstant.ERROR, "请上传PNG格式文件");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "请上传PNG格式文件");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					zipFile = null;
				}
			}
			if (inputStream != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
					zipFile = null;
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
					fileOut = null;
				}
			}
			file.delete();
		}
		
	}

	public void inputstreamtofile(InputStream ins) {
		String temp = path + "temp.zip";
		File file = new File(temp);
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					os = null;
				}
			}
		}

	}
}
