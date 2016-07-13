package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.MutiType;
import com.xiaov.model.Product;
import com.xiaov.model.Types;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.MutiTypeService;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.service.interfaces.TypesService;
import com.xiaov.service.interfaces.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharkseven on 2016/7/9.
 */
@Controller
public class MutiTypeController {

    @Autowired
    private MutiTypeService mutiTypeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private TypesService typesService;
    public final static ObjectMapper mapper = new ObjectMapper();
    @ResponseBody
    @RequestMapping("/admin/mutitype/save")
    public MessageBean save(MutiType mutiType){

        try {
            mutiTypeService.save(mutiType);
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageBean(APPConstant.ERROR,"服务器异常，请重试");
        }
        return  new MessageBean(APPConstant.SUCCESS,"添加成功");
    }
    @ResponseBody
    @RequestMapping("/admin/mutitype/more/save")
    public MessageBean saveMore(String mutiType,String type){

        try {
            JavaType javaType = getCollectionType(ArrayList.class, MutiType.class);
            List<MutiType> types =  parse(mutiType,type);

         String label="";
            for (int i=0;i<types.size() ;i++) {
                mutiTypeService.save(types.get(i));
                label=label+","+types.get(i).getType().getId();
            }
            if(type.equals("user")){
                UserInfo one = userService.getOne(UserInfo.class, types.get(0).getUserId());
                if(one.getUsRemark()!=null){
                    label=one.getUsRemark()+label;
                }
                one.setUsRemark(label);
                userService.update(one);
            }
                if(type.equals("product")){
                    Product p = productService.getOne(Product.class, types.get(0).getProductId());
                    if(p.getPdtLabel()!=null){
                        label=p.getPdtLabel()+label;
                    }
                    p.setPdtLabel(label);
                    productService.update(p);
                }
            return  new MessageBean(APPConstant.SUCCESS,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageBean(APPConstant.ERROR,"服务器异常，请重试");
        }
    }
    @ResponseBody
    @RequestMapping("/admin/mutitype/list")
    public List<MutiType> page(MutiType mutiType){

        try {
            List<MutiType> types = mutiTypeService.loadAll(mutiType);
            return types;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @ResponseBody
    @RequestMapping("/admin/product/list/label")
    public Page<Product> pageByMutiType(Page<Product> page){

        try{
            List<Product> products = productService.getProductByMutiType(page, page.getMutiType());
            page.setResult(products);
            return page;
        }catch (Exception e){
            e.printStackTrace();
        }
        page.setCode(APPConstant.ERROR);
        page.setMessage("服务器异常");
        page.setMutiType(null);
        return page;
    }
    @ResponseBody
    @RequestMapping("/admin/designer/list/label")
    public Page<UserInfo> pageByMutiTypeDesinger(Page<UserInfo> page){

        try{
            List<UserInfo> users = userService.getDesignerByMutiType(page, page.getMutiType());
            page.setResult(users);
            return page;
        }catch (Exception e){
            e.printStackTrace();
        }
        page.setCode(APPConstant.ERROR);
        page.setMessage("服务器异常");
        page.setMutiType(null);
        return page;
    }
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    private   List<MutiType> parse(String str,String type){

        if(!type.equals("product")&&!type.equals("user")){
            throw  new RuntimeException("参数异常");
        }
        List<MutiType> mutiTypes=new ArrayList<MutiType>();
        MutiType m=null;
        if(str!=null){
            String[] split = str.split("[_]");

            Types types;
            String id=split[0];
            if(split.length>2){


                for (int i=1;i<split.length;i++){
                    m=new MutiType();
                    types=new Types();
                    if(type.equals("product")){
                        m.setProductId(id);
                    }else {
                        m.setUserId(id);
                    }
                    types.setId(split[i]);
                    m.setType(types);
                    mutiTypes.add(m);
                }
            }
        }else{
            throw  new RuntimeException("参数异常");
        }
        return mutiTypes;
    }
}
