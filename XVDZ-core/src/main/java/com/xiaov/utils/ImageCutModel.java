package com.xiaov.utils;

import org.springframework.web.multipart.MultipartFile;



public class ImageCutModel{
	
	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;
	private Integer ch;
	private Integer cw;
	private MultipartFile imageFile;
	private String imgName;
	
	public ImageCutModel() {
		super();
	}

	public ImageCutModel(Integer x, Integer y, Integer w, Integer h,
			Integer ch, Integer cw, MultipartFile imageFile, String imgName) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.ch = ch;
		this.cw = cw;
		this.imageFile = imageFile;
		this.imgName = imgName;
	}


	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public Integer getCw() {
		return cw;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setCw(Integer cw) {
		this.cw = cw;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

}
