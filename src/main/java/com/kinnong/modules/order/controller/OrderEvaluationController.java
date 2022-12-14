package com.kinnong.modules.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.order.entity.OrderEvaluationEntity;
import com.kinnong.modules.order.service.OrderEvaluationService;


/**
 * 订单评价
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/evaluation")
public class OrderEvaluationController {
	@Autowired
	private OrderEvaluationService orderEvaluationService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderEvaluationEntity> orderEvaluationList = orderEvaluationService.queryList(query);
		int total = orderEvaluationService.queryTotal(query);
		
		return R.ok().put("rows", orderEvaluationList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		OrderEvaluationEntity orderEvaluation = orderEvaluationService.queryObject(id);
		
		return R.ok().put("orderEvaluation", orderEvaluation);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody OrderEvaluationEntity orderEvaluation){
		orderEvaluationService.save(orderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody OrderEvaluationEntity orderEvaluation){
		orderEvaluationService.update(orderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		orderEvaluationService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
