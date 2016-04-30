package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.AdvertismentDao;
import com.xiaov.model.Advertisment;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AdvertismentService;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class AdvertismentServiceImpl extends BaseServiceImpl<Advertisment> implements AdvertismentService {
    @Autowired
    private AdvertismentDao advertismentDao;

    @Override
    public void delete(Advertisment entity) {

        super.delete(entity);
    }

    @Override
    public List<Advertisment> loadAll(Advertisment entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(Advertisment entity) {

        super.save(entity);
    }

    @Override
    public void update(Advertisment entity) {

        super.update(entity);
    }

    @Override
    public Advertisment getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }

	/**
	 * 
	 * @Description:获取轮播图的广告
	 * @param requestPage
	 * @return Page<Advertisment>
	 * @throws
	 */
	public Page<Advertisment> getSliderAds(Page<Advertisment> requestPage) {
		try {
			Object[] param = {false,true,requestPage.getPageSize()*(requestPage.getPageNo()-1),requestPage.getPageSize()};
			List<Advertisment> list = advertismentDao.findByQuery("select from advertisement where deleteFlag=? and ads_state=? limit ?,?", param);
			requestPage.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestPage;
	}
}
