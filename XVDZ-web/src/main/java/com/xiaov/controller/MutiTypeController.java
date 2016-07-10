package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.MutiType;
import com.xiaov.model.Product;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.MutiTypeService;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.service.interfaces.TypesService;
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
    private ProductService productService;

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
    public MessageBean saveMore(String mutiType){

        try {
            JavaType javaType = getCollectionType(ArrayList.class, MutiType.class);
            List<MutiType> types =  (List<MutiType>)mapper.readValue(mutiType, javaType);
            for (MutiType type: types) {
                mutiTypeService.save(type);
            }
            return  new MessageBean(APPConstant.SUCCESS,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageBean(APPConstant.ERROR,"服务器异常，请重试");
        }
    }
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @ResponseBody
    @RequestMapping("/admin/product/list/label")
    public Page<Product> pageByMutiType(Page<Product> page){

        try{
            JavaType javaType = getCollectionType(ArrayList.class, MutiType.class);
            List<MutiType> types =  (List<MutiType>)mapper.readValue(page.getMutiType(), javaType);
            List<Product> products = productService.getProductByMutiType(page, types);
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
}
