package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.*;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.impl.UserServiceImpl;
import com.xiaov.service.interfaces.DiscountCodeService;
import com.xiaov.service.interfaces.DiscountCodeUseRecordService;
import com.xiaov.service.interfaces.InnerSessionService;
import com.xiaov.service.interfaces.UserService;
import com.xiaov.utils.*;
import com.xiaov.web.support.AuthenticationCahce;
import com.xiaov.web.support.CookieUtil;
import com.xiaov.web.support.SendMessage;
import com.xiaov.web.support.UserToken;
import org.apache.tools.ant.taskdefs.email.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceimpl;
    @Autowired
    private InnerSessionService innerSessionService;

    @Autowired
    private DiscountCodeService discountCodeService;

    @Autowired
    private DiscountCodeUseRecordService discountCodeUseRecordService;


    private String path;
    private static int bufSize = 512; // size of bytes
    private byte[] buf;
    private int readedBytes;

    // 获取所有
    @RequestMapping("/admin/user/all")
    @ResponseBody
    public List<UserInfo> getAll(UserInfo user) {
        List<UserInfo> users = userService.loadAll(user);
        // 没有关联对象
        // LazyObjecUtil.LazySetNull(users, "")
        return users;
    }

    // 添加
    @RequestMapping("/admin/user/save")
    @ResponseBody
    public MessageBean save(String telCode, String disCodeNo, String rePwd, String usPwd, String usTel,
                            String activeCode, String key) {


        InnerSession one = innerSessionService.getOne(InnerSession.class, key);

        if (one != null) {
            if ((System.currentTimeMillis() - one.getBegin()) <= one.getTime()) {

                if (!usTel.equals(one.getKey())) {

                    innerSessionService.delete(one);
                    return new MessageBean(APPConstant.ERROR, "手机号码有误");
                }
                if (one.getToken().equals(telCode)) {
                    if (usPwd.equals(rePwd)) {
                        try {
                            List<UserInfo> userIsAdd = userServiceimpl.getByProperty("usTel", usTel);
                            if (userIsAdd.isEmpty()) {
                                if (disCodeNo != null&&!"".equals(disCodeNo)) // 是否绑定优惠码
                                {
                                    List<DiscountCode> discode = discountCodeService.getByProperty("disCodeNo", disCodeNo);
                                    if (discode.size()!=1)// 优惠码是否存在
                                    {
                                        innerSessionService.delete(one);
                                        return new MessageBean(APPConstant.ERROR, "优惠码不存在或错误,请重新注册!");

                                    } else {
                                        UserInfo user = new UserInfo();
                                        user.setUsTel(usTel);
                                        user.setUsName(usTel);
                                        user.setUsPwd(Md5.GetMD5Code(usPwd));
                                        user.setUsSex("保密");
                                        user.setAddTime(new Date());
                                        user.setTypeId("user.common");
                                        userService.save(user); // 注册用户
                                        DiscountCodeUseRecord discountCodeUseRecord = new DiscountCodeUseRecord();
                                        discountCodeUseRecord.setUserInfo(user);
                                        discountCodeUseRecord.setDisCodeId(discode.get(0).getId());
                                        discountCodeUseRecord.setUseTime(new Date());

                                        discountCodeUseRecordService.save(discountCodeUseRecord);// 绑定优惠码
                                        innerSessionService.delete(one);
                                        return new MessageBean(1, "注册成功!");
                                    }
                                } else {
                                    UserInfo user = new UserInfo();
                                    user.setUsTel(usTel);
                                    user.setUsName(usTel);
                                    user.setUsPwd(Md5.GetMD5Code(usPwd));
                                    user.setUsSex("保密");
                                    user.setAddTime(new Date());
                                    user.setTypeId("user.common");
                                    userService.save(user);// 注册用户
                                    innerSessionService.delete(one);
                                    return new MessageBean(1, "注册成功!");
                                }
                            } else {
                                innerSessionService.delete(one);
                                return new MessageBean(APPConstant.ERROR, "该手机号已被注册!");
                            }
                        } catch (Exception e) {
                            innerSessionService.delete(one);
                            return new MessageBean(APPConstant.ERROR, "系统错误!");
                        }
                    } else {
                        innerSessionService.delete(one);
                        return new MessageBean(-2, "两次密码不匹配");
                    }
                } else {
                    innerSessionService.delete(one);
                    return new MessageBean(-3, "验证码错误!");
                }
            } else {

                innerSessionService.delete(one);
                return new MessageBean(APPConstant.ERROR, "验证码过时，请重新获取");
            }

        } else {
            return new MessageBean(APPConstant.ERROR, "请获取验证码");
        }

    }

    /**
     * 如果要向前途返回json格式数据，加@ResponseBody，返回要返回的对象，
     * 如果查询的结果有代理对象调用LazyObjecUtil.LazyPageSetNull去除代理对象
     * 如果关联对象数据也要带到前台，选择动态关闭延迟加载的方式查询数据库，方法见UserServiceImp page方法中的实例
     *
     * @param
     * @param page
     * @return
     */
    // 分页查询
    @RequestMapping("/admin/user/page")
    @ResponseBody
    public Page<UserInfo> page(UserInfo page) {

        Page<UserInfo> results = null;
        try {

            results = userService.page(page);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            results.setCode(APPConstant.ERROR);
            results.setMessage("服务器正忙，请重试");
            return results;
        }

    }
    @RequestMapping("/auth/user/page/bydiscountCode")
    @ResponseBody
    public Page<UserInfo> getUserByDiscountCode(UserInfo user){

        DiscountCode code=new DiscountCode();
        code.setSalesman(user.getId());
        Page<DiscountCode> page = discountCodeService.page(code);

        if(page.getResult().size()==1){

            code=page.getResult().get(0);
            user.setId(null);
            user.setTypeId("user.common");
            user.setDiscountCode(code.getDisCodeNo());
            Page<UserInfo> users = userService.page(user);
            return users;
        }else{
            user.setCode(APPConstant.ERROR);
            user.setMessage("请求异常,没有优惠码你");
            return user;
        }

    }
     // 删除
    @RequestMapping("/admin/user/delete")
    @ResponseBody
    public MessageBean deleteAjax(UserInfo user) {
        try {
            user = userService.getOne(user.getClass(), user.getId());
            userService.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
        return new MessageBean(APPConstant.SUCCESS, "删除成功");
    }

    // 删除
    @RequestMapping("/auth/user/update")
    @ResponseBody
    public MessageBean updateAjax(UserInfo user) {
        try {

            if (null != user.getUsPwd() && !"".equals(user.getUsPwd())) {

                String newPwd = Md5.GetMD5Code(user.getUsPwd());


                String pwd = userService.getOne(user.getClass(), user.getId()).getUsPwd();

                if (pwd.equals(Md5.GetMD5Code(user.getOldPwd()))) {
                    user.setUsPwd(newPwd);
                } else {
                    return new MessageBean(APPConstant.ERROR, "修改错误，密码错误");

                }
            }

            userService.update(user);
            return new MessageBean(APPConstant.ERROR, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.SUCCESS, "服务器异常，修改失败");
        }

    }

    @RequestMapping("/auth/user/head/update")
    @ResponseBody
    public MessageBean updateHead(UserInfo user,MultipartFile head, HttpServletRequest request) {

        String s = saveFile(head, request);
        user.setUsPic(s);
        userService.update(user);
        return new MessageBean(APPConstant.SUCCESS, "修改头像成功");

    }

    @RequestMapping("/admin/user/getOne")
    @ResponseBody
    public UserInfo getOne(UserInfo user) {
        try {
            user = userService.getOne(user.getClass(), user.getId());
            if (user != null) {
                user = LazyObjecUtil.LazyOneSetNull(user, "role");
            }
            return user;
        } catch (Exception e) {
            user.setCode(APPConstant.ERROR);
            return user;
        }

    }


    @RequestMapping("/admin/telCode/getTelCode")
    @ResponseBody
    public MessageBean sendMessageCheck(String usTel) {


        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setMobile(usTel);
            String rand = String.valueOf(new Random().nextInt(999999));
            sendMessage.setMsg("您好，您的验证码是" + rand);
            System.out.println(usTel);
            System.out.println(sendMessage.getMsg());
            sendMessage.SeedMessageAPI();
            InnerSession innerSession = new InnerSession();
            innerSession.setKey(usTel);
            innerSession.setToken(rand);
            innerSession.setTime(60000L);
            innerSession.setBegin(System.currentTimeMillis());
            innerSessionService.save(innerSession);
            return new MessageBean(APPConstant.SUCCESS, innerSession.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "获取短信验证码失败");
        }


    }

    @RequestMapping("/client/user/login")
    @ResponseBody
    public UserInfo login_user(UserInfo user, HttpServletRequest request, HttpServletResponse response) {
        UserInfo temp = new UserInfo();
        temp.setUsTel(user.getUsTel());
        try {
            Page<UserInfo> page = userService.page(temp);
            if (page.getResult().size() == 1) {
                temp = page.getResult().get(0);
                String pwd = Md5.GetMD5Code(user.getUsPwd());
                String uspwd = temp.getUsPwd();
                if (uspwd.equals(pwd)) {
                    UserToken token = AuthenticationCahce.put(temp.getId());
                    temp.setAuthCode(token.getToken());
                    temp.setUsPwd("");
                } else {
                    temp.setCode(APPConstant.ERROR);
                    temp.setMessage("登录失败");
                }
            } else {
                temp.setCode(APPConstant.ERROR);

                temp.setMessage("手机或密码错误，请重试");
            }
        } catch (Exception e) {

        }
        return temp;

    }

    @RequestMapping("/admin/adminLogin")
    public void login(UserInfo user, HttpServletRequest request, HttpServletResponse response) {

        try {
            List<UserInfo> loadAll = userService.loadAll(user);

            if (loadAll.size() == 1) {

                CookieUtil util = new CookieUtil(request);

                UserToken token = AuthenticationCahce.put(loadAll.get(0).getId());
                util.setValue("login", "user.token", token.getToken(), true);
                util.setValue("login", "user.userId", loadAll.get(0).getId(), true);

                util.save(response, "login", true);
                response.sendRedirect("index.jsp");
                return;
            } else {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("login.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 分页查询
    @RequestMapping("/admin/user/recommand/page")
    @ResponseBody
    public Page<UserInfo> recommandPage(UserInfo page) {

        Page<UserInfo> results = null;
        try {
            page.setIsRecommend(1);
            page.setTypeId("user.designer");
            results = userService.page(page);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            results.setCode(APPConstant.ERROR);
            results.setMessage("服务器正忙，请重试");
            return results;
        }

    }

    // 分页查询
    @RequestMapping("/admin/user/talent/page")
    @ResponseBody
    public Page<UserInfo> talentPage(UserInfo page) {


        Page<UserInfo> results = null;
        try {

            page.setIsTalent(1);
            page.setTypeId("user.designer");
            results = userService.page(page);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            results.setCode(APPConstant.ERROR);
            results.setMessage("服务器正忙，请重试");
            return results;
        }
    }

    /**
     * 管理员审核认证
     *
     * @param user
     * @return
     */
    @RequestMapping("/admin/user/identification")
    @ResponseBody
    public MessageBean identification(UserInfo user) {

        userService.update(user);
        return new MessageBean(APPConstant.SUCCESS, "认证成功");

    }


    /**
     * 图片上传和保存
     *
     * @param img
     * @param request
     * @return
     */
    private String saveFile(MultipartFile img, HttpServletRequest request) {
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        File file = null;
        try {
            path = request.getRealPath("/");
            String[] split = img.getOriginalFilename().split("[.]");
            String suffix = "." + exChange(split[split.length - 1]);
            //临时文件路径
            String tempPath = "images/designer/temp/";
            //压缩文件路径
            String basePath = "images/designer/compress/";
            //创建文件夹和文件
            file = new File(path + tempPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path + basePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = UUID.randomUUID() + suffix;
            file = new File(path + basePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            file = new File(path + tempPath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //获取压缩包中的图片
            inputStream = img.getInputStream();
            fileOut = new FileOutputStream(file);
            this.buf = new byte[this.bufSize];
            //写到临时文件
            while ((this.readedBytes = inputStream.read(this.buf)) > 0) {
                fileOut.write(this.buf, 0, this.readedBytes);
            }
            fileOut.flush();
            fileOut.close();

            //文件压缩
            CompressPicUtil mypic = new CompressPicUtil();
            mypic.resizePNG(path + tempPath + "/" + fileName, path + basePath + "/" + fileName, 200, 200, true);

            return tempPath + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/atuh/user/gen/qrcode")
    @ResponseBody
    public MessageBean genQRCode(UserInfo user,HttpServletRequest request){

        try {
            List<DiscountCode> salesman = discountCodeService.getByProperty("salesman", user.getId());
            if(salesman.size()!=1){
                return new MessageBean(APPConstant.ERROR,"数据错误，该业务员有两个以上优惠码或者无优惠吗,请检查");
            }
            String url= "http://store.xiaovdingzhi.com/index.php?page=style&coupon="+salesman.get(0).getDisCodeNo();
            String path=request.getRealPath("/");
            String fileName=UUID.randomUUID().toString();
            QRCodeUtil.encode(url, path+"img/QRCode/xiaoV.jpg", path+"image/salseman/QRCode",fileName, true);
            user.setQRCode_img("image/salseman/QRCode/"+fileName+".jpg");
            userService.update(user);
            return new MessageBean(APPConstant.SUCCESS,user.getQRCode_img());
        }catch (Exception e){
            return new MessageBean(APPConstant.ERROR,"生成二维码失败"+e.getMessage());
        }


    }
    @ResponseBody
    @RequestMapping("/admin/designer/search")
    public Page<SearchModel> search(SearchModel search) {

        try {

            List<UserInfo> users = userService.search(search);
            search.setResult(users);
            return search;

        } catch (Exception e) {
            search.setCode(APPConstant.ERROR);
            search.setMessage("服务器异常" + e.getMessage());
            return search;
        }
    }
    /**
     * 字母小写转大写
     * @param str
     * @return
     */
    public static String exChange(String str){
        StringBuffer sb = new StringBuffer();
        if(str!=null){
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(Character.isUpperCase(c)){
                    sb.append(Character.toLowerCase(c));
                }else{
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    public String getString(HttpServletRequest request){
        try{
            request.setCharacterEncoding("UTF-8");
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            char[] buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
            return  sb.toString();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private  UserInfo packageParam(String strs){
        try{
            Class clazz=Product.class;
            Object user=clazz.newInstance();
            if(StrKit.notBlank(strs)){
                String[] split = strs.split("[&]");
                for (String str:split){

                    String[] split1 = str.split("[=]");
                    if(split1.length==2){
                        Class<?> type = ReflectionUtils.getAccessibleField(clazz, split1[0]).getType();
                        if(type.getSimpleName().equals("Integer")){
                            ReflectionUtils.invokeSetterMethod(user,split1[0],Integer.valueOf(split1[1]),type);
                        }else if(type.getSimpleName().equals("Long")){
                            ReflectionUtils.invokeSetterMethod(user,split1[0],Long.valueOf(split1[1]),type);
                        }else if(type.getSimpleName().equals("Double")){
                            ReflectionUtils.invokeSetterMethod(user,split1[0],Double.valueOf(split1[1]),type);
                        }else{
                            ReflectionUtils.invokeSetterMethod(user,split1[0],split1[1],type);
                        }

                    }
                }
                return (UserInfo) user;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
