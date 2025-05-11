package com.kinnong.modules.member.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kinnong.modules.member.dao.DepositRefundDao;
import com.kinnong.modules.member.dao.MemberDao;
import com.kinnong.modules.member.entity.DepositRefundEntity;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.DepositRefundService;

@Service("depositRefundService")
public class DepositRefundServiceImpl implements DepositRefundService {
    
    @Autowired
    private DepositRefundDao depositRefundDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Override
    public DepositRefundEntity queryObject(Long id) {
        return depositRefundDao.queryObject(id);
    }
    
    @Override
    public List<DepositRefundEntity> queryList(Map<String, Object> map) {
        return depositRefundDao.queryList(map);
    }
    
    @Override
    public int queryTotal(Map<String, Object> map) {
        return depositRefundDao.queryTotal(map);
    }
    
    @Override
    public void save(DepositRefundEntity depositRefund) {
        depositRefundDao.save(depositRefund);
    }
    
    @Override
    public void update(DepositRefundEntity depositRefund) {
        depositRefundDao.update(depositRefund);
    }
    
    @Override
    public void delete(Long id) {
        depositRefundDao.delete(id);
    }
    
    @Override
    public void deleteBatch(Long[] ids) {
        depositRefundDao.deleteBatch(ids);
    }
    
    @Override
    @Transactional
    public void audit(Long id, Integer status, Long auditUserId, String remark) {
        // 查询申请信息
        DepositRefundEntity refund = depositRefundDao.queryObject(id);
        if (refund == null) {
            return;
        }
        
        // 更新申请状态
        DepositRefundEntity updateEntity = new DepositRefundEntity();
        updateEntity.setId(id);
        updateEntity.setStatus(status);
        updateEntity.setAuditTime(new Date());
        updateEntity.setAuditUserId(auditUserId);
        updateEntity.setRemark(remark);
        depositRefundDao.update(updateEntity);
        
        // 如果审核通过，则更新会员押金为0
        if (status == 1) {
            MemberEntity member = new MemberEntity();
            member.setId(refund.getMemberId());
            member.setDeposit(new BigDecimal("0"));
            memberDao.update(member);
        }
    }
    
    @Override
    public DepositRefundEntity queryPendingByMemberId(Long memberId) {
        return depositRefundDao.queryPendingByMemberId(memberId);
    }
} 