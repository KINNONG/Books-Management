package com.kinnong.modules.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.order.dao.OrderShipmentDao;
import com.kinnong.modules.order.entity.OrderShipmentEntity;
import com.kinnong.modules.order.service.OrderShipmentService;


@Service("orderShipmentService")
public class OrderShipmentServiceImpl implements OrderShipmentService {
	@Autowired
	private OrderShipmentDao orderShipmentDao;
	
	@Override
	public OrderShipmentEntity queryObject(Integer id){
		return orderShipmentDao.queryObject(id);
	}
	
	@Override
	public List<OrderShipmentEntity> queryList(Map<String, Object> map){
		return orderShipmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderShipmentDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderShipmentEntity orderShipment){
		orderShipmentDao.save(orderShipment);
	}
	
	@Override
	public void update(OrderShipmentEntity orderShipment){
		orderShipmentDao.update(orderShipment);
	}
	
	@Override
	public void delete(Integer id){
		orderShipmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderShipmentDao.deleteBatch(ids);
	}
	
}
