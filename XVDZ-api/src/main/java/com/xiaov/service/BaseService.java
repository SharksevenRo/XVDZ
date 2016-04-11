package com.xiaov.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
	 * @Description:添加记录
	 * @param entity 添加记录
	 * @return int
	 * @throws
	 */
    @Transactional
	int insert(T entity);
    
    /**
     * 
     * @Description:假删除记录
     * @param Id 删除记录主键ID
     * @return void
     * @throws
     */
    @Transactional
    void fakeDelete(String Id);
    /**
     * 
     * @Description:真删除
     * @param @param Id
     * @return void
     * @throws
     */
    @Transactional
    void realDelete(String Id);
    
    /**
     * 
     * @Description:根据主键查询
     * @param @param Id
     * @param @return
     * @return T
     * @throws
     */
    T selectById(String Id);
    
    /**
     * 
     * @Description:保存或更新，如果记录没有ID则保存，有这更新
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    @Transactional
    int saveOrUpdate(T entity);

    /**
     * 
     * @Description:保存
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    @Transactional
    int save(T entity);
    
    /**
     * 查询所有
     * @Description:分页查询
     * @param @return
     * @return List<T>
     * @throws
     */
    List<T> loadAll(int start,int pageSize);
}
