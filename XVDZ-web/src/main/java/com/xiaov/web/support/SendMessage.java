package com.xiaov.web.support;

/**
 * Created by zouziyang on 5/5/16.
 */
public class SendMessage {
    private String url = "http://222.73.117.156/msg/";// 应用地址
    private String account = "zhuxinkeji";// 账号
    private String pswd = "Admin888";// 密码
    private String mobile;// 手机号码，多个号码使用","分割
    private String msg;// 短信内容
    private boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
    private String extno = null;// 扩展码


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void SeedMessageAPI(){
        try {
            String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus,null, extno);
            System.out.println(returnString);
            // TODO 处理返回值,参见HTTP协议文档
        } catch (Exception e) {
            // TODO 处理异常
            e.printStackTrace();
        }
    }
}
