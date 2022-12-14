package com.kinnong.modules.advert.controller;

import java.util.Date;
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
import com.kinnong.modules.advert.entity.AdvertEntity;
import com.kinnong.modules.advert.service.AdvertService;

/**
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/advert")
public class AdvertController {
	@Autowired
	private AdvertService advertService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
    	params.put("order", "ASC");
        Query query = new Query(params);

		List<AdvertEntity> advertList = advertService.queryList(query);
		int total = advertService.queryTotal(query);
		
		return R.ok().put("rows", advertList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		AdvertEntity advert = advertService.queryObject(id);
		
		return R.ok().put("advert", advert);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody AdvertEntity advert){
		advert.setCreateTime(new Date());
		advertService.save(advert);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody AdvertEntity advert){
		advertService.update(advert);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		advertService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
