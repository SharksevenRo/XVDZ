package com.xiaov.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaov.constant.APPConstant;
import com.xiaov.model.*;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.*;
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
    private StyleService styleService;
    @Autowired
    private DiscountCoupanService discountCoupanService;


    @RequestMapping("/auth/orders/save")
    @ResponseBody
    public Orders saveAjax(Orders orders) {

        try {
            //检查订单
            orders=checkOrders(orders);
            orders.setOrState(Orders.UNPAY);
            orders.setAddTime(new Date());
            ordersService.save(orders);
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            orders.setCode(APPConstant.ERROR);
           orders.setMessage("服务器异常"+e.getMessage());
           return orders;
        }
    }

    @RequestMapping("/auth/orders/update")
    @ResponseBody
    public MessageBean updateAjax(Orders orders) {

        try {
            ordersService.update(orders);
            return new MessageBean(APPConstant.SUCCESS, "修改成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "修改失败"+e.getMessage());
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
        try {


            List<Orders> orderes = ordersService.pageOrder(orders);
           String [] fields= new String[]{"user", "discountCoupan", "dbTypes"};
            LazyObjecUtil.LazySetNull(orderes,fields);
            orders.setResult(orderes);
            return orders;
        } catch (Exception e) {
            orders.setCode(APPConstant.ERROR);
            orders.setMessage("服务器忙");
            return orders;
        }
    }
    @RequestMapping("/auth/orders/detail")
    @ResponseBody
    public Page<Orders> detail(Orders orders) {

        try{

            List<Orders> orderDetail = ordersService.getOrderDetail(orders);
            String[] fields = new String[]{"user", "dbTypes", "discountCoupan"};
            orderDetail= LazyObjecUtil.LazySetNull(orderDetail,fields);
            orders.setResult(orderDetail);
            orders.setCode(APPConstant.SUCCESS);
            orders.setOrderDetails(null);
            orders.setDbTypes(null);
            orders.setDiscountCoupan(null);
            return  orders;
        } catch (Exception e) {

            orders.setCode(APPConstant.ERROR);
            orders.setMessage("获取失败"+orders.getMessage());
            return orders;
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
            page.setResult(ordersService.getOrderDetail(orders));
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
    private Orders checkOrders(Orders orders) {


        boolean isDiscount=false;
        Product one=null;

        List<OrderDetail> orderDetails = getOrderDetails(orders.getMutiType());
        orders.setOrderDetails(orderDetails);
        //计算价格：
        Double sum=0d;
        Double realCost=0d;
        for (OrderDetail detail : orderDetails
                ) {

            one= productService.getOne(Product.class, detail.getProduct_id());
            if(orders.getDbTypes().getId().equals("order.group")){


                //判断是否是团体订单，并判读该商品是否支持团体定制
                if(one.getIsGroup()==1&&detail.getCount()>=one.getMinnum()){
                    sum=+one.getGroupPrice()*detail.getCount();
                    realCost=one.getGroupPrice()*detail.getCount();
                    isDiscount=false;
                }else{
                    sum+=one.getPdtPrc()*detail.getCount();
                    realCost=one.getGroupPrice()*detail.getCount();
                    //判断是否打折
                    if(one.getPdtDsct()!=null&&one.getPdtDsct()!=0&&one.getPdtDsct()<1){
                        sum=sum*one.getPdtDsct();
                        isDiscount=false;
                    }
                }
            }else{
                sum+=one.getPdtPrc()*detail.getCount();
                realCost=one.getPdtPrc()*detail.getCount();
                //判断是否打折
                if(one.getPdtDsct()!=null&&one.getPdtDsct()!=0&&one.getPdtDsct()<1){
                    sum=sum*one.getPdtDsct();
                    isDiscount=false;
                }
            }
        }
        if(orders.getDiscountCoupan().getId()!=null){
            DiscountCoupan discountCoupan = discountCoupanService.getOne(DiscountCoupan.class, orders.getDiscountCoupan().getId());
            //判断优惠卷是否有效
            if(checkCoupan(orders.getDiscountCoupan())){
                //有效进行优惠卷使用
                sum-=discountCoupan.getDisCouPrice();
                //注销优惠卷
                off(discountCoupan);
            }
        }
        orders.setOrTotal(realCost);
        orders.setOrRealCost(sum);
        return orders;
    }

    public List<OrderDetail> getOrderDetails(String json) {

        Gson gson = new Gson();

        List<OrderDetail> ps = gson.fromJson(json, new TypeToken<List<OrderDetail>>() {
        }.getType());

        return ps;
    }

    public boolean checkCoupan(DiscountCoupan coupan){

        return true;
    }

    public boolean off(DiscountCoupan discountCoupan){

        discountCoupanService.delete(discountCoupan);
        return  true;
    }
}