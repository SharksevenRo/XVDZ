package com.xiaov.service.interfaces;

import com.xiaov.model.DiscountCode;
import com.xiaov.service.BaseService;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
public interface DiscountCodeService extends BaseService<DiscountCode> {

    public List<DiscountCode> getByProperty(String propertynmae, String propertyvalues);
}
