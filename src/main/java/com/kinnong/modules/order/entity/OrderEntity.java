package com.kinnong.modules.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kinnong.modules.member.entity.MemberEntity;

/**
 * 借阅
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 借阅编号
	private String orderNumber;
	// 借阅金额
	private BigDecimal totalAmount;
	// 借阅状态，1：借阅中，2：已归还
	private Integer orderStatus;
	// 创建时间
	private Date createTime;

	private List<OrderBookEntity> orderBookList;

	private Long memberId;

	private OrderShipmentEntity orderShipment;

	// 备注
	private String remark;

	//物流
	private String courierNum;
	
	//
	private MemberEntity member;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	

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
	 * 设置：借阅编号
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * 获取：借阅编号
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 设置：借阅状态，1：借阅中，2：已归还
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取：借阅状态，1：借阅中，2：已归还
	 */
	public Integer getOrderStatus() {
		return orderStatus;
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

	public List<OrderBookEntity> getOrderBookList() {
		return orderBookList;
	}

	public void setOrderBookList(List<OrderBookEntity> orderBookList) {
		this.orderBookList = orderBookList;
	}


	public OrderShipmentEntity getOrderShipment() {
		return orderShipment;
	}

	public void setOrderShipment(OrderShipmentEntity orderShipment) {
		this.orderShipment = orderShipment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCourierNum() {
		return courierNum;
	}

	public void setCourierNum(String courierNum) {
		this.courierNum = courierNum;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
