package com.kinnong.modules.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kinnong.modules.order.entity.OrderEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 借阅
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

	void updateByOrderNumber(OrderEntity order);

	List<Map<String, String>> queryOrderCount();
	
}
