package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.*;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.ProductDetailServiceImpl;
import com.xiaov.service.impl.ProductServiceImpl;
import com.xiaov.service.interfaces.MaterialService;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.service.interfaces.StyleService;
import com.xiaov.utils.*;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductServiceImpl productServiceimpl;

    @Autowired
    private ProductDetailServiceImpl detailServiceImpl;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private StyleService styleService;


    private String path;
    private static int bufSize = 512; // size of bytes
    private byte[] buf;
    private int readedBytes;

    private static final String [] lazyField=   {"productType", "img","show","backImage"};

    /**
     * 商品报保存
     * @param product
     * @param imgCut
     * @return
     */
    @RequestMapping("/admin/product/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Product product, ImageCutModel imgCut) {

        try {
            product.setAddTime(new Date());
            productService.save(product);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }

    /**
     * 提交定制
     * @param product
     * @return
     */
    @RequestMapping("/auth/customization/commit")
    @ResponseBody
    public MessageBean commit(Product product) {


        String name="定制商品";
        try {
            if(product.getIsGroup()!=null&&product.getIsGroup().equals(1)){
                if(product.getGroupPrice()==null||product.getMinnum()==null){
                    return new MessageBean(APPConstant.SUCCESS, "团体定制请输入最低优惠数量和价格");
                }
                name="团体"+name;
            }
            if(product.getPdtName()==null||"".equals(product.getPdtName())){
                product.setPdtName(name);
            }
            Types types=new Types();
            types.setId("designer.product");
            Double count = countPrice(product);

            product.setPdtPrc(count);
            product.setProductType(types);
            product.setAddTime(new Date());
            productService.save(product);
            return new MessageBean(APPConstant.SUCCESS, product.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }

    /**
     * 提交定制
     * @param product
     * @return
     */
    @RequestMapping("/auth/customization/update")
    @ResponseBody
    public MessageBean commitUpdate(Product product) {


        String name="定制商品";
        try {
            if(product.getIsGroup()!=null&&product.getIsGroup().equals(1)){
                if(product.getGroupPrice()==null||product.getMinnum()==null){
                    return new MessageBean(APPConstant.SUCCESS, "团体定制请输入最低优惠数量和价格");
                }
                name="团体"+name;
            }
            if(product.getPdtName()==null||"".equals(product.getPdtName())){
                product.setPdtName(name);
            }
            Product one = productService.getOne(Product.class, product.getId());
            if(one==null){
                return new MessageBean(APPConstant.ERROR, "该商品不存在或者已被删除"+product.getId());
            }
            Double count = countPrice(product);
            if(count!=0){
                product.setPdtPrc(count);
            }
            product.setUpdateTime(new Date());
            productService.update(product);
            return new MessageBean(APPConstant.SUCCESS, product.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "修改失败");
        }
    }


    /**
     * 修改跟新商品
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/updateAjax")
    @ResponseBody
    public MessageBean updateAjax(Product product) {

        try {
            productService.update(product);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }

    /**
     * 商品分页查询
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/page")
    @ResponseBody
    public Page<Product> page(Product product) {

        try {
            Page<Product> page = productService.page(product);

            String[] fileName = {"productType", "img","show","backImage"};
            page = LazyObjecUtil.LazyPageSetNull(page, fileName);

            return page;
        } catch (Exception e) {
            e.printStackTrace();
            Page<Product> page = new Page<Product>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    /**
     * 删除商品
     * @param product
     * @return
     */
    @RequestMapping("/auth/product/delete")
    @ResponseBody
    public MessageBean deleteAjax(Product product) {

        try {
            product = productService.getOne(product.getClass(), product.getId());
            productService.delete(product);
            return new MessageBean(APPConstant.SUCCESS, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    /**
     * 获取某个商品
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/getOneAjax")
    @ResponseBody
    public Product getOne(Product product) {

        try {

            return productService.getOne(product.getClass(), product.getId());
        } catch (Exception e) {
            Product page = new Product();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    /**
     * 上传图案
     * @param imgCut
     * @param request
     * @return
     */
    @RequestMapping("/admin/product/picUploadAjax")
    @ResponseBody
    public MessageBean saveProductImage(ImageCutModel imgCut, HttpServletRequest request) {

        System.out.println("11");
        try {
            if (imgCut != null) {
                // 新文件名
                String newFileName = new Date().getTime() + ".jpg";
                String contextPath = request.getRealPath("/");
                // 获取公司ID
                // 设置正常保存路径域
                String[] normalScope = {"normal", imgCut.getType(), new Date().getYear() + "",
                        new Date().getMonth() + "", "product"};
                // 创建图片上传对象，这是正常路径
                UploadFileUtil fileUtil = new UploadFileUtil(contextPath, normalScope);

                // 设置压缩保存路径域
                String[] compressScope = {"normal", imgCut.getType(), new Date().getYear() + "",
                        new Date().getMonth() + "", "product"};
                // 压缩
                String compressTargetPath = fileUtil.savePicWithCompress(imgCut.getImageFile(), newFileName,
                        compressScope, false);
                String dbCompressUrl = fileUtil.getSmallRelativeFolderPath() + newFileName;
                // 剪切保存域
                String[] cutScope = {"normal", imgCut.getType(), new Date().getYear() + "", new Date().getMonth() + "",
                        "product"};
                // 剪切
                String cutTargetPath = fileUtil.cutImg(imgCut, newFileName, cutScope);
                String dbCutUrl = fileUtil.getCutRelativeFolderPath() + newFileName;

                if (compressTargetPath != null && dbCutUrl != null) {

                    return new MessageBean(APPConstant.SUCCESS, compressTargetPath);
                } else {
                    return new MessageBean(APPConstant.SUCCESS, "上传失败");
                }
            } else {
                return new MessageBean(APPConstant.SUCCESS, "上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "上传失败");
        }
    }

    /**
     * 最新商品
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/newProduct")
    @ResponseBody
    public Page<Product> newProduct(Product product) {
        /*
		 * try { product.setSidx("addTime"); product.setSord("DESC");
		 * Page<Product> page = productService.page(product);
		 * 
		 * String [] fileName={"material","productType"}; page =
		 * LazyObjecUtil.LazyPageSetNull(page, fileName);
		 * 
		 * return page; } catch (Exception e) { e.printStackTrace();
		 * Page<Product> page=new Page<Product>();
		 * page.setCode(APPConstant.ERROR); page.setMessage("服务器忙"); return
		 * page; }
		 */
        Page<Product> page = new Page<Product>();
        try {
            product.setSidx("addTime");
            product.setSord("DESC");
            page = productService.page(product);
            LazyObjecUtil.LazyPageSetNull(page, new String[]{"productType", "img","show","backImage"});
            return page;
        } catch (Exception e) {

            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }

    }

    /**
     * 最热商品
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/hotProduct")
    @ResponseBody
    public Page<Product> hotProduct(Product product) {

        try {

            List<Product> products = productService.hot(product);
            product.setResult(products);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            Page<Product> page = new Page<Product>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }

    }

    /**
     * 根据商品名称查询商品
     * 已废弃
     * @param searchText
     * @return
     */
    @RequestMapping(value = "/admin/product/searchProduct")
    @ResponseBody
    public List<Product> searchProduct(String searchText) {

        Criterion eq1 = Restrictions.or(Restrictions.like("pdtName", "%" + searchText + "%"),
                Restrictions.like("remark", "%" + searchText + "%"));
        Criterion[] criterions = {eq1};
        try {
            List<Product> productList = productServiceimpl.searchProduct(criterions);

            return productList;

        } catch (Exception e) {
            System.out.println("系统错误!");
            return null;
        }

    }

    /**
     * 基本款上传
     * 已废弃
     * @param zip
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/product/uploadByZip")
    public MessageBean uploadProductByZip(MultipartFile zip, HttpServletRequest request) {
        ZipFile zipFile = null;
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        File file = null;

        try {
            path = request.getRealPath("/");

            System.out.println(path);
            inputstreamtofile(zip.getInputStream());
            zipFile = new ZipFile(path + "temp.zip", "gbk");
            for (Enumeration<? extends ZipEntry> e = zipFile.getEntries(); e.hasMoreElements(); ) {
                ZipEntry entry = e.nextElement();
                System.out.println("文件名:" + entry.getName() + ", 内容如下:");
                if (entry.getName().toLowerCase().endsWith(".png")) {

                    // 文件路径分隔
                    String[] split = entry.getName().split("[/]");
                    // 分隔款式和价格
                    String[] split1 = split[2].split("[_]");
                    // 分隔颜色和正反面标记
                    String[] split2 = split[3].split("[_]");

                    // 添加商品
                    Product product = new Product();
                    Types types = new Types();
                    // 定制类型商品
                    types.setId("product.basetype");
                    product.setProductType(types);
                    // 商品名
                    product.setPdtName(split[1]);
                    // 商品价格

                    List<Product> loadAll2 = productServiceimpl.loadAll(product);
                    product.setAddTime(new Date());

                    if (loadAll2.size() <= 0) {
                        productService.save(product);
                    } else if (loadAll2.size() == 1) {
                        product = loadAll2.get(0);
                    }

                    ProductDetail detail = new ProductDetail();
                    detail.setProductId(product.getId());
                    // 款式
                    detail.setType(split1[0]);
                    // 价格
                    detail.setPrice((Double.parseDouble(split2[2].replace(".png", ""))));
                    // 颜色
                    detail.setColorName(split2[0]);

                    //临时文件路径
                    String tempPath = "images/baseproduct/temp/" + Pinyin4jUtil.spellNoneTone(split[2]).replace("ü", "");
                    //压缩文件路径
                    String basePath = "images/baseproduct/compress/" + Pinyin4jUtil.spellNoneTone(split[2]).replace("ü", "");
                    //创建文件夹和文件
                    file = new File(path + tempPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    file = new File(path + basePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String fileName = UUID.nameUUIDFromBytes(entry.getName().getBytes()) + ".png";
                    file = new File(path + basePath + "/" + fileName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    file = new File(path + tempPath + "/" + fileName);
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
                    mypic.resizePNG(path + tempPath + "/" + fileName, path + basePath + "/" + fileName, 200, 200, true);
                    List<ProductDetail> loadAll = detailServiceImpl.loadAll(detail);

                    if (loadAll.size() == 1) {
                        detail = loadAll.get(0);
                    }
                    Material material;
                    material = new Material();
                    material.setDbTypes(types);
                    material.setMeterialName("基本款图片");
                    material.setUrl(basePath + "/" + fileName);

                    if (materialService.loadAll(material).size() == 0) {
                        material.setAddTime(new Date());

                        material.setOriginalUrl(tempPath + fileName);

                        materialService.save(material);
                        if (split2[1].equals("1")) {
                            detail.setPicB(material);
                        } else if (split2[1].equals("2")) {
                            detail.setPicF(material);
                        }
                        if (loadAll.size() == 1) {
                            detailServiceImpl.update(detail);
                        } else {
                            detailServiceImpl.save(detail);
                        }
                    }
                }
            }
            return new MessageBean(APPConstant.SUCCESS, "请上传PNG格式文件");
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

    /**
     * 设计师作品上传
     *已废弃
     * @param zip
     * @param request
     * @return
     */
    @RequestMapping("/admin/product/desingerUploadByZip")
    @ResponseBody
    public MessageBean designerUpload(MultipartFile zip, HttpServletRequest request) {
        return null;

    }

    /**
     * 获取商品详情，已废弃
     * @param product
     * @return
     */
    @RequestMapping("/admin/product/getPdtDetail")
    @ResponseBody
    public Product getProductDetail(Product product) {

        try {
            return productServiceimpl.fillDetail(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("/admin/product/getUserWorks")
    @ResponseBody
    public List<Product> getUserProduct() {
        String values = "1";
        List<Product> result = productServiceimpl.getByProperty("usId", values);
        String[] fileds = new String[]{"productType", "img","show"};
        try {
            result = LazyObjecUtil.LazySetNull(result, fileds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设计师作品上传
     *
     * @param request
     * @param product
     * @param showImage
     * @return
     */
    @RequestMapping("/auth/designer/product/upload")
    @ResponseBody
    public MessageBean designer(HttpServletRequest request, Product product, MultipartFile image, MultipartFile showImage,MultipartFile backImge) {

        Types types = new Types();
        types.setId("designer.product");
        Material mImg = saveFile(image, request, types, "设计师作品");
        Material mshowImage = saveFile(showImage, request, types, "设计师作品");
        Material mbackImage = saveFile(backImge, request, types, "设计师作品");
        if(mImg!=null&&mshowImage!=null&&mbackImage!=null){

            product.setProductType(types);
            //设置溢价
            mImg.setPrice(product.getPdtPrc());
            mshowImage.setPrice(product.getPdtPrc());
            product.setImg(mImg);
            product.setShow(mshowImage);
            product.setBackImage(mbackImage);
            product.setAddTime(new Date());
            productService.save(product);
            return new MessageBean(APPConstant.SUCCESS, product.getId());
        }else{
            return new MessageBean(APPConstant.SUCCESS, "上传失败，服务器异常");
        }
    }
    /**
     * 设计师作品上传
     *
     * @param request
     * @param product
     * @param showImage
     * @return
     */
    @RequestMapping("/auth/designer/product/update")
    @ResponseBody
    public MessageBean designerUpdate(HttpServletRequest request, Product product, MultipartFile image, MultipartFile showImage,MultipartFile backImge) {

        Types types = new Types();

        Product one = productService.getOne(Product.class, product.getId());
        Material mImg = saveFile(image, request, types, "设计师作品");
        Material mshowImage = saveFile(showImage, request, types, "设计师作品");
        Material mbackImage = saveFile(backImge, request, types, "设计师作品");

        if(mImg!=null){
            one.setImg(mImg);
            mImg.setPrice(product.getPdtPrc());
        }
        if(mbackImage!=null){
            one.setBackImage(mbackImage);
        }
        if(mshowImage!=null){
            one.setShow(mshowImage);
        }
        if(product.getPdtPrc()!=null){
            one.setPdtPrc(product.getPdtPrc());
        }
        product.setUpdateTime(new Date());
        productService.update(product);
        return new MessageBean(APPConstant.SUCCESS, product.getId());
    }

    /**
     * 图片上传和保存
     * @param img
     * @param request
     * @param types
     * @param materialName
     * @return
     */
    private Material saveFile(MultipartFile img, HttpServletRequest request, Types types, String materialName) {
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        File file = null;

        try {

            if(img==null){
                return null;
            }
            path = request.getRealPath("/");
            String[] split = img.getOriginalFilename().split("[.]");
            String suffix = "." + exChange(split[split.length - 1]);
            //临时文件路径
            String tempPath = "images/designer/temp/";
            //压缩文件路径
            String basePath = "images/designer/compress/";
            //创建文件夹和文件
            file = new File(path + tempPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path + basePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = UUID.randomUUID() + suffix;
            file = new File(path + basePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            file = new File(path + tempPath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //获取压缩包中的图片
            inputStream = img.getInputStream();
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
            mypic.resizePNG(path + tempPath + "/" + fileName, path + basePath + "/" + fileName, 200, 200, true);
            Material material;
            material = new Material();
            material.setDbTypes(types);
            material.setOriginalUrl(tempPath + fileName);
            material.setMeterialName(materialName);
            material.setUrl(basePath  + fileName);
            material.setAddTime(new Date());
            materialService.save(material);

            return material;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设计师作品分页查询
     * @param product 无参数（分页参数） 可传其他参数，us_id....
     * @return
     */
    @RequestMapping("/admin/designer/product/page")
    @ResponseBody
    public Page<Product> designerPage(Product product){
        try {
            Page<Product> page = productService.pageNotLazy(product, lazyField, new Product());
            return page;
        }catch (Exception e){
            product.setCode(APPConstant.ERROR);
            product.setMessage("服务器异常"+e.getMessage());
            return product;
        }
    }
    /**
     * 获取设计模块
     * @param product
     * @return
     */
    @RequestMapping("/admin/designer/product/module")
    @ResponseBody
    public Page<Product> moudule(Product product){
        try {
            product.setIsModule(1);
            Page<Product> page = productService.pageNotLazy(product,lazyField,new Product());
            return page;
        }catch (Exception e){
            product.setCode(APPConstant.ERROR);
            product.setMessage("服务器异常"+e.getMessage());
            return product;
        }
    }
    /**
     * 获取所有定制作品
     * @param product
     * @return
     */
    @RequestMapping("/admin/designer/product/all")
    @ResponseBody
    public Page<Product> all(Product product){

        try {
            product.setIsModule(0);
            Page<Product> page = productService.pageNotLazy(product,lazyField,new Product());
            return page;
        }catch (Exception e){
            product.setCode(APPConstant.ERROR);
            product.setMessage("服务器异常"+e.getMessage());
            return product;
        }
    }
    /**
     * 获取设计师的某个作品
     * @param product id
     * @return
     */
    @RequestMapping("/admin/designer/product/getOne")
    @ResponseBody
    public Product getOneDesigner(Product product){
        try {

           Product product1=productService.geOneDetail(product);
            if(product1!=null){
               return product1;
            }else{
                product.setCode(APPConstant.ERROR);
                product.setMessage("参数不完整");
            }
            return product;
        }catch (Exception e){
            product.setCode(APPConstant.ERROR);
            product.setMessage("服务器异常"+e.getMessage());
            return product;
        }
    }
    /**
     * 根据类型查询商品
     * @param product productType.id
     * @return
     */
    @RequestMapping("/admin/product/page/type")
    @ResponseBody
    public Page<Product> pageByType(Product product){
        try {
            Page<Product> page = productService.pageNotLazy(product, lazyField, new Product());
            return page;
        }catch (Exception e){
            product.setCode(APPConstant.ERROR);
            product.setMessage("服务器异常"+e.getMessage());
            return product;
        }
    }
    /**
     * 商品搜索
     * @param search search
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/product/search")
    public Page<SearchModel> search(SearchModel search){

        try {

            List<Product> products = productService.search(search);
            search.setResult(products);
            return search;

        }catch (Exception e){
            search.setCode(APPConstant.ERROR);
            search.setMessage("服务器异常"+e.getMessage());
            return  search;
        }
    }


    /**
     * 字母小写转大写
     * @param str
     * @return
     */
    public static String exChange(String str){
        StringBuffer sb = new StringBuffer();
        if(str!=null){
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(Character.isUpperCase(c)){
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }

    /**
     * 文件流转文件
     * @param ins
     */
    private void inputstreamtofile(InputStream ins) {
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

    private Double countPrice(Product product){

        Double sum=0d;
        Style one=null;
        if(product.getStyle()!=null){
            one= styleService.getOne(Style.class, product.getStyle());
            sum+=one.getPrice();
        }


        String images = product.getDesigner_product_id();

        if(images!=null&&"".equals(images)){
            List<String> strings = splitStr(images);
            List<Material> byids = materialService.getByids(Material.class, strings);


            for (Material material:byids
                    ) {
                sum+=material.getPrice();
            }
        }


        return  sum;
    }


    private List<String> splitStr(String str) {

        List<String> strs = null;
        if (str != null && !"".equals(str)) {

            String[] split = str.split("[_]");

            if (split.length > 1) {
                strs = new ArrayList<String>();

                for (String str1 : split) {
                    strs.add(str1);
                }
            }
        }
        return strs;
    }
}
