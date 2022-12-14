package com.kinnong.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UeditorConfig{
	
	@Value("${ueditor.imageActionName}")
	private String imageActionName;
	@Value("${ueditor.imageUrlPrefix}")
	private String imageUrlPrefix;
	@Value("${ueditor.imagePath}")
	private String imagePath;
	@Value("${ueditor.imageFieldName}")
	private String imageFieldName;
	@Value("${ueditor.imageMaxSize}")
	private Integer imageMaxSize;
	private String[] imageAllowFiles = {".png", ".jpg", ".jpeg", ".gif"};
	
	public String getImageActionName() {
		return imageActionName;
	}
	public void setImageActionName(String imageActionName) {
		this.imageActionName = imageActionName;
	}
	public String getImageUrlPrefix() {
		return imageUrlPrefix;
	}
	public void setImageUrlPrefix(String imageUrlPrefix) {
		this.imageUrlPrefix = imageUrlPrefix;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageFieldName() {
		return imageFieldName;
	}
	public void setImageFieldName(String imageFieldName) {
		this.imageFieldName = imageFieldName;
	}
	public Integer getImageMaxSize() {
		return imageMaxSize;
	}
	public void setImageMaxSize(Integer imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}
	public String[] getImageAllowFiles() {
		return imageAllowFiles;
	}
	public void setImageAllowFiles(String[] imageAllowFiles) {
		this.imageAllowFiles = imageAllowFiles;
	}
	
}
