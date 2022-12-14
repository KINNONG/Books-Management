package com.kinnong.modules.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
public class OrderBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 借阅ID
	private Integer orderId;
	// 商品ID
	private Integer bookId;

	private Integer num;

	private String picUrl;

	private BigDecimal price;

	private String bookName;
	
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
	 * 设置：借阅ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：借阅ID
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：商品ID
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * 获取：商品ID
	 */
	public Integer getBookId() {
		return bookId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
