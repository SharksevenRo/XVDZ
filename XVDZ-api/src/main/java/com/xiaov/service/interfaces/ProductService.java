package com.xiaov.service.interfaces;

import java.util.List;

import com.xiaov.model.MutiType;
import com.xiaov.model.Product;
import com.xiaov.model.Types;
import com.xiaov.orm.core.PageRequest;
import com.xiaov.service.BaseService;

public interface ProductService extends BaseService<Product>{

	List<Product> getSimpleProduct(Types types) throws Exception;

	List<Product> getProductByMutiType(PageRequest pageRequest,List<MutiType> types);

}
