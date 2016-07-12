package com.xiaov.controller;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Advertisment;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AdvertismentService;
import com.xiaov.utils.LazyObjecUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouziyang on 4/24/16.
 */
@Controller
public class AdvertismentController {
    @Autowired
    private AdvertismentService advertismentService;

    /**
     * 
     * @Description: 添加广告
     * @param advertisment
     * @return MessageBean
     * @throws
     */
    @RequestMapping("/admin/advertisment/saveAjax")
    @ResponseBody
    public MessageBean saveAjax(Advertisment advertisment) {

        try {
            advertismentService.save(advertisment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
    
    /**
     * 
     * @Description: 修改广告
     * @param advertisment
     * @throws
     */
    @RequestMapping("/admin/advertisment/update")
    @ResponseBody
    public MessageBean updateAjax(Advertisment advertisment) {

        try {
            advertismentService.update(advertisment);
            return new MessageBean(APPConstant.SUCCESS, "上传成功");
        } catch (Exception e) {
            return new MessageBean(APPConstant.SUCCESS, "上传失败");
        }
    }
    
    /**
     * 
     * @Description:删除
     * @param @param advertisment
     * @param @return
     * @return MessageBean
     * @throws
     */
    @RequestMapping("/admin/advertisment/delete")
    @ResponseBody
    public MessageBean deleteAjax(Advertisment advertisment){

        try {
            advertisment=advertismentService.getOne(advertisment.getClass(), advertisment.getId());
            advertismentService.delete(advertisment);
            return new MessageBean(APPConstant.ERROR, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageBean(APPConstant.ERROR, "删除失败");
        }
    }

    /**
     * 
     * @Description:分页查询广告
     * @param @param advertisment
     * @param @return
     * @return Page<Advertisment>
     * @throws
     */
    @RequestMapping("/admin/advertisment/page")
    @ResponseBody
    public Page<Advertisment> page(Advertisment advertisment) {
    	 Page<Advertisment> page = new Page<Advertisment>();
        try {

        	page= advertismentService.page(advertisment);
        	LazyObjecUtil.LazyPageSetNull(page, new String[]{"userInfoByDeleteId","userInfoByUpdateId"});
        	return page;
        } catch (Exception e) {
           
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }

    /**
     * 
     * @Description:根据ID查询广告
     * @param @param advertisment
     * @param @return
     * @return Advertisment
     * @throws
     */
    @RequestMapping(value = "/admin/advertisment/getOneAjax", method = RequestMethod.POST)
    @ResponseBody
    public Advertisment getOne(Advertisment advertisment) {
        try {
            return advertismentService.getOne(advertisment.getClass(), advertisment.getAddId());

        } catch (Exception e) {
            Advertisment page = new Advertisment();
            page.setCode(APPConstant.ERROR);
            page.setMessage("服务器忙");
            return page;
        }
    }
    
    @RequestMapping(value = "/admin/advertisment/getSliderAds")
    @ResponseBody
    public Page<Advertisment> getSliderAds(Advertisment advertisment) {
    	Page<Advertisment> page = null;
    	try {	
    		page =  advertismentService.page(advertisment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    	return page;
    }
}
