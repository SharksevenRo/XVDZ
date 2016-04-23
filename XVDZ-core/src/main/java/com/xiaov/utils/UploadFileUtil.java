package com.xiaov.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;


/**
 * @类名称:UploadFileUtil
 * @类描述:
 * @创建时间:2016-3-28下午5:52:53
 * @作者: 龙华辉
 * @修改人:龙华辉
 * @修改时间:2016-3-28下午5:52:53
 * @修改备注:
 * @版本:v1.0
 */
public class UploadFileUtil {

	private static final int PIC_WITH_TIMESTAMP = 1;
	private static final int PIC_WITH_FORMATDATE = 2;
	private static final int PIC_WITH_UUID = 3;
	

	private String contextPath;
	private String[] scope;// {"normal",3,"product"}或者{"small",3,"product"}或者{"cut",3,"product"}
	
	//公司原图的文件夹相对路径为：images/normal/3/...
	private String normalRelativeFolderPath = "";
	//公司压缩图的文件夹相对路径为：images/small/3/...
	private String smallRelativeFolderPath = "";
	//公司剪切图的文件夹相对路径为：images/cut/3/...
	private String cutRelativeFolderPath = "";
	
	//图片的根文件夹全路径为：C:\goods\images\
	private String imagePath = "";
	
	private Properties props;
	
	private String baseImageRelativePath;
	
	private String srcPath = "";
	private String compressPath = "";
	private String targetCutPath = "";
	
	public UploadFileUtil() {
		super();
	}	

	public UploadFileUtil(String contextPath,String[] scope) {
		super();
		this.contextPath = contextPath;
		this.scope = scope;
		props=new Properties();
		try {
			//加载配置信息
			props.load(this.getClass().getClassLoader().getResourceAsStream("sys.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//图片基本文件夹:images\
		this.baseImageRelativePath = props.getProperty("baseImageRelativePath")+"\\";
		
		//公司原图的根文件夹全路径为：C:\axecom\images\
		this.imagePath = contextPath+baseImageRelativePath;
		
		if(scope!=null){		
			//相对路径 images\
			this.normalRelativeFolderPath+=baseImageRelativePath;			
			for(int i = 0;i<scope.length;i++){
				this.normalRelativeFolderPath+=scope[i]+"\\";
				this.srcPath +=scope[i]+"\\";
				//最后一个
				if(i==scope.length-1){
					this.normalRelativeFolderPath += props.getProperty("lastNormalFolderName");
					this.normalRelativeFolderPath += "\\";
				}
			}
			//拼接srcPath：C:\axecom\normal\2\product\normal
			this.srcPath = this.contextPath+this.normalRelativeFolderPath+"\\";
		}
	}

	public UploadFileUtil(String contextPath, String baseRelativePath,
			String owner, String[] scope, String[] fileNames, String imagePath) {
		super();
		this.contextPath = contextPath;
		this.scope = scope;
		this.imagePath = imagePath;
	}
	
	/**
	 * 
	 * @Description:切换到压缩保存路径域
	 * @param scope 路径域数组
	 * @return void
	 * @throws
	 */	
	public void swichScopeToCompress(String[] scope){
		if(scope!=null){
			this.smallRelativeFolderPath = "";
			this.smallRelativeFolderPath+=this.baseImageRelativePath;	
			for(int i = 0;i<scope.length;i++){
				this.smallRelativeFolderPath+=scope[i]+"\\";
				//最后一个
				if(i==scope.length-1){
					this.smallRelativeFolderPath += props.getProperty("lastCompressFolderName");
					this.smallRelativeFolderPath += "\\";
				}
			}
			this.compressPath = this.contextPath+this.smallRelativeFolderPath+"\\";
		}
	}
	
	/**
	 * 
	 * @Description:切换到剪切保存路径域
	 * @param scope路径域数组
	 * @return void
	 * @throws
	 */
	public void swichScopeToCut(String[] scope){
		if(scope!=null){
			this.cutRelativeFolderPath = "";
			this.cutRelativeFolderPath+=baseImageRelativePath;	
			for(int i = 0;i<scope.length;i++){
				this.cutRelativeFolderPath+=scope[i]+"\\";
				//最后一个
				if(i==scope.length-1){
					this.cutRelativeFolderPath += props.getProperty("lastCutFolderName");
					this.cutRelativeFolderPath += "\\";
				}
			}
			this.targetCutPath = this.contextPath+this.cutRelativeFolderPath+"\\";
		}
	}
	
	
	/**
	 * 
	 * @Description:静态调用，多文件压缩
	 * @param srcPath
	 * @param compressPath
	 * @param picFiles
	 * @param newFileNames
	 * @param generateFileName
	 * @return String[] 返回的文件路径数组
	 * @throws
	 */
	public static String[] savePicWihtCompress(String srcPath,
			String compressPath, MultipartFile[] picFiles, String[] newFileNames,
			boolean generateFileName) {
		String[] filePaths = null;
		if (picFiles != null&&checkImg(picFiles)) {
			filePaths = new String[picFiles.length];
			for (int i = 0; i < picFiles.length; i++) {
				filePaths[i] = savePicWithCompress(srcPath, compressPath,
						picFiles[i], newFileNames[i], generateFileName);
			}
		}
		return filePaths;
	}
	
	/**
	 * 
	 * @Description:多图片压缩
	 * @param picFiles 图片数组
	 * @param newFileNames 新生成的图片名称数组
	 * @param generateFileName 是否自动生成文件名
	 * @return String[] 返回压缩后的图片的路径数组
	 * @throws
	 */
	public String[] savePicWihtCompress(MultipartFile[] picFiles, String[] newFileNames,
			String[] compressScope,boolean generateFileName) {
		String[] filePaths = null;
		if (picFiles != null&&checkImg(picFiles)) {
			filePaths = new String[picFiles.length];
			for (int i = 0; i < picFiles.length; i++) {
				filePaths[i] = savePicWithCompress(picFiles[i], newFileNames[i],compressScope, generateFileName);
			}
		}
		return filePaths;
	}

	/**
	 * 
	 * @Description:图片保存并压缩
	 * @param tempPath 存储的源文件路径
	 * @param savePath 压缩保存文件的路径
	 * @param picFile 图片文件
	 * @param newFileName 新文件名
	 * @param generateFileName 是否要重新生成文件名
	 * @return String 返回保存文件的全路径
	 * @throws
	 */
	public static String savePicWithCompress(String srcPath, String compressPath,
			MultipartFile picFile, String newFileName, boolean generateFileName) {
		if (picFile == null||srcPath==null||compressPath==null||!checkImg(picFile)) {
			return null;
		}
		// 保存的文件基本路径，除了文件的名称外的路径信息
		File srcPathFolder = new File(srcPath);
		File compressPathFolder = new File(compressPath);
		if (!compressPathFolder.exists()) {
			compressPathFolder.mkdirs();
		}
		if (!srcPathFolder.exists()) {
			srcPathFolder.mkdirs();
		}
		if (newFileName == null || "".equals(newFileName)||generateFileName) {
			// 旧文件名
			String oldFileName = picFile.getOriginalFilename();
			// 新文件名，时间戳形式
			newFileName = generateFileName(oldFileName, PIC_WITH_TIMESTAMP);
		}
		try {
			String srcFilePath=srcPath + newFileName;
			File file = new File(srcFilePath);
			// 将文件保存到硬盘
			picFile.transferTo(file);
			CompressPicUtil mypic = new CompressPicUtil();
			// 图片压缩
			mypic.compressPic(srcPath, compressPath, newFileName, newFileName,
					120, 120, true);
			// 删除临时文件
			//file.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return srcPath+newFileName;
	}
	
	/**
	 * 
	 * @Description:压缩图片
	 * @param picFile 要压缩的图片
	 * @param newFileName 生成的压缩图片名
	 * @param compressScope 保存压缩图片的路径域
	 * @param generateFileName 是否自动生成文件名
	 * @return String 返回压缩图片的绝对路径
	 * @throws
	 */
	public String savePicWithCompress(MultipartFile picFile, String newFileName,
			String[] compressScope, boolean generateFileName) {
		//验证有效性
		if (picFile == null||srcPath==null||!checkImg(picFile)) {
			return null;
		}
			
		swichScopeToCompress(compressScope);
		
		// 保存的文件基本路径，除了文件的名称外的路径信息
		File srcPathFolder = new File(srcPath);
		File compressPathFolder = new File(compressPath);
		if (!compressPathFolder.exists()) {
			compressPathFolder.mkdirs();
		}
		if (!srcPathFolder.exists()) {
			srcPathFolder.mkdirs();
		}
		if (newFileName == null || "".equals(newFileName)||generateFileName) {
			// 旧文件名
			String oldFileName = picFile.getOriginalFilename();
			// 新文件名，时间戳形式
			newFileName = generateFileName(oldFileName, PIC_WITH_TIMESTAMP);
		}
		try {
			String srcFilePath=srcPath + newFileName;
			File file = new File(srcFilePath);
			// 将文件保存到硬盘
			picFile.transferTo(file);
			CompressPicUtil mypic = new CompressPicUtil();
			// 图片压缩
			mypic.compressPic(srcPath, compressPath, newFileName, newFileName,
					120, 120, true);
			// 删除临时文件
			//file.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return srcPath+newFileName;
	}
	
	/**
	 * 
	 * @Description:图片剪切
	 * @param imgCut 剪切模型
	 * @param fileName 要剪切图片的名称
	 * @return String 剪切图片的绝对路径
	 * @throws
	 */
	public String cutImg(ImageCutModel imgCut,String fileName,String[] cutScope) {
		String cutPath = null;
		int x = 0, y = 0, w = 0, h = 0, cw = 0, ch = 0;
		if (imgCut != null) {
			x = imgCut.getX();
			y = imgCut.getY();
			w = imgCut.getW();
			h = imgCut.getH();
			cw = imgCut.getCw();
			ch = imgCut.getCh();
			//切换到剪切
			swichScopeToCut(cutScope);
			File cutPathFolder = new File(this.targetCutPath);
			if (!cutPathFolder.exists()) {
				cutPathFolder.mkdirs();
			}
		}
		try {
			// 读取原始文件
			BufferedImage big = ImageIO.read(new File(srcPath+fileName));
			Integer width = big.getWidth();
			Integer height = big.getHeight();
			// 比较宽高
			if (width >= w && height >= h && w * h > 0 && cw * ch > 0) {
				Image image = big.getScaledInstance(width, height,
						Image.SCALE_DEFAULT);
				// 定义一个图片剪切的矩形区域
				x = x * width / cw;
				y = y * height / ch;
				w = w * width / cw;
				h = h * height / ch;
				ImageFilter filter = new CropImageFilter(x, y, w, h);
				// 剪切的图片
				Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), filter));
				BufferedImage cutImg = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = cutImg.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				// 生成剪切图
				ImageIO.write(cutImg, "JPEG", new File(targetCutPath+fileName));
				cutPath = targetCutPath+fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cutPath;
	}

	/**
	 * 
	 * @Description:图片剪切
	 * @param x 剪切点x,相对位置
	 * @param y 剪切点y,相对文字
	 * @param w 剪切的宽度
	 * @param h 剪切的高度
	 * @param cutImgWidth 客户端所剪切图片的宽度
	 * @param cutImgHeight 客户端所剪切图片的高度
	 * @param cutToFileName 客户端所剪切图片的高度
	 * @param cutScope 保存剪切图的路径域
	 * @return String
	 * @throws
	 */
	public String cutImg(int x, int y, int w, int h, int cutImgWidth,
			int cutImgHeight,String cutToFileName,String[] cutScope) {
		String cutPath = "";
		
		//切换到剪切
		swichScopeToCut(cutScope);
		
		try {
			// 读取原始文件
			BufferedImage big = ImageIO.read(new File(srcPath));
			Integer width = big.getWidth();
			Integer height = big.getHeight();
			// 比较宽高
			if (width >= w && height >= h) {
				Image image = big.getScaledInstance(width, height,
						Image.SCALE_DEFAULT);
				// 定义一个图片剪切的矩形区域
				x = x * width / cutImgWidth;
				y = y * height / cutImgHeight;
				w = w * width / cutImgWidth;
				h = h * height / cutImgHeight;
				ImageFilter filter = new CropImageFilter(x, y, w, h);
				// 剪切的图片
				Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), filter));
				BufferedImage cutImg = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = cutImg.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				// 生成剪切图
				ImageIO.write(cutImg, "JPEG", new File(targetCutPath+cutToFileName));
				cutPath = targetCutPath+cutToFileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cutPath;
	}
	
	
	public static boolean checkImg(MultipartFile picFile){
		boolean flag = false;
		if(picFile!=null){
			// 通过原始名称取得后缀名
			String oldFileName = picFile.getOriginalFilename();
			String extension = oldFileName.substring(oldFileName
					.lastIndexOf("."));
			// 判断上传的是否是图片类型 ，如果是就继续判断，如果不是就终止
			if (extension.equalsIgnoreCase(".jpg")
					|| extension.equalsIgnoreCase(".jpeg")
					|| extension.equalsIgnoreCase(".png")
					|| extension.equalsIgnoreCase(".bmp")) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	public static boolean checkImg(MultipartFile[] picFile){
		boolean flag = true;
		if(picFile!=null){
			for (int i = 0; i < picFile.length; i++) {		
				if (checkImg(picFile[i])) {
					continue;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * Save Files use new names
	 * 
	 * @param savePath
	 * @param files
	 * @param filesName
	 * @param isGenerateFn
	 *            true
	 * @return fileSaveUrl[]
	 * @throws Exception
	 */
	public static String[] uploadFiles(String savePath, File[] files,
			String[] filesName, boolean isGenerateFn) throws Exception {
		if (files == null)
			return null;
		String[] fileUrls = new String[files.length];
		for (int i = 0; i < files.length; i++) { // 循环每个上传的文件
			fileUrls[i] = uploadFile(savePath, files[i], filesName[i],
					isGenerateFn);
		}
		return fileUrls;
	}

	/**
	 * Base save files
	 * 
	 * @param savePath
	 * @param files
	 * @param filesName
	 * @return fileSaveUrl[]
	 * @throws Exception
	 */
	public static String[] uploadFiles(String savePath, File[] files,
			String[] filesName) throws Exception {
		if (files == null)
			return null;
		String[] fileUrls = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileUrls[i] = uploadFile(savePath, files[i], filesName[i]);
		}
		return fileUrls;
	}

	/**
	 * upload by check file size
	 * 
	 * @param savePath
	 * @param file
	 * @param fileName
	 * @param maxSize
	 * @return fileSaveUrl or false
	 * @throws Exception
	 */
	public static String uploadFile(String savePath, File file,
			String fileName, long maxSize) throws Exception {
		long fileSize = file.length();
		String fileUrl = "false";
		if (isValidSize(fileSize, maxSize)) {
			fileUrl = uploadFile(savePath, file, fileName);
		}
		return fileUrl;
	}

	/**
	 * upload by check file type
	 * 
	 * @param savePath
	 * @param file
	 * @param fileName
	 * @param allowFileExt
	 *            .jpg,.gif,.doc,.rar
	 * @return fileSaveUrl or false
	 * @throws Exception
	 */
	public static String uploadFile(String savePath, File file,
			String fileName, String allowFileExt) throws Exception {
		String fnExt = getFileExtension(fileName);
		String fileUrl = "false"; // 是否允许文件上传
		if (isValidFile(fnExt, allowFileExt)) {
			fileUrl = uploadFile(savePath, file, fileName);
		}
		return fileUrl;
	}

	/**
	 * Upload file use a new name
	 * 
	 * @param savePath
	 * @param file
	 * @param fileName
	 * @param isGenerateFn
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(String savePath, File file,
			String fileName, boolean isGenerateFn) throws Exception {
		String newFn = fileName;
		if (isGenerateFn) {
			newFn = generateFileName(fileName, PIC_WITH_TIMESTAMP);
		}
		String fileUrl = uploadFile(savePath, file, newFn);
		return fileUrl;
	}

	/**
	 * Base Save a file
	 * 
	 * @param savePath
	 * @param file
	 * @param fileName
	 * @return fileSaveUrl
	 * @throws Exception
	 */
	public static String uploadFile(String savePath, File file, String fileName)
			throws Exception {

		InputStream is = new FileInputStream(file);
		File toFile = new File(savePath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
		// String fileUrl = savePath + "/" + fileName;
		return fileName;
	}

	/**
	 * check file content type or file extension name
	 * 
	 * @param contentType
	 * @param allowType
	 * @return
	 */
	private static boolean isValidFile(String contentType, String allowType) {
		boolean flag = false;
		for (String type : allowType.split(",")) {
			if (type.equalsIgnoreCase(contentType)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * check size
	 * 
	 * @param fileSize
	 * @param allowSize
	 * @return
	 */
	private static boolean isValidSize(long fileSize, long allowSize) {
		boolean flag = false;
		if (fileSize <= allowSize)
			flag = true;
		return flag;
	}

	/**
	 * new name with date&time plus random number
	 * 
	 * @param fileName
	 * @return
	 */
	private static String generateFileName(String fileName, int nameStyle) {
		String newFileName = "";
		switch (nameStyle) {
			case PIC_WITH_TIMESTAMP:
				newFileName = new Date().getTime() + "";
				break;
			case PIC_WITH_FORMATDATE:
				DateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
				newFileName = format.format(new Date());
				break;
			case PIC_WITH_UUID:
				newFileName = UUID.randomUUID().toString();
				break;
			default:
				break;
		}
		String extension = getFileExtension(fileName);
		return newFileName + extension;
	}

	/**
	 * get file extension name with dot
	 * 
	 * @param fileName
	 * @return .doc
	 */
	private static String getFileExtension(String fileName) {
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return extension;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String[] getScope() {
		return scope;
	}

	public void setScope(String[] scope) {
		this.scope = scope;
	}

	public String getNormalRelativeFolderPath() {
		return normalRelativeFolderPath;
	}

	public void setNormalRelativeFolderPath(String normalRelativeFolderPath) {
		this.normalRelativeFolderPath = normalRelativeFolderPath;
	}

	public String getSmallRelativeFolderPath() {
		return smallRelativeFolderPath;
	}

	public void setSmallRelativeFolderPath(String smallRelativeFolderPath) {
		this.smallRelativeFolderPath = smallRelativeFolderPath;
	}

	public String getCutRelativeFolderPath() {
		return cutRelativeFolderPath;
	}

	public void setCutRelativeFolderPath(String cutRelativeFolderPath) {
		this.cutRelativeFolderPath = cutRelativeFolderPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
