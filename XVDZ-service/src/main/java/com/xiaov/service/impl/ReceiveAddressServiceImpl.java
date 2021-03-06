package com.xiaov.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.dao.ReceiveAddressDao;
import com.xiaov.model.ReceiveAddress;
import com.xiaov.service.interfaces.ReceiveAddressService;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class ReceiveAddressServiceImpl extends BaseServiceImpl<ReceiveAddress> implements ReceiveAddressService {

	private static Logger logger = LoggerFactory.getLogger(ReceiveAddressServiceImpl.class);
    @Autowired
    private ReceiveAddressDao receiveAddressDao;

    @Override
    public void delete(ReceiveAddress entity) {

        super.delete(entity);
    }

    @Override
    public List<ReceiveAddress> loadAll(ReceiveAddress entity) {

        return super.loadAll(entity);
    }

    @Override
    @Transactional
    public void save(ReceiveAddress entity) {

//    	ReceiveAddress temp=new ReceiveAddress();
//    	temp.setAddDefault("1");
//    	temp.setUserInfo(entity.getUserInfo());
//    	//获取默认地址
//    	List<ReceiveAddress> defaultAdd =loadAll(temp);
//    	if(defaultAdd.size()==1){
//    		//修改默认地址
//    		ReceiveAddress address = defaultAdd.get(0);
//    		address.setAddDefault("0");
//    		receiveAddressDao.update(address);
//    		entity.setAddDefault("1");
//            super.save(entity);
//    	}else{
//    		logger.error("默认地址异常");
//    		throw new RuntimeException("默认地址异常");
//    	}
        super.save(entity);
    	
    }

    @Override
    public void update(ReceiveAddress entity) {

        super.update(entity);
    }

    @Override
    public ReceiveAddress getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
    public  List<ReceiveAddress> getByProperty(String propertynmae,String propertyvalues){
        List<ReceiveAddress> result ;
        Criterion [] criterions={Restrictions.eq(propertynmae,propertyvalues),Restrictions.eq("deleteFlag",0)};
        result= dao.getEntitiestNotLazy(new ReceiveAddress(),new String[]{"userInfo"},criterions);
        return result;
    }

}
