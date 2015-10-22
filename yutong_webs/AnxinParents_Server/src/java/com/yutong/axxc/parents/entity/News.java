package com.yutong.axxc.parents.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News  implements Serializable{

	private static final long serialVersionUID = -8301612632212283520L;
	@JsonProperty("news_type")
	private int type ; //-1 全部  0 行业 1宇通
	@JsonProperty("news_id")
	private int id;
	@JsonProperty("news_title")
	private String title;
	@JsonProperty("news_summary")
	private String summary;//新闻摘要
	@JsonProperty("news_content")
	private String content;//新闻内容
	@JsonProperty("news_image_id")
	private String imgId;
	@JsonProperty("news_image_url")
	private String imgURL;//新闻图片URL
	@JsonProperty("news_author")
	private String publisher;
	@JsonProperty("news_time")
	private String publishDate;//发布时间
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		return "News [type=" + type + ", id=" + id + ", title=" + title
				+ ", summary=" + summary + ", content=" + content + ", imgId="
				+ imgId + ", imgURL=" + imgURL + ", publisher=" + publisher
				+ ", publishDate=" + publishDate + "]";
	}

	
	
}
