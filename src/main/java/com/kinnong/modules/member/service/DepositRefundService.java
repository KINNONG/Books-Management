package com.kinnong.modules.member.service;

import com.kinnong.modules.member.entity.DepositRefundEntity;
import java.util.List;
import java.util.Map;

/**
 * 押金退还申请
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface DepositRefundService {
    
    DepositRefundEntity queryObject(Long id);
    
    List<DepositRefundEntity> queryList(Map<String, Object> map);
    
    int queryTotal(Map<String, Object> map);
    
    void save(DepositRefundEntity depositRefund);
    
    void update(DepositRefundEntity depositRefund);
    
    void delete(Long id);
    
    void deleteBatch(Long[] ids);
    
    /**
     * 审核押金退还申请
     * @param id 申请ID
     * @param status 审核状态，1：通过，2：拒绝
     * @param auditUserId 审核人ID
     * @param remark 备注
     */
    void audit(Long id, Integer status, Long auditUserId, String remark);
    
    /**
     * 查询用户未完成的退押金申请
     * @param memberId 会员ID
     * @return 未完成的申请记录
     */
    DepositRefundEntity queryPendingByMemberId(Long memberId);
} 