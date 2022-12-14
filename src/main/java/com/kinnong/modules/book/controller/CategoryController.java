package com.kinnong.modules.book.controller;

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
import com.kinnong.modules.book.entity.CategoryEntity;
import com.kinnong.modules.book.service.CategoryService;


/**
 * 分类
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 查询全部
	 * @param params
	 * @return
	 */
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
    	params.put("order", "ASC");
		List<CategoryEntity> categoryList = categoryService.queryList(params);
		return R.ok().put("categoryList", categoryList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sidx", "sort");
    	params.put("order", "ASC");
        Query query = new Query(params);

		List<CategoryEntity> categoryList = categoryService.queryList(query);
		int total = categoryService.queryTotal(query);
		
		return R.ok().put("rows", categoryList).put("total", total);
	}
	
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping("/getAll")
	public R getAll(){
		List<CategoryEntity> categoryList = categoryService.queryList(null);
		return R.ok().put("categoryList", categoryList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		CategoryEntity category = categoryService.queryObject(id);
		
		return R.ok().put("category", category);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody CategoryEntity category){
		category.setCreateTime(new Date());
		categoryService.save(category);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody CategoryEntity category){
		categoryService.update(category);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		categoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
