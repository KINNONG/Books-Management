package com.kinnong.modules.order.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.utils.R;
import com.kinnong.modules.order.entity.OrderEvaluationEntity;
import com.kinnong.modules.order.service.OrderEvaluationService;

/**
 * 评价留言
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/api/evaluation")
public class ApiEvaluationController {
	
	@Autowired
	private OrderEvaluationService orderEvaluationService;
	
	@AuthIgnore
    @GetMapping("list")
	public R list(@RequestParam Map<String, Object> map) {
		List<OrderEvaluationEntity> evaluations = orderEvaluationService.queryList(map);
		return R.ok().put("evaluations", evaluations);
	}
	
	@PostMapping("save")
	public R save(@RequestAttribute("userId") Long userId, @RequestBody OrderEvaluationEntity orderEvaluation) {
		orderEvaluation.setCreateTime(new Date());
		orderEvaluation.setMemberId(userId);
		orderEvaluationService.save(orderEvaluation);
		return R.ok();
	}
}
