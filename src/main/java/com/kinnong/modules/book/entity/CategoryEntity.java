package com.kinnong.modules.book.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 分类名称
	private String categoryName;
	// 排序
	private Integer sort;

	private String picUrl;

	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 设置：分类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 获取：分类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
