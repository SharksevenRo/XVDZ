package com.xiaov.service.interfaces;

import com.xiaov.model.Dynamic;
import com.xiaov.service.BaseService;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
public interface DynamicService  extends BaseService<Dynamic>{
    public List<Dynamic> getAll();
}
