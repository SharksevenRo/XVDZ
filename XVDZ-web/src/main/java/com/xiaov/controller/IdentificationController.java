package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.DiscountCode;
import com.xiaov.model.Identification;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.DiscountCodeService;
import com.xiaov.service.interfaces.IdentificationService;
import com.xiaov.service.interfaces.UserService;
import com.xiaov.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Sharkseven on 2016/7/12.
 */
@Controller
public class IdentificationController {

    @Autowired
    private IdentificationService identificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscountCodeService discountCodeService;

    @RequestMapping("/auth/user/identification")
    @ResponseBody
    public MessageBean auth(Identification identification) {

        try {
            Identification temp = new Identification();
            temp.setUserId(identification.getUserId());
            Page<Identification> page = identificationService.page(temp);

            if (page.getResult() != null && page.getResult().size() != 0) {
                return new MessageBean(APPConstant.ERROR, "认证信息提交失败，你已经提交过认证");
            }
            identification.setAddTime(new Date());
            identificationService.save(identification);


            UserInfo one = userService.getOne(UserInfo.class, identification.getUserId());

            //将用户状态设置为认证中
            one.setUsState(1);
            userService.update(one);
            return new MessageBean(APPConstant.SUCCESS, "认证信息提交，请等待管理员审核");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "提交认证失败" + e.getMessage());
        }
    }


    @RequestMapping("/admin/identification/update")
    @ResponseBody
    public MessageBean auth(UserInfo user) {

        try {

            Identification identification = new Identification();
            identification.setUserId(user.getId());
            Page<Identification> page = identificationService.page(identification);
            if (page.getResult().size() == 1) {
                Identification temp = page.getResult().get(0);
                UserInfo one = userService.getOne(UserInfo.class, identification.getUserId());
                //将用户状态设置认证成功
                if (user.getUsState().equals(2)) {
                    one.setTypeId(temp.getType());
                    //如果是业务员认证审核
                    if (StrKit.notBlank(user.getDiscountCode())&&temp.getType().equals("user.salesman")) {
                        DiscountCode code = new DiscountCode();
                        code.setSalesman(one.getId());
                        code.setCreateId(one.getId());
                        code.setDisCodeNo(user.getDiscountCode());
                        code.setAddTime(new Date());
                        if(user.getDisCodeNum()!=null&&user.getDisCodeNum()<1){
                            code.setDisCodeNum(user.getDisCodeNum());
                            discountCodeService.save(code);
                            one.setDisCodeNum(user.getDisCodeNum());
                        }else{
                            return new MessageBean(APPConstant.ERROR, "认证信息修改状态成功,抽成点大于1或者没有传");
                        }
                    }
                }
                one.setUsState(user.getUsState());
                userService.update(one);
                //删除认证信息
                identificationService.delete(temp);
                return new MessageBean(APPConstant.SUCCESS, "认证信息提交，修改状态成功");
            } else {
                return new MessageBean(APPConstant.ERROR, "认证信息提交，修改状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "修改状态失败" + e.getMessage());
        }
    }
}
