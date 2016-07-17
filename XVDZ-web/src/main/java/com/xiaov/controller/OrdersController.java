package com.xiaov.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaov.constant.APPConstant;
import com.xiaov.model.*;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.DiscountCoupanService;
import com.xiaov.service.interfaces.MaterialService;
import com.xiaov.service.interfaces.OrdersService;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.utils.LazyObjecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zouziyang on 4/21/16.
 */
@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private DiscountCoupanService discountCoupanService;


    @RequestMapping("/auth/orders/save")
    @ResponseBody
    public MessageBean saveAjax(Orders orders) {

        try {

            //检查订单
            checkOrders(orders);
            orders.setOrState(Orders.UNPAY);
            orders.setAddTime(new Date());
            ordersService.save(orders);
            for (OrderDetail detail:orders.getOrderDetails()
                    ) {
                detail.setOrDtNo(orders.getId());
            }
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/auth/orders/update")
    @ResponseBody
    public MessageBean updateAjax(Orders orders) {

        try {
            ordersService.update(orders);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }

    @RequestMapping("/auth/orders/delete")
    @ResponseBody
    public MessageBean deleteAjax(Orders orders) {

        try {
            orders = ordersService.getOne(orders.getClass(), orders.getId());
            ordersService.delete(orders);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    @RequestMapping("/auth/orders/page")
    @ResponseBody
    public Page<Orders> page(Orders orders) {
        Page<Orders> page = new Page<Orders>();
        try {

            page = ordersService.page(orders);
            page = LazyObjecUtil.LazyPageSetNull(page, new String[]{"user", "discountCoupan", "dbTypes"});
            return page;
        } catch (Exception e) {
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/auth/orders/getOne")
    @ResponseBody
    public Orders getOne(Orders orders) {
        try {
            Orders one = ordersService.getOne(orders.getClass(), orders.getId());
            one = LazyObjecUtil.LazyOneSetNull(one, new String[]{"user", "discountCoupan", "dbTypes"});
            return one;

        } catch (Exception e) {
            Orders page = new Orders();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping("/auth/orders/page/detail")
    @ResponseBody
    public Page<Orders> pageDetail(Orders orders) {
        Page<Orders> page = new Page<Orders>();
        try {
            page.setResult(ordersService.getOrderDetai(orders));
            LazyObjecUtil.LazyPageSetNull(page, new String[]{"user", "discountCoupan", "dbTypes"});
            return page;
        } catch (Exception e) {
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }


    /**
     * 检查订单金额是否正确
     *
     * @param orders
     * @return
     */
    private boolean checkOrders(Orders orders) {

        double cost = 0;

        Product one=null;

        List<OrderDetail> orderDetails = getOrderDetails(orders.getMutiType());
        String materials = "";
        for (OrderDetail detail : orderDetails
                ) {
            one= productService.getOne(Product.class, detail.getDesigner_product_id());
            detail.setDesigner_product(one);
            materials = detail.getImage_back() + "_" + detail.getImage_front();
            List<String> strings = splitStr(materials);
            List<Material> byids1 = materialService.getByids(Material.class,strings);
            detail.setMaterials(byids1);
        }

        orders.setOrderDetails(orderDetails);
        //计算价格：
        double sum=0;
        for (OrderDetail detail:orders.getOrderDetails()){
            //判断是否是团体订单，以及订单数量是否满足最小数量
            if(orders.getDbTypes().getId().equals("order.group")&&detail.getOrDtMount()>=detail.getDesigner_product().getMinnum()){
                sum=+detail.getDesigner_product().getPdtPrc();
            }else{
                //正常价格
                sum=+detail.getDesigner_product().getPdtPrc();
            }
            //计算使用的图片的价格
            for(Material material:detail.getMaterials()){
                //计算图案jiage
                if(material.getPrice()!=null&&material.getPrice()!=0){
                    sum+=material.getPrice();
                }
            }
            //判断是否有折扣（如果使用优惠价就不能折扣？）
            if(orders.getDiscountCoupan().getId()!=null){
                DiscountCoupan discountCoupan = discountCoupanService.getOne(DiscountCoupan.class, orders.getDiscountCoupan().getId());
                //判断优惠卷是否有效
                if(checkCoupan(orders.getDiscountCoupan())){
                    //有效进行优惠卷使用
                    sum-=discountCoupan.getDisCouPrice();
                    //注销优惠卷
                }
            }else{
                Double discount=detail.getDesigner_product().getPdtDsct();
                if(discount!=null&&(discount>0d&&discount<1)){
                    sum=sum*discount;
                }else{
                    return  false;
                }
            }
        }
        return true;
    }

    public List<OrderDetail> getOrderDetails(String json) {

        Gson gson = new Gson();

        List<OrderDetail> ps = gson.fromJson(json, new TypeToken<List<OrderDetail>>() {
        }.getType());

        return ps;
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

    public boolean checkCoupan(DiscountCoupan coupan){

        return true;
    }

    public boolean off(DiscountCoupan discountCoupan){

        return  true;
    }
}