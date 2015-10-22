/**
 * @(#)ResourceInfo.java 2013-4-12
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-4-12 上午8:58:19
 */
public class ResourceInfo {

	/**
	 * 图片资源URI，用户新闻营销信息的图片
	 */
	private String img;

	/**
	 * 新闻营销信息文字
	 */
	private String text;

	/**
	 * 照片的base64编码内容，用于下发指令拍照的图片
	 */
	@JsonIgnore
	private String photo;

	/**
	 * @return Returns the photo.
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            The photo to set.
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return Returns the img.
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img
	 *            The img to set.
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResourceInfo [img=" + img + ", text=" + text + ", photo="
				+ photo + "]";
	}
}
