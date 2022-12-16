package com.kinnong.modules.order.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.order.entity.OrderShipmentEntity;

/**
 * 借阅配送表
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface OrderShipmentService {
	
	OrderShipmentEntity queryObject(Integer id);
	
	List<OrderShipmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderShipmentEntity orderShipment);
	
	void update(OrderShipmentEntity orderShipment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
