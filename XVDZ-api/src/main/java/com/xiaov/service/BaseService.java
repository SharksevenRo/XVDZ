package com.xiaov.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务类基本接口
 */
import com.xiaov.orm.core.Page;



public interface BaseService<T>{
	
    /**
     * 
     * @Description:删除,可传多个条件，关系等于和and，只传PK的时候是删除某个
     * @param @param Id
     * @return void
     * @throws
     */
    void delete(T entity);
    
    /**
     * 
     * @Description:保存
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    void save(T entity);
    /**
     * 更新
     * @param entity
     */
    void update(T entity);

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
    public Page<T> page(Page page);
    
    /**
     * 用主键获取对象
     * @param clazz
     * @param pk
     * @return
     */
    public T getOne(Class clazz,String pk);

    /**
     * 根据ids查询实体
     * @param ids
     * @return
     */
    public List<T> getByids(Class clazz,List<String> ids);

    /**
     * 动态代开延迟加载的page查询
     * @param page
     * @param fields
     * @return
     */
    public Page<T> pageNotLazy(Page page,String [] fields,T t);

    /**
     * 添加额外条件
     * @param page
     * @param fields
     * @param t
     * @param criterions
     * @return
     */
    public Page<T> pageNotLazy(Page page, String [] fields, Criterion [] criterions, T t);
    
}
