package com.kinnong.modules.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kinnong.modules.order.entity.OrderShipmentEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 借阅配送表
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@Mapper
public interface OrderShipmentDao extends BaseDao<OrderShipmentEntity> {

	OrderShipmentEntity queryByOrderId(Integer orderId);
	
}
