package com.kinnong.modules.member.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class MemberAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//会员ID
	private Long memberId;
	//openid
	private String openid;
	//授权类型
	private String authType;
	//创建时间
	private Date createTime;

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
	 * 设置：会员ID
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员ID
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：授权类型
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * 获取：授权类型
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
