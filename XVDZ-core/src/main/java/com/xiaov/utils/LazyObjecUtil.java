package com.xiaov.utils;

import java.util.List;

import com.xiaov.orm.core.Page;
/**
 * 延迟加载对象成员设空处理
 * @author Sharkseven
 *
 */
public class LazyObjecUtil {

	/**
	 * 将结果集中的延迟加载对象设NULL,解决无法打包成json数据
	 * @param data
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static <T>  List<T> LazySetNull(List<T> data,String fieldName) throws Exception{
		
		if(data.size()>0){
				for (T t : data) {
					ReflectionUtils.setFieldValue(t, fieldName, null);
				}
		}else{
			new RuntimeException("list 为空");
		}
		return data;
	}
	/**
	 * 多个延迟加载成员变量
	 * @param data
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
	public static <T>  List<T> LazySetNull(List<T> data,String[] fieldNames) throws Exception{
		
		for (String string : fieldNames) {
			data=LazySetNull(data, string);
		}
		return data;
	}
	/**
	 * Page中的数据延迟加载对象设NULL
	 * @param page 
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
	public static <T>  Page<T>  LazyPageSetNull(Page<T> page,String fieldName) throws Exception{
		 page.setResult(LazySetNull(page.getResult(), fieldName));
		 return page;
	}
	/**
	 * Page中的数据延迟加载对象设NULL,多个延迟加载成员变量
	 * @param page
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
	public static <T>  Page<T>  LazyPageSetNull(Page<T> page,String[] fieldNames) throws Exception{
		 
		for (String string : fieldNames) {
			page.setResult(LazySetNull(page.getResult(), string));
		}
		 return page;
	}
}
