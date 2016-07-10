package com.xiaov.service.interfaces;

import java.util.List;

import com.xiaov.model.Types;
import com.xiaov.service.BaseService;

/**
 * Created by yymao on 2016/4/26.
 */
public interface TypesService extends BaseService<Types>{
	
	/**
	 * 获取一级父类型 
	 * @return
	 */
	public List<Types> getTypesByParent(Types types);

	public List<Types> getMaterialAndDefault(Types types);

	/**
	 * 获取标签类型
	 * @return
     */
	public  List<Types> getLebles(Types types);
}
