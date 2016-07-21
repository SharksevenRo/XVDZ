package com.xiaov.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaov.model.OrderDetail;
import com.xiaov.model.Orders;
import com.xiaov.model.Product;
import com.xiaov.utils.Pinyin4jUtil;

import java.util.ArrayList;
import java.util.List;

public class TestPinYin {
    public static void main(String[] args) {

        String str = "{\"ordersdetals\":[\n" +
                "\n" +
                "{ \"id\": \"Brett\", \"orNo\":\"McLaughlin\"},\n" +
                "\n" +
                "{ \"id\": \"Jason\", \"orNo\":\"Hunter\"},\n" +
                "\n" +
                "{ \"id\": \"Elliotte\", \"orNo\":\"Harold\"}\n" +
                "\n" +
                "]}";
        Gson gson = new Gson();

       /* List<OrderDetail> ps = gson.fromJson(str, new TypeToken<List<OrderDetail>>() {
        }.getType());
        for (int i = 0; i < ps.size(); i++) {
            OrderDetail p = ps.get(i);
            System.out.println(p.toString());
        }
        str = "女孩子";
        String spellNoneTone = Pinyin4jUtil.spellNoneTone(str);
        System.out.println(spellNoneTone.replace("ü", ""));
        System.out.println(spellNoneTone);*/

        List<Orders> orderses=new ArrayList<Orders>();

        Orders order=new Orders();
        order.setId("1");

        List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();

        OrderDetail orderDetail=new OrderDetail();
/*
        Product product=new Product();
        product.setId("4028e3e9544e0e6501544e1063780001");

        orderDetail.setDesigner_product(product);

        orderDetails.add(orderDetail);
        orderDetail.setImage_front("40283f83550fce5e01550fd750df0000_40283f83550fce5e01550fd75d400003");

         orderses.add(order);
        order.setOrderDetails(orderDetails);

        String s = gson.toJson(orderses);*/

    }


}
