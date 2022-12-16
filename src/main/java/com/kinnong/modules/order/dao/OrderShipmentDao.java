package com.kinnong.modules.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kinnong.modules.order.entity.OrderShipmentEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 借阅配送表
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@Mapper
public interface OrderShipmentDao extends BaseDao<OrderShipmentEntity> {

	OrderShipmentEntity queryByOrderId(Integer orderId);
	
}
