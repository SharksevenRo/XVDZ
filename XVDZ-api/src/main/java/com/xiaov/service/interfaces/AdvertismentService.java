package com.xiaov.service.interfaces;

import com.xiaov.model.Advertisment;
import com.xiaov.orm.core.Page;
import com.xiaov.service.BaseService;

/**
 * Created by zouziyang on 4/18/16.
 */
public interface AdvertismentService extends BaseService<Advertisment> {

	/**
	 * @Description:获取轮播图广告
	 * @paramrequestPage
	 * @return Page<Advertisment>
	 * @throws 
	 */
	Page<Advertisment> getSliderAds(Page<Advertisment> requestPage);

}
