package com.kinnong.modules.member.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 押金退还申请
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public class DepositRefundEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private Long id;
    // 会员ID
    private Long memberId;
    // 退还金额
    private BigDecimal amount;
    // 状态，0：待审核，1：已通过，2：已拒绝
    private Integer status;
    // 申请时间
    private Date applyTime;
    // 审核时间
    private Date auditTime;
    // 审核人ID
    private Long auditUserId;
    // 备注
    private String remark;
    
    // 会员信息（非数据库字段）
    private MemberEntity member;
    // 审核人信息（非数据库字段）
    private String auditUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }
} 