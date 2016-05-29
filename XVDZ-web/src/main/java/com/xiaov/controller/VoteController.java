package com.xiaov.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Vote;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.service.interfaces.VoteService;
import com.xiaov.utils.CompressPicUtil;
import com.xiaov.utils.Pinyin4jUtil;
import com.xiaov.utils.UploadFileUtil;
import com.xiaov.web.support.CookieUtil;

@Controller
public class VoteController extends BaseController {

	@Autowired
	private VoteService voteService;
	private static int bufSize = 512; // size of bytes
	private byte[] buf;
	private int readedBytes;

	@RequestMapping("/user/vote")
	@ResponseBody
	public MessageBean vote(Vote vote,HttpServletRequest request) {
		String openId = new CookieUtil(request).getValue("user", "openId", true);
		vote.setOpenId(openId);
		if (!voteService.isRepeate(vote)) {
			
			voteService.vote(vote);
			return new MessageBean(APPConstant.SUCCESS, "投票成功");
		} else {
			return new MessageBean(APPConstant.ERROR, "您已为该选选手投票，不能重复投票");
		}
	}

	@RequestMapping("/user/top")
	@ResponseBody
	public List<Vote> top(Vote vote) {
		try {
			return voteService.top(null);
		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping("/admin/infoSave")
	@ResponseBody
	public MessageBean infoSave(Vote vote, MultipartFile touxiang,
			MultipartFile tupian) {
		File file=null;
		InputStream inputStream = null;
		FileOutputStream fileOut = null;
		String contextPath = request.getRealPath("/");
		// 新文件名
		String newFileName1 = new Date().getTime() + ".jpg";
		String newFileName2 = new Date().getTime() + "2.png";
		try {
			String tempPath = "images/vote/temp/";
			//压缩文件路径
			String basePath = "images/vote/compress/";
			// 设置正常保存路径域
			//创建文件夹和文件
			file= new File(contextPath+tempPath );
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(contextPath+basePath );
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(contextPath+basePath+"/"+newFileName1);
			if (!file.exists()) {
				file.createNewFile();
			}
			file = new File(contextPath+tempPath+"/"+newFileName1);
			if (!file.exists()) {
				file.createNewFile();
			}
			//获取压缩包中的图片
			inputStream = touxiang.getInputStream();
			fileOut = new FileOutputStream(file);
			this.buf = new byte[this.bufSize];
			//写到临时文件
			while ((this.readedBytes = inputStream.read(this.buf)) > 0) {
				fileOut.write(this.buf, 0, this.readedBytes);
			}
			fileOut.flush();
			fileOut.close();
			
			file= new File(contextPath+tempPath );
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(contextPath+basePath );
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(contextPath+basePath+"/"+newFileName2);
			if (!file.exists()) {
				file.createNewFile();
			}
			file = new File(contextPath+tempPath+"/"+newFileName2);
			if (!file.exists()) {
				file.createNewFile();
			}
			//获取压缩包中的图片
			inputStream = touxiang.getInputStream();
			fileOut = new FileOutputStream(file);
			this.buf = new byte[this.bufSize];
			//写到临时文件
			while ((this.readedBytes = inputStream.read(this.buf)) > 0) {
				fileOut.write(this.buf, 0, this.readedBytes);
			}
			fileOut.flush();
			fileOut.close();
			
			CompressPicUtil mypic1 = new CompressPicUtil();
			mypic1.resizePNG(contextPath+tempPath+"/"+newFileName1,  contextPath+basePath+"/"+newFileName1, 200, 200, true);
			// 压缩1
			vote.setPic1(contextPath+basePath+"/"+newFileName1);
			CompressPicUtil mypic2 = new CompressPicUtil();
			mypic1.resizePNG(contextPath+basePath+"/"+newFileName1,  contextPath+basePath+"/"+newFileName1, 200, 200, true);
			vote.setPic2(contextPath+basePath+"/"+newFileName1);
			voteService.save(vote);
			return new MessageBean(APPConstant.SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "添加失败");
		}
	}
}
