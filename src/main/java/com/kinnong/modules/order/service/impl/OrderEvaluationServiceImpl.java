package com.kinnong.modules.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.order.dao.OrderBookDao;
import com.kinnong.modules.order.dao.OrderDao;
import com.kinnong.modules.order.dao.OrderEvaluationDao;
import com.kinnong.modules.order.entity.OrderBookEntity;
import com.kinnong.modules.order.entity.OrderEntity;
import com.kinnong.modules.order.entity.OrderEvaluationEntity;
import com.kinnong.modules.order.service.OrderEvaluationService;


@Service("orderEvaluationService")
public class OrderEvaluationServiceImpl implements OrderEvaluationService {
	@Autowired
	private OrderEvaluationDao orderEvaluationDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderBookDao orderBookDao;
	
	@Override
	public OrderEvaluationEntity queryObject(Integer id){
		return orderEvaluationDao.queryObject(id);
	}
	
	@Override
	public List<OrderEvaluationEntity> queryList(Map<String, Object> map){
		return orderEvaluationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderEvaluationDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderEvaluationEntity orderEvaluation){
		OrderEntity order = new OrderEntity();
		if(orderEvaluation.getOrderId() != null) {
			order.setOrderStatus(3);
	    	order.setId(orderEvaluation.getOrderId());
	    	orderDao.update(order);
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("orderId", orderEvaluation.getOrderId());
	    	List<OrderBookEntity> orderBookList = orderBookDao.queryList(map);
	    	for (OrderBookEntity orderBook : orderBookList) {
	    		orderEvaluation.setBookId(orderBook.getBookId());
	    		orderEvaluationDao.save(orderEvaluation);
			}
		}else {
			orderEvaluationDao.save(orderEvaluation);
		}
	}
	
	@Override
	public void update(OrderEvaluationEntity orderEvaluation){
		orderEvaluationDao.update(orderEvaluation);
	}
	
	@Override
	public void delete(Integer id){
		orderEvaluationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderEvaluationDao.deleteBatch(ids);
	}
	
}
