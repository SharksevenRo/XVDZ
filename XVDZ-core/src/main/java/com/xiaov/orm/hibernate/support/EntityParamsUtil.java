package com.xiaov.orm.hibernate.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.xiaov.utils.ReflectionUtils;
import com.xiaov.utils.StrKit;

/**
 * 将实体类中非空的参数封装成Map、eq ，即相等的模式
 * @author Sharkseven
 *
 */
public class EntityParamsUtil<T> {
	
	/**
	 * 将参数打包成Map
	 * @param page
	 * @param isPage 是否分页
	 * @return
	 */
	public Map<String,Object> paramsToMap(T t,boolean isPage){
		Class clazz = t.getClass();
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(clazz, !isPage);
		
		Object value = null;
		Map<String, Object> map=new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			try {
				value= ReflectionUtils.invokeGetterMethod(t, field.getName());
			} catch (Exception e) {
			}
			
			if(value!=null){
				map.put(StrKit.firstCharToLowerCase(field.getName()), value);
			}
		}
		return map;
	}
	/**
	 * 将参数打包成eq 暂时支持EQ and的情况，就是相等and的情况
	 * @param t
	 * @param isPage
	 * @return
	 */
	public List<SimpleExpression> paramsToEqs(T t,boolean isPage){
		Class clazz=t.getClass();
		List<SimpleExpression> criterions=new ArrayList<SimpleExpression>();
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(clazz, !isPage);
		for (Field field : accessibleFields) {
			Object value=null;
			try {
				value= ReflectionUtils.invokeGetterMethod(t, field.getName());
			} catch (Exception e) {
			}
		
			if(value!=null){
				criterions.add(Restrictions.eq(field.getName(), value));
			}
		}
		return criterions;
	}
	/**
	 * 非分页查询打包参数调用
	 * @param t
	 * @return
	 */
	public List<SimpleExpression> paramsToEqs(T t){
		return paramsToEqs(t, false);
	}
	/**
	 * 非分页查询打包参数调用
	 * @param t
	 * @return
	 */
	public Map<String,Object> paramsToMap(T t){
		return paramsToMap(t, false);
	}
}
