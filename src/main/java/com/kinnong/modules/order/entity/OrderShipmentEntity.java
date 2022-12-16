package com.kinnong.modules.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 借阅配送表
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class OrderShipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主键自增
	private Integer id;
	// 借阅ID
	private Integer orderId;
	// 姓名
	private String contacts;
	// 手机号码
	private String mobile;
	// 省
	private Integer provinceId;
	// 省名称
	private String provinceName;
	// 市
	private Integer cityId;
	// 市名称
	private String cityName;
	// 区
	private Integer districtId;
	// 区名称
	private String districtName;
	// 详细地址
	private String address;
	// 邮政编码
	private Integer zipcode;
	// 更新时间
	private Date updateTime;

	/**
	 * 设置：主键自增
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：主键自增
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置：省
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取：省
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置：市
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取：市
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置：区
	 */
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	/**
	 * 获取：区
	 */
	public Integer getDistrictId() {
		return districtId;
	}

	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

}
