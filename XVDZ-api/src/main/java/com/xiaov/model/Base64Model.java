package com.xiaov.model;

/**
 * Created by Sharkseven on 2016/7/26.
 */
public class Base64Model {

    private String [] aDesignImgsBase64;
    private String [] aFullImgsBase64;

    public Base64Model(String[] aDesignImgsBase64, String[] aFullImgsBase64) {
        this.aDesignImgsBase64 = aDesignImgsBase64;
        this.aFullImgsBase64 = aFullImgsBase64;
    }

    public Base64Model() {
    }

    public String[] getaDesignImgsBase64() {
        return aDesignImgsBase64;
    }

    public void setaDesignImgsBase64(String[] aDesignImgsBase64) {
        this.aDesignImgsBase64 = aDesignImgsBase64;
    }

    public String[] getaFullImgsBase64() {
        return aFullImgsBase64;
    }

    public void setaFullImgsBase64(String[] aFullImgsBase64) {
        this.aFullImgsBase64 = aFullImgsBase64;
    }
}
