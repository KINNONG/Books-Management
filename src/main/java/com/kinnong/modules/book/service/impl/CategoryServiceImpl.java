package com.kinnong.modules.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.book.dao.CategoryDao;
import com.kinnong.modules.book.entity.CategoryEntity;
import com.kinnong.modules.book.service.CategoryService;

import java.util.List;
import java.util.Map;


@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryEntity queryObject(Integer id){
		return categoryDao.queryObject(id);
	}
	
	@Override
	public List<CategoryEntity> queryList(Map<String, Object> map){
		return categoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return categoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CategoryEntity category){
		categoryDao.save(category);
	}
	
	@Override
	public void update(CategoryEntity category){
		categoryDao.update(category);
	}
	
	@Override
	public void delete(Integer id){
		categoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		categoryDao.deleteBatch(ids);
	}

}
