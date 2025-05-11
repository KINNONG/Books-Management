package com.kinnong.modules.member.dao;

import com.kinnong.modules.member.entity.DepositRefundEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 押金退还申请DAO
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@Mapper
public interface DepositRefundDao {
    
    DepositRefundEntity queryObject(Long id);
    
    List<DepositRefundEntity> queryList(Map<String, Object> map);
    
    int queryTotal(Map<String, Object> map);
    
    void save(DepositRefundEntity depositRefund);
    
    void update(DepositRefundEntity depositRefund);
    
    void delete(Long id);
    
    void deleteBatch(Long[] ids);
    
    /**
     * 查询用户未完成的退押金申请
     * @param memberId 会员ID
     * @return 未完成的申请记录
     */
    DepositRefundEntity queryPendingByMemberId(Long memberId);
} 