package com.xiaov.service.interfaces;

import java.util.List;

import com.xiaov.model.MutiType;
import com.xiaov.model.Product;
import com.xiaov.model.SearchModel;
import com.xiaov.model.Types;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PageRequest;
import com.xiaov.service.BaseService;

public interface ProductService extends BaseService<Product>{

	List<Product> getSimpleProduct(Types types) throws Exception;

	List<Product> getProductByMutiType(PageRequest pageRequest,String mutiType);

	List<Product> designerPage(Product product);

	List<Product> search(SearchModel search);
	public List<Product> pageByType(Product product);
	public Product geOneDetail(Product product);
	public Product geOneDetailNotClose(Product product);
	public List<Product> hot(Product product);

	List<Product> moudule(Product product);
}
