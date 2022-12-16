package com.kinnong.modules.order.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.order.entity.OrderEvaluationEntity;

/**
 * 订单评价
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface OrderEvaluationService {
	
	OrderEvaluationEntity queryObject(Integer id);
	
	List<OrderEvaluationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEvaluationEntity orderEvaluation);
	
	void update(OrderEvaluationEntity orderEvaluation);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
