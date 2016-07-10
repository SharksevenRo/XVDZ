package com.xiaov.service.impl;

import com.xiaov.dao.MaterialDao;
import com.xiaov.dao.TypesDao;
import com.xiaov.model.Material;
import com.xiaov.model.Types;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.TypesService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/4/26.
 */
@Service
public class TypesImpl extends BaseServiceImpl<Types> implements TypesService {

    @Autowired
    private TypesDao dao;
    
    @Autowired
    private MaterialDao materialDao;
    @Override
    public void delete(Types entity) {
        super.delete(entity);
    }

    @Override
    public void update(Types entity) {
        super.update(entity);
    }

    @Override
    public void save(Types entity) {
        super.save(entity);
    }

    @Override
    public List<Types> loadAll(Types entity) {
        return super.loadAll(entity);
    }



    @Override
    public Types getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }
    /**
     * 查询产品类型
     */
	public List<Types> getProductType() {
		List<Types> list = null;
		Object[] param = {"10",false};
		try {
			list = //dao.findByQuery("select * from types t where t.parentType.id=? and t.deleteFlag=?",param);
			dao.findByProperty("parentType.id", "10");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Types> getTypesByParent(Types types){
		
		return dao.getEntitiestNotLazy(new Types(), null,new Criterion[]{Restrictions.eq("parentType.id", types.getId()),Restrictions.eq("deleteFlag", 0)});
	}

	public List<Types> getMaterialAndDefault(Types types) {
		List<Types> child = dao.getEntitiestNotLazy(new Types(), new String []{"parentType"},new Criterion []{Restrictions.eq("parentType", types.getParentType())});
		for (Types type : child) {
			fillMaterial(type);
		}
		return child;
	}
	
	public Types fillMaterial(Types types){
		
		List<Material> materials = materialDao.getEntitiestNotLazy(new Material(), new String []{"dbTypes"},new Criterion []{Restrictions.eq("dbTypes", types)});
		types.setMaterials(materials);
		return types;
	}

	public  List<Types> getLebles(Types types){

		List<Types> parents = getTypesByParent(types);

		List<Types> children;
		for (Types t :
				parents
				) {
			children=getTypesByParent(t);
			t.setChildren(children);
		}
		return parents;
	}
}
