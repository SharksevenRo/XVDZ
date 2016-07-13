package com.xiaov.model;

import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PageRequest;

/**
 * Created by Sharkseven on 2016/7/13.
 */
public class SearchModel extends Page {

    private String search;

    public SearchModel(PageRequest request, String search) {
        super(request);
        this.search = search;
    }
    public SearchModel(){

    }
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
