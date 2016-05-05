package com.xiaov.test;

public class HttpSenderTest {
	public static void main(String[] args) {
		String url = "http://222.73.117.156/msg/";// 应用地址
		String account = "zhuxinkeji";// 账号
		String pswd = "Admin888";// 密码
		String mobile = "13691726734";// 手机号码，多个号码使用","分割
		String msg = "您好，您的验证码是123456";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = null;// 扩展码

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
