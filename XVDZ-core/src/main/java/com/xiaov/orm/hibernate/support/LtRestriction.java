/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiaov.orm.hibernate.support;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.xiaov.orm.core.RestrictionNames;

/**
 * 小于约束 ( from object o where o.value < ?) RestrictionName:LT
 * <p>
 * 表达式:LT属性类型_属性名称[_OR_属性名称...]
 * </p>
 * 
 * @author maurice
 *
 */
public class LtRestriction extends CriterionSingleValueSupport{
	
	/*
	 * (non-Javadoc)
	 * @see com.github.dactiv.orm.core.hibernate.CriterionBuilder#getRestrictionName()
	 */
	public String getRestrictionName() {
		return RestrictionNames.LT;
	}

	/*
	 * (non-Javadoc)
	 * @see com.github.dactiv.orm.core.hibernate.CriterionBuilder#build(java.lang.String, java.lang.Object)
	 */
	public Criterion build(String propertyName, Object value) {
		return Restrictions.lt(propertyName, value);
	}

}
