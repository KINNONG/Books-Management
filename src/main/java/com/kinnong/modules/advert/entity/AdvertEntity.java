package com.kinnong.modules.advert.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Banner
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class AdvertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 图片地址
	private String picUrl;
	// 是否启用，0：禁用，1：启用
	private Integer enable;

	private String link;
	private Integer sort;

	// 创建时间
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 设置：是否启用，0：禁用，1：启用
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * 获取：是否启用，0：禁用，1：启用
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
