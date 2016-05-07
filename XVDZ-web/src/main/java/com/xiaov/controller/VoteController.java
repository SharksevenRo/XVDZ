package com.xiaov.controller;

import java.util.Date;
import java.util.List;

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
import com.xiaov.utils.UploadFileUtil;
import com.xiaov.web.support.CookieUtil;

@Controller
public class VoteController extends BaseController {

	@Autowired
	private VoteService voteService;

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

	@RequestMapping("/user/infoSave")
	@ResponseBody
	public MessageBean infoSave(Vote vote, MultipartFile touxiang,
			MultipartFile tupian) {
		String contextPath = request.getRealPath("/");
		// 新文件名
		String newFileName1 = new Date().getTime() + ".jpg";
		String newFileName2 = new Date().getTime() + "2.jpg";
		try {
			
			// 设置正常保存路径域
			String[] normalScope = { "normal", "vote" };
			// 创建图片上传对象，这是正常路径
			UploadFileUtil fileUtil = new UploadFileUtil(contextPath,
					normalScope);

			// 设置压缩保存路径域
			String[] compressScope = { "compress", "vote" };
			// 压缩1
			String touxiangCompressTargetPath = fileUtil.savePicWithCompress(
					touxiang, newFileName1, compressScope, false);
			String dbCompress1Url = fileUtil.getNormalRelativeFolderPath()+newFileName1;
			vote.setPic1(dbCompress1Url);
			// 压缩2
			String tupianCompressTargetPath = fileUtil.savePicWithCompress(
					tupian, newFileName2, compressScope, false);
			String dbCompress2Url = fileUtil.getNormalRelativeFolderPath()+newFileName2;
			vote.setPic2(dbCompress2Url);
			voteService.save(vote);
			return new MessageBean(APPConstant.SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "添加失败");
		}
	}
}