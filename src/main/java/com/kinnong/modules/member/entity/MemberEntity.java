package com.kinnong.modules.member.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	// 昵称
	private String nickname;
	// 头像
	private String avatarUrl;

	// 性别
	private String gender;

	private String realName;

	private String mobile;

	private String loginName;

	private String password;

	// 创建时间
	private Date createTime;
	
	private BigDecimal deposit;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * 支付类型：wechat-微信，alipay-支付宝，bank-银行卡
	 */
	private String paymentType;

	/**
	 * 微信账户
	 */
	private String wechatAccount;

	/**
	 * 支付宝账户
	 */
	private String alipayAccount;

	/**
	 * 银行卡号
	 */
	private String bankCardNumber;

	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 持卡人姓名
	 */
	private String cardholderName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	/**
	 * 设置：身份证号
	 * @param idCard 身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 获取：身份证号
	 * @return String
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * 设置：支付类型
	 * @param paymentType 支付类型
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 获取：支付类型
	 * @return String
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * 设置：微信账户
	 * @param wechatAccount 微信账户
	 */
	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	/**
	 * 获取：微信账户
	 * @return String
	 */
	public String getWechatAccount() {
		return wechatAccount;
	}

	/**
	 * 设置：支付宝账户
	 * @param alipayAccount 支付宝账户
	 */
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	/**
	 * 获取：支付宝账户
	 * @return String
	 */
	public String getAlipayAccount() {
		return alipayAccount;
	}

	/**
	 * 设置：银行卡号
	 * @param bankCardNumber 银行卡号
	 */
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	/**
	 * 获取：银行卡号
	 * @return String
	 */
	public String getBankCardNumber() {
		return bankCardNumber;
	}

	/**
	 * 设置：银行名称
	 * @param bankName 银行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 获取：银行名称
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 设置：持卡人姓名
	 * @param cardholderName 持卡人姓名
	 */
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	/**
	 * 获取：持卡人姓名
	 * @return String
	 */
	public String getCardholderName() {
		return cardholderName;
	}
}
