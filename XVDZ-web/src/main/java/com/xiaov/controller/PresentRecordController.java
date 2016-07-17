package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Account;
import com.xiaov.model.PresentRecord;
import com.xiaov.model.User;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AccountService;
import com.xiaov.service.interfaces.PresentRecordService;
import com.xiaov.service.interfaces.UserService;
import com.xiaov.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Controller
public class PresentRecordController {
    @Autowired
    private PresentRecordService resentRecordService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @RequestMapping("/auth/record/save")
    @ResponseBody
    public MessageBean save(PresentRecord record) {

        try {
            if(!StrKit.isBlank(record.getUserId())){

                UserInfo user=userService.getOne(UserInfo.class,record.getUserId());

                //判断用户是否存在
                if(user==null){
                    return new MessageBean(APPConstant.ERROR, "非法请求");
                }else{

                    if(StrKit.isBlank(record.getReceiver_account())){
                        return new MessageBean(APPConstant.SUCCESS, "请填写接受账户");
                    }
                    if(StrKit.isBlank(record.getAccount_type())){
                        return new MessageBean(APPConstant.SUCCESS, "请填写接受账户的账户类型");
                    }
                    //获取用户的账户
                    Account accountByUser = accountService.getAccountByUser(user);
                    //判断账户余额和提现金额
                    if(record.getAmount()<=accountByUser.getActMm()){

                        record.setPresent_time(new Date());
                        //更新提现状态的金额
                        accountByUser.setPresent_amount(accountByUser.getPresent_amount()+record.getAmount());
                        accountService.update(accountByUser);
                        //插入提现记录
                        record.setState(0);
                        resentRecordService.save(record);
                        return new MessageBean(APPConstant.SUCCESS, "提现申请成功");

                    }else{
                        return new MessageBean(APPConstant.ERROR, "提现金额大于账户余额");
                    }
                }
            }else{
                return new MessageBean(APPConstant.ERROR, "非法请求");

            }
        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "服务异常");
        }
    }

    @RequestMapping("/admin/record/update")
    @ResponseBody
    public MessageBean update(PresentRecord record) {

        try {
            PresentRecord one = resentRecordService.getOne(PresentRecord.class, record.getId());

            if(one!=null){
                record.setArrival_time(new Date());
                UserInfo user=userService.getOne(UserInfo.class,one.getUserId());

                if(user!=null){
                    Account accountByUser = accountService.getAccountByUser(user);
                    //更新处于提现状态的金额
                    accountByUser.setPresent_amount(accountByUser.getPresent_amount()-record.getAmount());
                    accountService.update(accountByUser);
                    //更新提现状态,提现完成
                    record.setState(1);
                    resentRecordService.update(record);
                    return new MessageBean(APPConstant.SUCCESS, "操作成功");
                }
                return new MessageBean(APPConstant.ERROR, "操作失败");
            }else{
                return new MessageBean(APPConstant.ERROR, "操作失败");
            }

        } catch (Exception e) {
            return new MessageBean(APPConstant.ERROR, "操作失败");
        }
    }

    @RequestMapping("/auth/record/page")
    @ResponseBody
    public Page<PresentRecord> page(PresentRecord record) {
        try {

            if(record.getUserId()==null){
                record.setCode(APPConstant.ERROR);
                record.setMessage("非法请求");
                return  record;
            }
            return resentRecordService.page(record);
        } catch (Exception e) {
            Page<PresentRecord> page = new Page<PresentRecord>();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    @RequestMapping(value = "/admin/record/getOne", method = RequestMethod.POST)
    @ResponseBody
    public PresentRecord getOne(PresentRecord record) {
        try {
            return resentRecordService.getOne(record.getClass(), record.getId());

        } catch (Exception e) {
            PresentRecord page = new PresentRecord();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

}
