package com.xiaov.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xiaov.example.model.UserModel;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;

/**
 * 
 * @类名称:BaseService
 * @类描述:提供基本的service接口供各个模块的service层使用
 * @创建时间:2016-3-25下午5:11:52
 * @作者: 龙华辉
 * @修改人:龙华辉
 * @修改时间:2016-3-25下午5:11:52
 * @修改备注:
 * @版本:v1.0
 */

public interface BaseService<T>{
	
    /**
     * 
     * @Description:删除,可传多个条件，关系等于和and，只传PK的时候是删除某个
     * @param @param Id
     * @return void
     * @throws
     */
    @Transactional
    void Delete(T entity);
    
    /**
     * 
     * @Description:保存或更新，如果记录没有ID则保存，有这更新
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    @Transactional
    void saveOrUpdate(T entity);

    /**
     * 
     * @Description:保存
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    @Transactional
    void save(T entity);
    
    /**
     * 查询所有 t中封装所有的查询条件 基于等于 和and
     * @param @return 
     * @return List<T>
     * @throws
     */
    List<T> loadAll(T entity);
    
    /**
     * 分页
     * @param page
     * @param filter
     * @return
     */
    public Page<T> page(Page<T> page);
}
