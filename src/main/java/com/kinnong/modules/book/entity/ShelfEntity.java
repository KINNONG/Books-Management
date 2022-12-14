package com.kinnong.modules.book.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 书架
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
public class ShelfEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Long memberId;
	//
	private Integer bookId;
	//
	private String bookName;
	//
	private String picUrl;
	//
	private BigDecimal price;
	//
	private Integer num;
	
	private String author;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * 获取：
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * 设置：
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * 获取：
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * 设置：
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取：
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * 设置：
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：
	 */
	public Integer getNum() {
		return num;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
