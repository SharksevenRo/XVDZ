package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Identification;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.IdentificationService;
import com.xiaov.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sharkseven on 2016/7/12.
 */
@Controller
public class IdentificationController {

    @Autowired
    private IdentificationService identificationService;

    @Autowired
    private UserService userService;

    @RequestMapping("/auth/user/identification")
    @ResponseBody
    public MessageBean auth(Identification identification){

        try {
            identificationService.save(identification);

            UserInfo one = userService.getOne(UserInfo.class, identification.getUserId());

            //将用户状态设置为认证中
            one.setUsState(1);
            userService.update(one);
            return new MessageBean(APPConstant.SUCCESS,"认证信息提交，请等待管理员审核");
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageBean(APPConstant.ERROR,"提交认证失败"+e.getMessage());
        }
    }


    @RequestMapping("/admin/identification/update")
    @ResponseBody
    public MessageBean auth(UserInfo user){

        try {

            Identification identification=new Identification();
            identification.setUserId(user.getId());
            Page<Identification> page = identificationService.page(identification);
            if(page.getResult().size()==1){
                    Identification temp = page.getResult().get(0);
                    UserInfo one = userService.getOne(UserInfo.class, identification.getUserId());
                    //将用户状态设置认证成功
                    if(user.getUsState().equals(2)){
                        one.setTypeId(temp.getType());
                    }
                    one.setUsState(user.getUsState());
                    userService.update(one);
                    return new MessageBean(APPConstant.SUCCESS,"认证信息提交，修改状态成功");
                }else{
                    return new MessageBean(APPConstant.ERROR,"认证信息提交，修改状态失败");
                }
        }catch (Exception e){
            e.printStackTrace();
            return  new MessageBean(APPConstant.ERROR,"修改状态失败"+e.getMessage());
        }
    }
}
