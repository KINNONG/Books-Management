package com.kinnong.modules.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.DepositRefundEntity;
import com.kinnong.modules.member.service.DepositRefundService;

/**
 * 押金退还申请管理
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/depositRefund")
public class DepositRefundController {
    
    @Autowired
    private DepositRefundService depositRefundService;
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        query.put("sidx", "id");
        query.put("order", "desc");
        return R.ok()
            .put("rows", depositRefundService.queryList(query))
            .put("total", depositRefundService.queryTotal(query));
    }
    
    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("depositRefund", depositRefundService.queryObject(id));
    }
    
    /**
     * 审核
     */
    @RequestMapping("/audit")
    public R audit(@RequestBody DepositRefundEntity depositRefund) {
        if (depositRefund.getId() == null || depositRefund.getStatus() == null) {
            return R.error("参数不完整");
        }
        
        if (depositRefund.getStatus() != 1 && depositRefund.getStatus() != 2) {
            return R.error("审核状态参数错误");
        }
        
        // 使用默认管理员ID，通常是1
        Long auditUserId = 1L;
        
        depositRefundService.audit(
            depositRefund.getId(), 
            depositRefund.getStatus(), 
            auditUserId, 
            depositRefund.getRemark()
        );
        
        return R.ok();
    }
} 