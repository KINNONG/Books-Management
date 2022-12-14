package com.kinnong.modules.order.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.order.entity.OrderEntity;

/**
 * 借阅
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
public interface OrderService {
	
	OrderEntity queryObject(Integer id);
	
	List<OrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEntity order);
	
	void update(OrderEntity order);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void createOrder(OrderEntity orderEntity);

	void updateByOrderNumber(OrderEntity order);

	List<Map<String, String>> queryOrderCount();

	void returnBook(OrderEntity order);
}
