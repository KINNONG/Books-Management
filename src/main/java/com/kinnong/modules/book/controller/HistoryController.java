package com.kinnong.modules.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.modules.book.entity.HistoryEntity;
import com.kinnong.modules.book.service.HistoryService;
import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;




/**
 * 浏览记录
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("history")
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HistoryEntity> historyList = historyService.queryList(query);
		int total = historyService.queryTotal(query);
		
		return R.ok().put("rows", historyList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		HistoryEntity history = historyService.queryObject(id);
		
		return R.ok().put("history", history);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody HistoryEntity history){
		historyService.save(history);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody HistoryEntity history){
		historyService.update(history);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		historyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
